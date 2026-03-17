package com.livraria.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dt_emprestimo;
    private LocalDate dt_prevista;
    private LocalDate dt_devolucao;

    @ManyToOne
    @JoinColumn(name = "idLivroExemplar")
    private LivroExemplar livroExemplar;

    @ManyToOne
    @JoinColumn(name = "idAluno")
    private Aluno aluno;

    // getters e setters
}