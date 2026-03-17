package com.livraria.controller;

import com.livraria.model.Responsavel;
import com.livraria.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResponsavelController {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @GetMapping("/responsaveis")
    public String listar(Model model) {
        model.addAttribute("responsavel", new Responsavel());
        model.addAttribute("responsaveis", responsavelRepository.findAll());
        return "responsaveis";
    }

    @PostMapping("/responsaveis")
    public String salvar(@ModelAttribute Responsavel responsavel, Model model) {

        try {
            responsavelRepository.save(responsavel);
            return "redirect:/responsaveis";

        } catch (DataIntegrityViolationException e) {

            model.addAttribute("erro", "CPF já cadastrado!");
            model.addAttribute("responsaveis", responsavelRepository.findAll());
            model.addAttribute("responsavel", responsavel);

            return "responsaveis";
        }
    }
}