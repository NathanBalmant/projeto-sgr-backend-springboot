package com.cefet.sgr_backend.dto;

import com.cefet.sgr_backend.entities.Morador;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MoradorDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String celular;
    private String email;
    private String contatoFamilia;
    private String login;
    private String senha;

    
    

    private String foto;

    
    public MoradorDTO() {
    }

    public MoradorDTO(Morador morador){
        this.id = morador.getId();
        this.nome = morador.getNome();
        this.cpf = morador.getCpf();
        this.dataNascimento = morador.getDataNascimento().toString();
        this.celular = morador.getCelular();
        this.email = morador.getEmail();
        this.contatoFamilia = morador.getContatoFamilia();
        this.login = morador.getLogin();
        this.foto = morador.getFoto();
        
    }
    
    public Long getId() {
        return id;
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

    public String getLogin() {
        return login;
    }

    public String getFoto() {
        return foto;
    }

   
    public String getSenha() {
        return senha;
    }

    
}