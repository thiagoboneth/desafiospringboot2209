package com.meli.desafiospringboot2209.entity;

public class Veterinario {

    private String cpf;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    public Integer idMedico;
    public String numeroRegistro;
    public String especialidade;

    public Veterinario() {

    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    //Interfaces fluentes
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

    public Veterinario dataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public Veterinario comIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
        return this;
    }

    public Veterinario comNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
        return this;
    }

    public Veterinario comEspecialidade(String especialidade) {
        this.especialidade = especialidade;
        return this;
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", idMedico=" + idMedico +
                ", numeroRegistro='" + numeroRegistro + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }
}
