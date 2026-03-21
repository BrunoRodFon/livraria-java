package com.livraria.controller;

import com.livraria.model.Autor;
import com.livraria.repository.AutorRepository;
import com.livraria.service.impl.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorService service;

    @Autowired
    private AutorRepository autorRepo;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @GetMapping("/form")
    public String form(Model model){
        return service.form(model);
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Autor autor){
        return service.salvar(autor);
    }
}