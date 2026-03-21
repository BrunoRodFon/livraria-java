package com.livraria.service;

import com.livraria.model.Responsavel;
import org.springframework.ui.Model;

public interface IResponsavelService {
    String listar(Model model);

    String salvar(Responsavel responsavel, Model model);
}
