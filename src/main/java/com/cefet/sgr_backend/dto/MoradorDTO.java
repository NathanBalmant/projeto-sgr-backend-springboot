package com.cefet.sgr_backend.dto;

import com.cefet.sgr_backend.entities.Morador;

public class MoradorDTO {

    private String nome;
    private String cpf;
    private String dataNascimento;
    private String celular;
    private String email;
    private String contatoFamilia;
    private String foto;

    
    public MoradorDTO() {
    }

    public MoradorDTO(Morador morador){
        this.nome = morador.getNome();
        this.cpf = morador.getCpf();
        this.dataNascimento = morador.getDataNascimento().toString();
        this.celular = morador.getCelular();
        this.email = morador.getEmail();
        this.contatoFamilia = morador.getContatoFamilia();
        this.foto = morador.getFoto();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getContatoFamilia() {
        return contatoFamilia;
    }

    public String getFoto() {
        return foto;
    }


    
}