package com.livraria.controller;

import com.livraria.model.Aluno;
import com.livraria.repository.AlunoRepository;
import com.livraria.repository.ResponsavelRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final ResponsavelRepository responsavelRepository;

    public AlunoController(AlunoRepository alunoRepository,
                           ResponsavelRepository responsavelRepository) {
        this.alunoRepository = alunoRepository;
        this.responsavelRepository = responsavelRepository;
    }

    @GetMapping("/alunos")
    public String listar(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("alunos", alunoRepository.findAll());

        // 🔥 ESSENCIAL
        model.addAttribute("responsaveis", responsavelRepository.findAll());

        return "alunos";
    }

    @PostMapping("/alunos")
    public String salvar(@ModelAttribute Aluno aluno, Model model) {
        try {
            alunoRepository.save(aluno);
            return "redirect:/alunos";

        } catch (DataIntegrityViolationException e) {

            model.addAttribute("erro", "Já existe um aluno com esse RA!");
            model.addAttribute("aluno", aluno);
            model.addAttribute("alunos", alunoRepository.findAll());

            // 🔥 CORRIGIDO
            model.addAttribute("responsaveis", responsavelRepository.findAll());

            return "alunos";
        }
    }
}