package com.livraria.repository;

import com.livraria.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    int countByAlunoIdAndAtivoTrue(Long alunoId);

    long countByLivroExemplar_Livro_Id(Long livroId);

    int countByLivroExemplarIdAndAtivoTrue(Long livroExemplarId);
}