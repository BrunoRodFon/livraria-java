package com.livraria.controller;

import com.livraria.repository.AlunoRepository;
import com.livraria.repository.LivroRepository;
import com.livraria.repository.EmprestimoRepository;
import com.livraria.repository.MultaRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AlunoRepository alunoRepository;
    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final MultaRepository multaRepository;

    public HomeController(
            AlunoRepository alunoRepository,
            LivroRepository livroRepository,
            EmprestimoRepository emprestimoRepository,
            MultaRepository multaRepository
    ) {
        this.alunoRepository = alunoRepository;
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.multaRepository = multaRepository;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("totalAlunos",
                alunoRepository.count() != 0 ? alunoRepository.count() : 0);

        model.addAttribute("totalLivros",
                livroRepository.count() != 0 ? livroRepository.count() : 0);

        model.addAttribute("totalEmprestimos",
                emprestimoRepository.count() != 0 ? emprestimoRepository.count() : 0);

        model.addAttribute("totalMultas",
                multaRepository.count() != 0 ? multaRepository.count() : 0);

        return "index";
    }
}