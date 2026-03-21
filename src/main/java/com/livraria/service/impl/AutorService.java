package com.livraria.service.impl;

import com.livraria.model.Autor;
import com.livraria.repository.AutorRepository;
import com.livraria.service.IAutorService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AutorService implements IAutorService{

    private final AutorRepository autorRepo;

    public AutorService(AutorRepository autorRepo) {
        this.autorRepo = autorRepo;
    }

    @Override
    public String form(Model model) {
        model.addAttribute("autor", new Autor());
        return "autores/form";
    }

    @Override
    public String salvar(Autor autor) {
        autorRepo.save(autor);
        return "redirect:/livros/form";
    }
}
