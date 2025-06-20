package com.cefet.sgr_backend.entities;

import java.time.LocalDate;
import java.util.Objects;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Fiz só a classe Morador, mas se precisar de mais alguma coisa coloque ou edite
@Entity
@Table(name = "tb_morador")
public class Morador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf", unique = true, nullable = false)
    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números")
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "celular")
    private String celular;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "contato_familia")
    private String contatoFamilia;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "senha")
    private String senha;

    private String foto;

    public Morador() {}

    public Morador(Long id, String nome, String cpf, LocalDate dataNascimento, String celular,
                   String email, String contatoFamilia, String login, String senha, String foto) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.celular = celular;
        this.email = email;
        this.contatoFamilia = contatoFamilia;
        this.login = login;
        this.senha = senha;
        this.foto = foto;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContatoFamilia() {
        return contatoFamilia;
    }

    public void setContatoFamilia(String contatoFamilia) {
        this.contatoFamilia = contatoFamilia;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Morador)) return false;
        Morador morador = (Morador) o;
        return Objects.equals(id, morador.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
