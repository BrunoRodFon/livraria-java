package com.livraria.controller;

import com.livraria.repository.AlunoRepository;
import com.livraria.repository.LivroRepository;
import com.livraria.repository.EmprestimoRepository;
import com.livraria.repository.MultaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private MultaRepository multaRepository;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("totalAlunos", alunoRepository.count());
        model.addAttribute("totalLivros", livroRepository.count());
        model.addAttribute("totalEmprestimos", emprestimoRepository.count());
        model.addAttribute("totalMultas", multaRepository.count());

        return "index";
    }
}