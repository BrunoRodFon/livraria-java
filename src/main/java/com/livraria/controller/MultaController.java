package com.livraria.controller;

import com.livraria.model.Multa;
import com.livraria.repository.EmprestimoRepository;
import com.livraria.service.impl.MultaService;
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
        model.addAttribute("multa", new Multa());
        model.addAttribute("emprestimos", emprestimoRepository.findAll());

        return "multas";
    }

    @PostMapping("/multas")
    public String salvarMulta(@ModelAttribute Multa multa) {
        multaService.salvar(multa);
        return "redirect:/multas";
    }
}