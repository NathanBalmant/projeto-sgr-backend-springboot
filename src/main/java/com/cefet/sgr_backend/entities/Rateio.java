package com.cefet.sgr_backend.entities;

import com.cefet.sgr_backend.enums.SituacaoRateio;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbRateio")
public class Rateio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    @Enumerated(EnumType.STRING)
    private SituacaoRateio situacao;

    @ManyToOne
    @JoinColumn(name = "idMorador", nullable = false)
    private Morador morador;

    @ManyToOne
    @JoinColumn(name = "idConta", nullable = false)
    private Conta conta;

  
    public Rateio() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

   

    public SituacaoRateio getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoRateio situacao) {
        this.situacao = situacao;
    }

    public Morador getMorador() {
        return morador;
    }

    public Conta getConta() {
        return conta;
    }

    // Setters
    public void setIdRateio(Long idRateio) {
        this.id = idRateio;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

   

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
