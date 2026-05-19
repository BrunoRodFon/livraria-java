package com.livraria.controller;

import com.livraria.impl.EmprestimoService;
import com.livraria.impl.AlunoService;
import com.livraria.impl.ExemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ExemplarService exemplarService;

    @GetMapping("/emprestimos")
    public String listarEmprestimos(Model model) {

        model.addAttribute("emprestimos", emprestimoService.listarTodos());
        model.addAttribute("alunos", alunoService.listarTodos());
        model.addAttribute("exemplares", exemplarService.listarTodosDisponiveis());

        return "emprestimos";
    }

    @PostMapping("/emprestimos")
    public String realizarEmprestimo(@RequestParam Long alunoId,
                                     @RequestParam Long exemplarId,
                                     Model model) {
        try {
            emprestimoService.realizarEmprestimo(alunoId, exemplarId);
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return listarEmprestimos(model);
        }
        return "redirect:/emprestimos";
    }

    @PostMapping("/emprestimos/devolver/{id}")
    public String devolver(@PathVariable Long id) {
        emprestimoService.devolver(id);
        return "redirect:/emprestimos";
    }

    @PostMapping("/emprestimos/deletar/{id}")
    public String deletar(@PathVariable Long id, Model model) {
        try {
            emprestimoService.deletar(id);
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return listarEmprestimos(model);
        }
        return "redirect:/emprestimos";
    }
}