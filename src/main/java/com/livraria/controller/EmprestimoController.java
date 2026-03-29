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

    // 📌 LISTAR EMPRÉSTIMOS
    @GetMapping("/emprestimos")
    public String listarEmprestimos(Model model) {

        model.addAttribute("emprestimos", emprestimoService.listarTodos());

        // 🔥 OK (já estava correto)
        model.addAttribute("alunos", alunoService.listarTodos());
        model.addAttribute("exemplares", exemplarService.listarTodosDisponiveis());

        return "emprestimos";
    }

    // 📌 REALIZAR EMPRÉSTIMO
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

    // 🔥 NOVO: DEVOLUÇÃO (FALTAVA)
    @PostMapping("/emprestimos/devolver/{id}")
    public String devolver(@PathVariable Long id) {

        emprestimoService.devolver(id);

        return "redirect:/emprestimos";
    }
}