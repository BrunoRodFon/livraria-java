package com.livraria.controller;

import com.livraria.repository.AlunoRepository;
import com.livraria.repository.LivroRepository;
import com.livraria.repository.EmprestimoRepository;
import com.livraria.repository.MultaRepository;
import com.livraria.repository.ResponsavelRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class HomeController {

    private final AlunoRepository alunoRepository;
    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final MultaRepository multaRepository;
    private final ResponsavelRepository responsavelRepository; // ← adicione

    public HomeController(
            AlunoRepository alunoRepository,
            LivroRepository livroRepository,
            EmprestimoRepository emprestimoRepository,
            MultaRepository multaRepository,
            ResponsavelRepository responsavelRepository // ← adicione
    ) {
        this.alunoRepository = alunoRepository;
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.multaRepository = multaRepository;
        this.responsavelRepository = responsavelRepository; // ← adicione
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("totalAlunos", alunoRepository.count());
        model.addAttribute("totalLivros", livroRepository.count());
        model.addAttribute("totalEmprestimos", emprestimoRepository.count());
        model.addAttribute("totalMultas", multaRepository.count());
        model.addAttribute("totalResponsaveis", responsavelRepository.count());

        List<String> alunosNomes = alunoRepository.findAll()
                .stream()
                .map(a -> a.getNome())
                .toList();

        List<Integer> alunosEmprestimos = alunoRepository.findAll()
                .stream()
                .map(a -> emprestimoRepository.countByAlunoIdAndAtivoTrue(a.getId()))
                .toList();

        model.addAttribute("alunosNomes", alunosNomes);
        model.addAttribute("alunosEmprestimos", alunosEmprestimos);

        List<String> livrosNomes = livroRepository.findAll()
                .stream()
                .map(l -> l.getTitulo()) // ajuste se seu campo tiver outro nome
                .toList();

        List<Integer> livrosEmprestimos = livroRepository.findAll()
                .stream()
                .map(l -> emprestimoRepository.countByLivroExemplar_Livro_Id(l.getId()))
                .map(Long::intValue)
                .toList();

        model.addAttribute("livrosNomes", livrosNomes);
        model.addAttribute("livrosEmprestimos", livrosEmprestimos);

        List<String> alunosMultas = alunoRepository.findAll()
                .stream()
                .map(a -> a.getNome())
                .toList();

        List<Double> valoresMultas = alunoRepository.findAll()
                .stream()
                .map(a -> multaRepository.findAll()
                        .stream()
                        .filter(m -> m.getEmprestimo().getAluno().getId().equals(a.getId()))
                        .mapToDouble(m -> {
                            // ⚠️ ajuste aqui conforme sua entidade
                            return m.getValor();
                        })
                        .sum()
                )
                .toList();

        model.addAttribute("alunosMultas", alunosMultas);
        model.addAttribute("valoresMultas", valoresMultas);

        return "index";
    }
}