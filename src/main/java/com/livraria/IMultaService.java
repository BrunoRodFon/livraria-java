package com.livraria;

import com.livraria.model.Emprestimo;
import com.livraria.model.Multa;
import java.util.List;

public interface IMultaService {

    List<Multa> listarTodas();

    // 🔥 ALTERADO: agora recebe o emprestimo
    //Multa salvar(Multa multa, Emprestimo emp);

    // 📌 SALVAR MANUAL (se precisar no futuro)
    Multa salvar(Multa multa, Emprestimo emp);

    // 🔥 REMOVER o metodo salvar da interface
    //Multa NÃO deveria ser criada manualmente — é regra do sistema
    void gerarMultaAtraso(Emprestimo emprestimo, long diasAtraso);
}