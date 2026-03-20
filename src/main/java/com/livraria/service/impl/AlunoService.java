package com.livraria.service.impl;

import com.livraria.model.Aluno;
import com.livraria.repository.AlunoRepository;
import com.livraria.repository.ResponsavelRepository;
import com.livraria.service.IAlunoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AlunoService implements IAlunoService {

    private final AlunoRepository alunoRepository;
    private final ResponsavelRepository responsavelRepository;

    public AlunoService(AlunoRepository alunoRepository, ResponsavelRepository responsavelRepository) {
        this.alunoRepository = alunoRepository;
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    public String listar(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("alunos", alunoRepository.findAll());
        model.addAttribute("responsaveis", responsavelRepository.findAll());

        return "alunos";
    }

    @Override
    public String salvar(Aluno aluno, Model model) {
        try {
            alunoRepository.save(aluno);
            return "redirect:/alunos";

        } catch (DataIntegrityViolationException e) {

            model.addAttribute("erro", "Já existe um aluno com esse RA!");
            model.addAttribute("aluno", aluno);
            model.addAttribute("alunos", alunoRepository.findAll());

            model.addAttribute("responsaveis", responsavelRepository.findAll());

            return "alunos";
        }
    }

}
