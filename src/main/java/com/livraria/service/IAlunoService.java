package com.livraria.service;

import com.livraria.model.Aluno;
import org.springframework.ui.Model;

public interface IAlunoService {
    String listar(Model model);
    String salvar(Aluno aluno, Model model);
}
