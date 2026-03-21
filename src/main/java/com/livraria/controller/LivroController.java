package com.livraria.controller;

import com.livraria.model.Livro;
import com.livraria.model.Autor;
import com.livraria.model.Genero;
import com.livraria.repository.LivroRepository;
import com.livraria.repository.AutorRepository;
import com.livraria.repository.GeneroRepository;

import com.livraria.service.impl.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping("/form")
    public String form(Model model) {
        return service.form(model);
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Livro livro) {
        return service.salvar(livro);
    }

    @GetMapping("/lista")
    public String listar(Model model) {
        return service.listar(model);
    }
}