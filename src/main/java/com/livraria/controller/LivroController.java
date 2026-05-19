package com.livraria.controller;

import com.livraria.model.Livro;
import com.livraria.impl.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, Model model) {
        return service.deletar(id, model);
    }
}