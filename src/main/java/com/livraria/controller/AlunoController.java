package com.livraria.controller;

import com.livraria.impl.AlunoService;
import com.livraria.model.Aluno;
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

    @PostMapping("/alunos/deletar/{id}")
    public String deletar(@PathVariable Long id, Model model) {

        try {
            service.deletarAluno(id);
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return service.listar(model);
        }

        return "redirect:/alunos";
    }
}