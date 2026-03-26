package com.livraria.service.impl;

import com.livraria.model.Multa;
import com.livraria.repository.MultaRepository;
import com.livraria.service.IMultaService;
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
    public Multa salvar(Multa multa) {
        return multaRepository.save(multa);
    }
}