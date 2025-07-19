package com.cefet.sgr_backend.dto;

public class GastoPorTipoDTO {
    private String tipo;
    private Double total;

    public GastoPorTipoDTO(String tipo, Double total) {
        this.tipo = tipo;
        this.total = total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}