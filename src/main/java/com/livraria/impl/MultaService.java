package com.livraria.impl;

import com.livraria.model.Emprestimo;
import com.livraria.model.Multa;
import com.livraria.repository.MultaRepository;
import com.livraria.IMultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultaService implements IMultaService {

    @Autowired
    private MultaRepository multaRepository;

    // 📌 LISTAR
    @Override
    public List<Multa> listarTodas() {
        return multaRepository.findAll();
    }

    // 📌 SALVAR MANUAL (se precisar no futuro)
    @Override
    public Multa salvar(Multa multa, Emprestimo emp) {

        // 🔥 GARANTE RELACIONAMENTO CORRETO
        multa.setEmprestimo(emp);

        // 🔥 VALIDAÇÃO BÁSICA
        if (multa.getValor() <= 0) {
            throw new RuntimeException("Valor da multa deve ser maior que zero");
        }

        return multaRepository.save(multa);
    }

    // 📌 GERAR MULTA AUTOMÁTICA (ATRASO)
    public void gerarMultaAtraso(Emprestimo emprestimo, long diasAtraso) {

        // 🔥 VALIDAÇÃO 1: só gera se houver atraso
        if (diasAtraso <= 0) {
            return; // não faz nada
        }

        // 🔥 VALIDAÇÃO 2: evita multa duplicada
        boolean jaExiste = multaRepository
                .existsByEmprestimoAndTipo(emprestimo, Multa.Tipo.ATRASO);

        if (jaExiste) {
            return; // já existe multa para esse empréstimo
        }

        // 🔥 CRIA MULTA
        Multa multa = new Multa();

        multa.setEmprestimo(emprestimo); // ✔ CORRETO
        multa.setTipo(Multa.Tipo.ATRASO);
        multa.setStatus(Multa.Status.PENDENTE);

        // 💰 REGRA DE NEGÓCIO CENTRALIZADA
        double valorPorDia = 2.0;
        double valorTotal = diasAtraso * valorPorDia;

        multa.setValor(valorTotal);

        multaRepository.save(multa);
    }
}