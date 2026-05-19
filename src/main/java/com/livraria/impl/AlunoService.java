package com.livraria.impl;

import com.livraria.model.Aluno;
import com.livraria.repository.AlunoRepository;
import com.livraria.repository.EmprestimoRepository;
import com.livraria.IAlunoService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AlunoService implements IAlunoService {

    private final AlunoRepository alunoRepository;
    private final EmprestimoRepository emprestimoRepository;

    public AlunoService(AlunoRepository alunoRepository,
                        EmprestimoRepository emprestimoRepository) {
        this.alunoRepository = alunoRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    @Override
    public String listar(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("alunos", alunoRepository.findAll());
        return "alunos";
    }

    @Override
    public String salvar(Aluno aluno, Model model) {
        alunoRepository.save(aluno);
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