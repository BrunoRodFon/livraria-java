package com.livraria.service;

import com.livraria.model.Multa;
import java.util.List;

public interface IMultaService {

    List<Multa> listarTodas();

    Multa salvar(Multa multa);
}