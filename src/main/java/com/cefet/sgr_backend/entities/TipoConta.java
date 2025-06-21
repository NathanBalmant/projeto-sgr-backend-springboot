package com.cefet.sgr_backend.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbTipo")
public class TipoConta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipo") 
    private Long id;

    @Column(name = "name", nullable = false)
    private String nome;

    public TipoConta() {}

    public TipoConta(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoConta)) return false;
        TipoConta tipoConta = (TipoConta) o;
        return Objects.equals(id, tipoConta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}