package com.livraria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Titulo;
    private String Descricao;

    @ManyToOne
    @JoinColumn(name = "idAutor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "idGenero")
    private Genero genero;

    // getters e setters
}