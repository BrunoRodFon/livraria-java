package com.livraria;

import com.livraria.model.Autor;
import org.springframework.ui.Model;

public interface IAutorService {
    String form(Model model);
    String salvar(Autor autor);
}
