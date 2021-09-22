package com.meli.desafiospringboot2209.model;

public class Pessoa {
    public String cpf;
    public String nome;
    public String sobrenome;
    public String dataNascimento;

    public Pessoa(String cpf, String nome, String sobrenome, String dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Pessoa{cpf='" + cpf + ", nome=" + nome + ", sobrenome=" + sobrenome + ", dataNascimento=" + dataNascimento +  "}";
    }

}