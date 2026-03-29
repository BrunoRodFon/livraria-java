package com.livraria;

import com.livraria.model.Emprestimo;

import java.util.List;

public interface IEmprestimoService {

    Emprestimo realizarEmprestimo(Long alunoId, Long exemplarId);

    void devolver(Long emprestimoId);

    boolean estaAtrasado(Emprestimo emprestimo);

    double calcularMulta(Emprestimo emprestimo);

    // ➕ novos métodos para o controller
    List<Emprestimo> listarTodos();

}
