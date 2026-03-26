package com.livraria.service;

import com.livraria.model.LivroExemplar;
import org.springframework.ui.Model;

import java.util.List;

public interface IExemplarService {

    String form (Model model);

    String salvar(LivroExemplar exemplar, Model model);

    String listar(Model model);

    List<LivroExemplar> listarTodosDisponiveis();
}
