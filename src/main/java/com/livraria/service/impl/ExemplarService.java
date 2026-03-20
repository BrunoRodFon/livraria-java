package com.livraria.service.impl;

import com.livraria.model.LivroExemplar;
import com.livraria.repository.LivroExemplarRepository;
import com.livraria.repository.LivroRepository;
import com.livraria.service.IExemplarService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ExemplarService implements IExemplarService {

    private final LivroRepository livroRepo;

    private final LivroExemplarRepository exemplarRepo;

    public ExemplarService(LivroRepository livroRepo, LivroExemplarRepository exemplarRepo) {
        this.livroRepo = livroRepo;
        this.exemplarRepo = exemplarRepo;
    }

    @Override
    public String form(Model model) {
        model.addAttribute("exemplar", new LivroExemplar());
        model.addAttribute("livros", livroRepo.findAll());
        return "exemplares/form";
    }

    @Override
    public String salvar(LivroExemplar exemplar, Model model) {
        if (exemplarRepo.findByCod(exemplar.getCod()).isPresent()) {
            model.addAttribute("erro", "Já existe um exemplar com esse código!");
            model.addAttribute("livros", livroRepo.findAll());
            model.addAttribute("exemplar", exemplar); // importante! mantém os dados preenchidos
            return "exemplares/form";
        }

        exemplarRepo.save(exemplar);
        return "redirect:/exemplares/lista";
    }

    @Override
    public String listar(Model model) {
        List<LivroExemplar> exemplares = exemplarRepo.findAll();
        model.addAttribute("exemplares", exemplares);
        return "exemplares/lista";
    }
}
