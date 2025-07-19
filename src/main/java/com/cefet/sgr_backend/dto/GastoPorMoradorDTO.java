package com.cefet.sgr_backend.dto;

public class GastoPorMoradorDTO {
    private String moradorNome;
    private Double total;

    public GastoPorMoradorDTO(String moradorNome, Double total) {
        this.moradorNome = moradorNome;
        this.total = total;
    }

    public String getMoradorNome() {
        return moradorNome;
    }

    public void setMoradorNome(String moradorNome) {
        this.moradorNome = moradorNome;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}