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

    @GetMapping("/multas")
    public String listarMultas(Model model) {

        model.addAttribute("multas", multaService.listarTodas());
        model.addAttribute("emprestimos", emprestimoRepository.findAll());
        model.addAttribute("multa", new Multa());

        return "multas";
    }

    @PostMapping("/multas")
    public String salvarMulta(Multa multa,
                              @RequestParam Long emprestimoId,
                              Model model) {

        try {
            Emprestimo emp = emprestimoRepository.findById(emprestimoId)
                    .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

            multaService.salvar(multa, emp);

        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return listarMultas(model);
        }

        return "redirect:/multas";
    }

    @PostMapping("/multas/deletar/{id}")
    public String deletar(@PathVariable Long id, Model model) {

        try {
            multaService.deletar(id);
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return listarMultas(model);
        }

        return "redirect:/multas";
    }

    // ✅ NOVO: FINALIZAR MULTA
    @PostMapping("/multas/finalizar/{id}")
    public String finalizar(@PathVariable Long id, Model model) {

        try {
            multaService.finalizar(id);
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return listarMultas(model);
        }

        return "redirect:/multas";
    }
}