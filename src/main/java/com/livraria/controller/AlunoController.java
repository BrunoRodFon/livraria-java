package com.livraria.controller;

import com.livraria.model.Aluno;
import com.livraria.service.impl.AlunoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping("/alunos")
    public String listar(Model model) {
        return service.listar(model);
    }

    @PostMapping("/alunos")
    public String salvar(@ModelAttribute Aluno aluno, Model model) {
        return service.salvar(aluno, model);
    }

}