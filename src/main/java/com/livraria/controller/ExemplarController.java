package com.livraria.controller;

import com.livraria.impl.ExemplarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exemplares")
public class ExemplarController {

    private final ExemplarService service;

    public ExemplarController(ExemplarService service) {
        this.service = service;
    }

    @GetMapping("/lista")
    public String listar(Model model) {
        return service.listar(model);
    }

    @GetMapping("/form")
    public String form(Model model) {
        return service.form(model);
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute com.livraria.model.LivroExemplar exemplar,
                         Model model) {
        return service.salvar(exemplar, model);
    }

    // ⭐ DELETE COM TRATAMENTO DE ERRO
    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, Model model) {

        try {
            service.deletar(id);
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return service.listar(model);
        }

        return "redirect:/exemplares/lista";
    }
}