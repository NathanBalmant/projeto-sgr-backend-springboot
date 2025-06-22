package com.cefet.sgr_backend.dto;

import java.time.LocalDate;

import com.cefet.sgr_backend.entities.Conta;

public class ContaDTO {
    private Long id;
    private Double valor;
    private LocalDate dataVencimento;
    private Boolean situacao;
    private String observacao;
    private Long idMorador;
    private Long idTipoConta;

    public ContaDTO(){}

    public ContaDTO(Conta conta) {
        this.id = conta.getId();
        this.valor = conta.getValor();
        this.dataVencimento = conta.getDataVencimento();
        this.situacao = conta.getSituacao();
        this.observacao = conta.getObservacao();
        this.idMorador = conta.getMorador().getId();
        this.idTipoConta = conta.getTipoConta().getId();
    }

    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public Long getIdMorador() {
        return idMorador;
    }

    public Long getIdTipoConta() {
        return idTipoConta;
    }

}
