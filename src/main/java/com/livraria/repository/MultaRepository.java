package com.livraria.repository;

import com.livraria.model.Aluno;
import com.livraria.model.Emprestimo;
import com.livraria.model.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultaRepository extends JpaRepository<Multa, Long> {
    boolean existsByEmprestimoAlunoAndStatus(Aluno aluno, Multa.Status status);

    boolean existsByEmprestimoAndTipo(Emprestimo emprestimo, Multa.Tipo tipo);
}