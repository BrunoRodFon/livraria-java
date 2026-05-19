package com.livraria.impl;

import com.livraria.model.LivroExemplar;
import com.livraria.repository.EmprestimoRepository;
import com.livraria.repository.LivroExemplarRepository;
import com.livraria.repository.LivroRepository;
import com.livraria.IExemplarService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ExemplarService implements IExemplarService {

    private final LivroRepository livroRepo;
    private final LivroExemplarRepository exemplarRepo;
    private final EmprestimoRepository emprestimoRepo;

    public ExemplarService(LivroRepository livroRepo,
                           LivroExemplarRepository exemplarRepo,
                           EmprestimoRepository emprestimoRepo) {
        this.livroRepo = livroRepo;
        this.exemplarRepo = exemplarRepo;
        this.emprestimoRepo = emprestimoRepo;
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
            model.addAttribute("exemplar", exemplar);
            return "exemplares/form";
        }

        exemplar.setStatus(LivroExemplar.Status.DISPONIVEL);
        exemplarRepo.save(exemplar);

        return "redirect:/exemplares/lista";
    }

    @Override
    public String listar(Model model) {
        model.addAttribute("exemplares", exemplarRepo.findAll());
        return "exemplares/lista";
    }

    @Override
    public List<LivroExemplar> listarTodosDisponiveis() {
        return exemplarRepo.findByStatus(LivroExemplar.Status.DISPONIVEL);
    }

    // ⭐ DELETE COM REGRA DE NEGÓCIO
    public void deletar(Long id) {

        LivroExemplar ex = exemplarRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Exemplar não encontrado"));

        long ativos = emprestimoRepo.countByLivroExemplarIdAndAtivoTrue(id);

        if (ativos > 0) {
            throw new RuntimeException("Exemplar possui empréstimos ativos");
        }

        exemplarRepo.delete(ex);
    }
}