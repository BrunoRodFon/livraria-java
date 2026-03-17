package com.livraria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livro_exemplares")
public class LivroExemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cod;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "idLivro")
    private Livro livro;

    public enum Status {
        Disponivel,
        Emprestado
    }

    // getters e setters
}