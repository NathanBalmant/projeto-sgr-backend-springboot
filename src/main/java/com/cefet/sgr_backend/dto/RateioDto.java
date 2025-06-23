package com.cefet.sgr_backend.dto;

import com.cefet.sgr_backend.entities.Rateio;
import com.cefet.sgr_backend.enums.SituacaoRateio;

public class RateioDto {

    private Long id;
    private Double valor;
    private SituacaoRateio situacao;
    private Long idMorador;
    private Long idConta;

    public RateioDto() {
    }

    public RateioDto(Rateio rateio) {
        this.id = rateio.getId();
        this.valor = rateio.getValor();
        this.situacao = rateio.getSituacao();
        this.idMorador = rateio.getMorador().getId();
        this.idConta = rateio.getConta().getId();
    }

    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public SituacaoRateio getSituacao() {
        return situacao;
    }

    public Long getIdMorador() {
        return idMorador;
    }

    public Long getIdConta() {
        return idConta;
    }
}
