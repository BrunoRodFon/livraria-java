package com.livraria.controller;

import com.livraria.model.Responsavel;
import com.livraria.impl.ResponsavelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResponsavelController {

    private final ResponsavelService service;

    public ResponsavelController(ResponsavelService service) {
        this.service = service;
    }

    @GetMapping("/responsaveis")
    public String listar(Model model) {
        return service.listar(model);
    }

    @PostMapping("/responsaveis")
    public String salvar(@ModelAttribute Responsavel responsavel, Model model) {
        return service.salvar(responsavel, model);
    }

    @PostMapping("/responsaveis/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            service.deletar(id);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }

        return "redirect:/responsaveis";
    }
}