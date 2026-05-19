package com.livraria.impl;

import com.livraria.IMultaService;
import com.livraria.model.Emprestimo;
import com.livraria.model.Multa;
import com.livraria.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultaService implements IMultaService {

    @Autowired
    private MultaRepository multaRepository;

    @Override
    public List<Multa> listarTodas() {
        return multaRepository.findAll();
    }

    @Override
    public Multa salvar(Multa multa, Emprestimo emp) {

        multa.setEmprestimo(emp);

        if (multa.getValor() == null || multa.getValor() <= 0) {
            throw new RuntimeException("Valor da multa deve ser maior que zero");
        }

        return multaRepository.save(multa);
    }

    @Override
    public void gerarMultaAtraso(Emprestimo emprestimo, long diasAtraso) {

        if (diasAtraso <= 0) return;

        boolean jaExiste = multaRepository
                .existsByEmprestimoAndTipo(emprestimo, Multa.Tipo.ATRASO);

        if (jaExiste) return;

        Multa multa = new Multa();
        multa.setEmprestimo(emprestimo);
        multa.setTipo(Multa.Tipo.ATRASO);
        multa.setStatus(Multa.Status.PENDENTE);
        multa.setValor(diasAtraso * 2.0);

        multaRepository.save(multa);
    }

    // ❌ EXCLUIR (somente pago)
    public void deletar(Long id) {

        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Multa não encontrada"));

        if (multa.getStatus() == Multa.Status.PENDENTE) {
            throw new RuntimeException("Não é possível excluir multa pendente");
        }

        multaRepository.deleteById(id);
    }

    // ✅ NOVO: FINALIZAR MULTA (PAGAR)
    public void finalizar(Long id) {

        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Multa não encontrada"));

        if (multa.getStatus() == Multa.Status.PAGO) {
            throw new RuntimeException("Multa já está paga");
        }

        multa.setStatus(Multa.Status.PAGO);

        multaRepository.save(multa);
    }
}