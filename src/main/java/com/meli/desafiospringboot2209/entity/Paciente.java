package com.meli.desafiospringboot2209.entity;

public class Paciente {

    private String especie;
    private String raca;
    private String cor;
    private String dataNascimento;
    private String nome;
    private String sexo;
    private String cpfProprietario;
    private String numeroColeira;

    public Paciente comEspecie(String especie) {
        this.especie = especie;
        return this;
    }

    public Paciente comRaca(String raca) {
        this.raca = raca;
        return this;
    }

    public Paciente comCor(String cor) {
        this.cor = cor;
        return this;
    }

    public Paciente comDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public Paciente comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Paciente comSexo(String sexo) {
        this.sexo = sexo;
        return this;
    }

    public Paciente comCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
        return this;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public String getCor() {
        return cor;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public String getNumeroColeira() {
        return numeroColeira;
    }
}
