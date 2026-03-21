package com.livraria.service.impl;

import com.livraria.model.Genero;
import com.livraria.repository.GeneroRepository;
import com.livraria.service.IGeneroService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GeneroService implements IGeneroService {

    private final GeneroRepository generoRepo;

    public GeneroService(GeneroRepository generoRepo) {
        this.generoRepo = generoRepo;
    }

    @Override
    public String form(Model model) {
        model.addAttribute("genero", new Genero());
        return "generos/form";
    }

    @Override
    public String salvar(Genero genero) {
        generoRepo.save(genero);
        return "redirect:/livros/form";
    }
}
