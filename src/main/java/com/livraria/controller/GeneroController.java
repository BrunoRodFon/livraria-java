package com.livraria.controller;

import com.livraria.model.Genero;
import com.livraria.repository.GeneroRepository;
import com.livraria.service.impl.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/generos")
public class GeneroController {

    private final GeneroService service;

    public GeneroController(GeneroService service) {
        this.service = service;
    }

    @GetMapping("/form")
    public String form(Model model){
        return service.form(model);
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Genero genero){
        return service.salvar(genero);
    }
}