package com.livraria.service.impl;

import com.livraria.model.*;
import com.livraria.repository.*;
import com.livraria.service.IEmprestimoService;
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

    // 📌 REALIZAR EMPRÉSTIMO
    @Override
    public Emprestimo realizarEmprestimo(Long alunoId, Long exemplarId) {

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        LivroExemplar exemplar = exemplarRepository.findById(exemplarId)
                .orElseThrow(() -> new RuntimeException("Exemplar não encontrado"));

        // ✔ REGRA 1: Livro disponível
        if (!exemplar.getDisponivel()) {
            throw new RuntimeException("Livro não está disponível");
        }

        // ✔ REGRA 2: Máximo 3 livros
        int ativos = emprestimoRepository.countByAlunoIdAndAtivoTrue(alunoId);
        if (ativos >= 3) {
            throw new RuntimeException("Aluno já possui 3 livros emprestados");
        }

        // ✔ REGRA 3: Prazo de 30 dias
        Emprestimo emp = new Emprestimo();
        emp.setAluno(aluno);
        emp.setLivroExemplar(exemplar);

        LocalDate hoje = LocalDate.now();
        emp.setDtEmprestimo(hoje);
        emp.setDtPrevista(hoje.plusDays(30));
        emp.setAtivo(true);

        // Atualiza disponibilidade
        exemplar.setDisponivel(false);
        exemplarRepository.save(exemplar);

        return emprestimoRepository.save(emp);
    }

    // 📌 DEVOLUÇÃO
    @Override
    public void devolver(Long emprestimoId) {

        Emprestimo emp = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        emp.setDtDevolucao(LocalDate.now());
        emp.setAtivo(false);

        LivroExemplar exemplar = emp.getLivroExemplar();
        exemplar.setDisponivel(true);

        exemplarRepository.save(exemplar);
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

        return dias * 2.0; // R$2 por dia
    }

    @Override
    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

}