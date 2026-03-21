package com.livraria.controller;

import com.livraria.model.LivroExemplar;
import com.livraria.model.Livro;
import com.livraria.repository.LivroExemplarRepository;
import com.livraria.repository.LivroRepository;

import com.livraria.service.impl.ExemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/exemplares")
public class ExemplarController {

    private final ExemplarService service;

    public ExemplarController(ExemplarService service) {
        this.service = service;
    }

    @GetMapping("/form")
    public String form(Model model) {
        return service.form(model);

    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute LivroExemplar exemplar, Model model) {
        return service.salvar(exemplar, model);
    }

    @GetMapping("/lista")
    public String listar(Model model) {
        return service.listar(model);
    }
}