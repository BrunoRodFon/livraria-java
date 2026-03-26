package com.livraria.repository;

import com.livraria.model.LivroExemplar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroExemplarRepository extends JpaRepository<LivroExemplar, Long> {

    // Agora recebe Integer em vez de String
    Optional<LivroExemplar> findByCod(Integer cod);

    List<LivroExemplar> findByDisponivelTrue();
}