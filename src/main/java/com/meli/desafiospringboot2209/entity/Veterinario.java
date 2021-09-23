package com.meli.desafiospringboot2209.entity;

public class Veterinario {

    private String cpf;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    public static Integer idMedico;
    private String numeroRegistro;
    private String especialidade;


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

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public static Integer getIdMedico() {
        return idMedico;
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

    public Veterinario comDataNascimento(String dataNascimento) {
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
        return   getCpf() + ';' +  getNome() + ';' + getSobrenome() + ';' + getDataNascimento() + ';' +getIdMedico() +";" + getNumeroRegistro() + ';'+ getEspecialidade();
    }
}
