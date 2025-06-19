package com.cefet.sgr_backend.entities;

import jakarta.persistence.*;
import java.util.Objects;

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
    private String cpf;

    @Column(name = "data_nascimento")
    private String dataNascimento;

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

    public Morador() {}

    public Morador(Long id, String nome, String cpf, String dataNascimento, String celular,
                   String email, String contatoFamilia, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.celular = celular;
        this.email = email;
        this.contatoFamilia = contatoFamilia;
        this.login = login;
        this.senha = senha;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
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
