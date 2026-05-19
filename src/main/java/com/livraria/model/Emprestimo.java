package com.livraria.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dt_emprestimo")
    private LocalDate dtEmprestimo;

    @Column(name = "dt_prevista")
    private LocalDate dtPrevista;

    @Column(name = "dt_devolucao")
    private LocalDate dtDevolucao;

    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "idLivroExemplar")
    private LivroExemplar livroExemplar;

    @ManyToOne
    @JoinColumn(name = "idAluno")
    private Aluno aluno;

    // getters e setters

    public Long getId() {
        return id;
    }


    public LocalDate getDtEmprestimo() {
        return dtEmprestimo;
    }

    public void setDtEmprestimo(LocalDate dtEmprestimo) {
        this.dtEmprestimo = dtEmprestimo;
    }

    public LocalDate getDtPrevista() {
        return dtPrevista;
    }

    public void setDtPrevista(LocalDate dtPrevista) {
        this.dtPrevista = dtPrevista;
    }

    public LocalDate getDtDevolucao() {
        return dtDevolucao;
    }

    public void setDtDevolucao(LocalDate dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
    }

    public LivroExemplar getLivroExemplar() {
        return livroExemplar;
    }

    public void setLivroExemplar(LivroExemplar livroExemplar) {
        this.livroExemplar = livroExemplar;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}