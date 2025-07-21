package com.cefet.sgr_backend.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects; // Importar List

import com.cefet.sgr_backend.enums.SituacaoConta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany; 
import jakarta.persistence.Table; 


@Entity
@Table(name = "tb_conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    private LocalDate dataVencimento;

    @Enumerated(EnumType.STRING)
    private SituacaoConta situacao;

    @ManyToOne
    @JoinColumn(name = "idMorador")
    private Morador morador;

    @ManyToOne
    @JoinColumn(name = "idTipoConta")
    private TipoConta tipoConta;

    private String observacao;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rateio> rateios; 

    public Conta() {}

    public Conta(Long id, Double valor, LocalDate dataVencimento, SituacaoConta situacao, Morador morador,
            TipoConta tipoConta, String observacao) {
        this.id = id;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.situacao = situacao;
        this.morador = morador;
        this.tipoConta = tipoConta;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public SituacaoConta getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoConta situacao) {
        this.situacao = situacao;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Rateio> getRateios() {
        return rateios;
    }

    public void setRateios(List<Rateio> rateios) {
        this.rateios = rateios;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Conta other = (Conta) obj;
        return Objects.equals(id, other.id);
    }
}
