package com.livraria.service.impl;

import com.livraria.model.Responsavel;
import com.livraria.repository.ResponsavelRepository;
import com.livraria.service.IResponsavelService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ResponsavelService implements IResponsavelService {

    private final ResponsavelRepository responsavelRepository;

    public ResponsavelService(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    public String listar(Model model) {
        model.addAttribute("responsavel", new Responsavel());
        model.addAttribute("responsaveis", responsavelRepository.findAll());
        return "responsaveis";
    }

    @Override
    public String salvar(Responsavel responsavel, Model model) {
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
