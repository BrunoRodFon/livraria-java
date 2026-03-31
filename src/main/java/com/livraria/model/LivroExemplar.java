package com.livraria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "livro_exemplares")
public class LivroExemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cod;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "idLivro")
    private Livro livro;

    // ❌ REMOVIDO -> campo redundante
    // private Boolean disponivel;


    //Criar um Enum com label customizado
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

    /* ✔ METODO AUXILIAR (PROFISSIONAL)*/
    public boolean isDisponivel() {
        return this.status == Status.DISPONIVEL;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getCod() { return cod; }
    public void setCod(Integer cod) { this.cod = cod; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }
}