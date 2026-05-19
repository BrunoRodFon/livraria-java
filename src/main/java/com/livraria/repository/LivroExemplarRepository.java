package com.livraria.repository;

import com.livraria.model.LivroExemplar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroExemplarRepository extends JpaRepository<LivroExemplar, Long> {

    Optional<LivroExemplar> findByCod(Integer cod);

    List<LivroExemplar> findByStatus(LivroExemplar.Status status);

    long countByLivroId(Long livroId);
}