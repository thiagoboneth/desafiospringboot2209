package com.meli.desafiospringboot2209.entity;

public class Veterinario {

    private String cpf;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String numeroRegistro;
    private String especialidade;

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public Veterinario comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Veterinario comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Veterinario comSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public Veterinario comDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public Veterinario comEspecialidade(String especialidade) {
        this.especialidade = especialidade;
        return this;
    }
}
