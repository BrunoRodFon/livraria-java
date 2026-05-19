package com.livraria;

import com.livraria.model.Emprestimo;

import java.util.List;

public interface IEmprestimoService {

    Emprestimo realizarEmprestimo(Long alunoId, Long exemplarId);

    void devolver(Long emprestimoId);

    void deletar(Long emprestimoId);

    boolean estaAtrasado(Emprestimo emprestimo);

    // 🔥 CORREÇÃO IMPORTANTE:
    // agora retorna void porque você já salva a Multa no banco
    void calcularMulta(Emprestimo emprestimo);

    List<Emprestimo> listarTodos();
}