package com.livraria.controller;

import com.livraria.model.Emprestimo;
import com.livraria.service.impl.EmprestimoService;
import com.livraria.service.impl.AlunoService;
import com.livraria.service.impl.ExemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        List<Emprestimo> emprestimos = emprestimoService.listarTodos();
        model.addAttribute("emprestimos", emprestimos);

        // dados para o formulário
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
            return listarEmprestimos(model); // recarrega a página com erro
        }
        return "redirect:/emprestimos";
    }
}
