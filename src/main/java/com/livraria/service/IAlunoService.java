package com.livraria.service;

import com.livraria.model.Aluno;
import org.springframework.ui.Model;

import java.util.List;

public interface IAlunoService {
    String listar(Model model);
    String salvar(Aluno aluno, Model model);

    List<Aluno> listarTodos();
}
