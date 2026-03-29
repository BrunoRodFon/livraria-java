package com.livraria.impl;

import com.livraria.model.*;
import com.livraria.repository.*;
import com.livraria.IEmprestimoService;
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
    private LivroExemplarRepository exemplarRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private MultaRepository multaRepository;

    @Autowired
    private MultaService multaService;

    // 📌 REALIZAR EMPRÉSTIMO
    @Override
    public Emprestimo realizarEmprestimo(Long alunoId, Long exemplarId) {

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        boolean possuiDivida = multaRepository.existsByEmprestimoAlunoAndStatus(
                aluno,
                Multa.Status.PENDENTE
        );

        if (possuiDivida) {
            throw new RuntimeException("Aluno possui multa pendente!");
        }

        LivroExemplar exemplar = exemplarRepository.findById(exemplarId)
                .orElseThrow(() -> new RuntimeException("Exemplar não encontrado"));

        if (!exemplar.isDisponivel()) {
            throw new RuntimeException("Livro não está disponível");
        }

        int ativos = emprestimoRepository.countByAlunoIdAndAtivoTrue(alunoId);
        if (ativos >= 3) {
            throw new RuntimeException("Aluno já possui 3 livros emprestados");
        }

        Emprestimo emp = new Emprestimo();
        emp.setAluno(aluno);
        emp.setLivroExemplar(exemplar);

        LocalDate hoje = LocalDate.now();
        emp.setDtEmprestimo(hoje);
        emp.setDtPrevista(hoje.plusDays(30));
        emp.setAtivo(true);

        // 🔥 CORREÇÃO CRÍTICA
        // SALVA PRIMEIRO O EMPRÉSTIMO NO BANCO
        emp = emprestimoRepository.save(emp);

        // 🔥 ATUALIZA O EXEMPLAR
        exemplar.setStatus(LivroExemplar.Status.EMPRESTADO);
        exemplarRepository.save(exemplar);

        return emp;
    }

    // 📌 DEVOLUÇÃO
    @Override
    public void devolver(Long emprestimoId) {

        Emprestimo emp = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        LivroExemplar exemplar = emp.getLivroExemplar(); // 🔥 FALTAVA ISSO

        // 🔥 LIBERA EXEMPLAR
        exemplar.setStatus(LivroExemplar.Status.DISPONIVEL);
        exemplarRepository.save(exemplar);

        emp.setDtDevolucao(LocalDate.now());
        emp.setAtivo(false);

        if (emp.getDtDevolucao().isAfter(emp.getDtPrevista())) {

            long diasAtraso = ChronoUnit.DAYS.between(
                    emp.getDtPrevista(),
                    emp.getDtDevolucao()
            );

            // 🔥 AGORA FUNCIONA
            multaService.gerarMultaAtraso(emp, diasAtraso);
        }

        emprestimoRepository.save(emp);
    }

    // 📌 ATRASO
    @Override
    public boolean estaAtrasado(Emprestimo emp) {
        return emp.getDtDevolucao() == null &&
                LocalDate.now().isAfter(emp.getDtPrevista());
    }

    // 📌 MULTA
    @Override
    public double calcularMulta(Emprestimo emp) {

        if (!estaAtrasado(emp)) return 0;

        long dias = ChronoUnit.DAYS.between(
                emp.getDtPrevista(),
                LocalDate.now()
        );

        return dias * 2.0;
    }

    @Override
    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }
}