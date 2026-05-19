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
        DISPONIVEL("Disponível"),
        EMPRESTADO("Emprestado");

        private String descricao;

        Status(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    public boolean isDisponivel() {
        return this.status == Status.DISPONIVEL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}