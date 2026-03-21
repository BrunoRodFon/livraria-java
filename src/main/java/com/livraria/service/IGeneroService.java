package com.livraria.service;

import com.livraria.model.Genero;
import org.springframework.ui.Model;

public interface IGeneroService {

    String form (Model model);
    String salvar (Genero genero);

}
