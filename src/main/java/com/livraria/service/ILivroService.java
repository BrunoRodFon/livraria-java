package com.livraria.service;

import com.livraria.model.Livro;
import org.springframework.ui.Model;

public interface ILivroService {
    String form (Model model);

    String salvar (Livro livro);

    String listar (Model model);

}
