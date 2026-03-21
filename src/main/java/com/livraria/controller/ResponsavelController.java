package com.livraria.controller;

import com.livraria.model.Responsavel;
import com.livraria.repository.ResponsavelRepository;
import com.livraria.service.impl.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}