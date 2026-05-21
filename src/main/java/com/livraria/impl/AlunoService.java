package com.livraria.impl;

import com.livraria.IAlunoService;
import com.livraria.model.Aluno;
import com.livraria.repository.AlunoRepository;
import com.livraria.repository.EmprestimoRepository;
import com.livraria.repository.ResponsavelRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AlunoService implements IAlunoService {

    private final AlunoRepository alunoRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final ResponsavelRepository responsavelRepository;

    public AlunoService(AlunoRepository alunoRepository,
                        EmprestimoRepository emprestimoRepository,
                        ResponsavelRepository responsavelRepository) {

        this.alunoRepository = alunoRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    public String listar(Model model) {

        model.addAttribute("aluno", new Aluno());

        model.addAttribute("alunos", alunoRepository.findAll());

        // 🔥 ISSO FALTAVA
        model.addAttribute("responsaveis", responsavelRepository.findAll());

        return "alunos";
    }

    @Override
    public String salvar(Aluno aluno, Model model) {

        try {

            alunoRepository.save(aluno);

        } catch (RuntimeException e) {

            model.addAttribute("erro", e.getMessage());

            model.addAttribute("aluno", aluno);

            model.addAttribute("alunos", alunoRepository.findAll());

            // 🔥 IMPORTANTE
            model.addAttribute("responsaveis", responsavelRepository.findAll());

            return "alunos";
        }

        return "redirect:/alunos";
    }

    @Override
    public void deletarAluno(Long id) {

        long emprestimosAtivos =
                emprestimoRepository.countByAlunoIdAndAtivoTrue(id);

        if (emprestimosAtivos > 0) {
            throw new RuntimeException("Aluno possui empréstimos ativos");
        }

        alunoRepository.deleteById(id);
    }

    @Override
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }
}