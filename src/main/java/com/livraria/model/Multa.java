package com.livraria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "multas")
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private Double valor;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "idEmprestimo")
    private Emprestimo emprestimo;

    public enum Tipo {
        Dano,
        atraso
    }

    public enum Status {
        Pago,
        Pendente
    }

    // getters e setters
}