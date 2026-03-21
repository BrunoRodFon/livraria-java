package com.livraria.service.impl;

import com.livraria.model.Autor;
import com.livraria.model.Genero;
import com.livraria.model.Livro;
import com.livraria.repository.AutorRepository;
import com.livraria.repository.GeneroRepository;
import com.livraria.repository.LivroRepository;
import com.livraria.service.ILivroService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LivroService implements ILivroService {

    private final LivroRepository livroRepo;

    private final AutorRepository autorRepo;

    private final GeneroRepository generoRepo;

    public LivroService(LivroRepository livroRepo, AutorRepository autorRepo, GeneroRepository generoRepo) {
        this.livroRepo = livroRepo;
        this.autorRepo = autorRepo;
        this.generoRepo = generoRepo;
    }

    @Override
    public String form(Model model) {
        model.addAttribute("livro", new Livro());
        model.addAttribute("autores", autorRepo.findAll());
        model.addAttribute("generos", generoRepo.findAll());
        return "livros/form";
    }

    @Override
    public String salvar(Livro livro) {
        if (livro.getAutor() != null && livro.getAutor().getId() != null) {
            Autor autor = autorRepo.findById(livro.getAutor().getId()).orElse(null);
            livro.setAutor(autor);
        }

        if (livro.getGenero() != null && livro.getGenero().getId() != null) {
            Genero genero = generoRepo.findById(livro.getGenero().getId()).orElse(null);
            livro.setGenero(genero);
        }

        livroRepo.save(livro);
        return "redirect:/livros/lista";
    }

    @Override
    public String listar(Model model) {
        model.addAttribute("livros", livroRepo.findAll());
        return "livros/lista";
    }
}
