package com.livraria.impl;

import com.livraria.IEmprestimoService;
import com.livraria.model.Aluno;
import com.livraria.model.Emprestimo;
import com.livraria.model.LivroExemplar;
import com.livraria.model.Multa;
import com.livraria.repository.AlunoRepository;
import com.livraria.repository.EmprestimoRepository;
import com.livraria.repository.LivroExemplarRepository;
import com.livraria.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EmprestimoService implements IEmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private LivroExemplarRepository exemplarRepository;

    @Autowired
    private MultaRepository multaRepository;

    @Override
    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    @Override
    public Emprestimo realizarEmprestimo(Long alunoId, Long exemplarId) {

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        LivroExemplar exemplar = exemplarRepository.findById(exemplarId)
                .orElseThrow(() -> new RuntimeException("Exemplar não encontrado"));

        // ✅ CORRETO: usando enum Status
        if (!exemplar.isDisponivel()) {
            throw new RuntimeException("Exemplar indisponível");
        }

        boolean possuiMulta = multaRepository
                .existsByEmprestimoAlunoAndStatus(aluno, Multa.Status.PENDENTE);

        if (possuiMulta) {
            throw new RuntimeException("Aluno possui multas pendentes");
        }

        Emprestimo emp = new Emprestimo();
        emp.setAluno(aluno);
        emp.setLivroExemplar(exemplar);
        emp.setDtEmprestimo(LocalDate.now());
        emp.setDtPrevista(LocalDate.now().plusDays(7));
        emp.setAtivo(true);

        // 🔥 muda status corretamente
        exemplar.setStatus(LivroExemplar.Status.EMPRESTADO);
        exemplarRepository.save(exemplar);

        return emprestimoRepository.save(emp);
    }

    @Override
    public void devolver(Long id) {

        Emprestimo emp = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        emp.setAtivo(false);
        emp.setDtDevolucao(LocalDate.now());

        LivroExemplar exemplar = emp.getLivroExemplar();

        // 🔥 volta para disponível
        exemplar.setStatus(LivroExemplar.Status.DISPONIVEL);

        exemplarRepository.save(exemplar);
        emprestimoRepository.save(emp);

        calcularMulta(emp);
    }

    @Override
    public boolean estaAtrasado(Emprestimo emprestimo) {

        if (emprestimo.getDtPrevista() == null || emprestimo.getDtDevolucao() == null) {
            return false;
        }

        return ChronoUnit.DAYS.between(
                emprestimo.getDtPrevista(),
                emprestimo.getDtDevolucao()
        ) > 0;
    }

    @Override
    public void calcularMulta(Emprestimo emprestimo) {

        if (!estaAtrasado(emprestimo)) {
            return;
        }

        long diasAtraso = ChronoUnit.DAYS.between(
                emprestimo.getDtPrevista(),
                emprestimo.getDtDevolucao()
        );

        boolean jaExiste = multaRepository
                .existsByEmprestimoAndTipo(emprestimo, Multa.Tipo.ATRASO);

        if (jaExiste) {
            return;
        }

        Multa multa = new Multa();
        multa.setEmprestimo(emprestimo);
        multa.setTipo(Multa.Tipo.ATRASO);
        multa.setStatus(Multa.Status.PENDENTE);
        multa.setValor(diasAtraso * 2.0);

        multaRepository.save(multa);
    }

    @Override
    public void deletar(Long id) {

        Emprestimo emp = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        boolean possuiMulta = multaRepository.existsByEmprestimoAlunoAndStatus(
                emp.getAluno(),
                Multa.Status.PENDENTE
        );

        if (possuiMulta) {
            throw new RuntimeException("Não é possível excluir empréstimo com multas pendentes");
        }

        emprestimoRepository.deleteById(id);
    }
}