package com.livraria.controller;

import com.livraria.model.Emprestimo;
import com.livraria.model.Multa;
import com.livraria.repository.EmprestimoRepository;
import com.livraria.impl.MultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MultaController {

    @Autowired
    private MultaService multaService;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    // 🔥 ADICIONAR GET (faltava isso)
    // Sem isso você não consegue abrir a tela de multas
    @GetMapping("/multas")
    public String listarMultas(Model model) {
        model.addAttribute("multas", multaService.listarTodas());

        // 🔥 NECESSÁRIO PARA O SELECT
        model.addAttribute("emprestimos", emprestimoRepository.findAll());

        model.addAttribute("multa", new Multa()); // 🔥 evita erro no form

        return "multas";
    }

    @PostMapping("/multas")
    public String salvarMulta(Multa multa, @RequestParam Long emprestimoId, Model model) {

        try {
            // 🔥 BUSCA O EMPRÉSTIMO REAL
            Emprestimo emp = emprestimoRepository.findById(emprestimoId)
                    .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

            multaService.salvar(multa, emp);

        } catch (RuntimeException e) {

            // 🔥 TRATAMENTO DE ERRO (faltava isso)
            model.addAttribute("erro", e.getMessage());
            return listarMultas(model); // recarrega a tela com erro
        }

        return "redirect:/multas";
    }
}