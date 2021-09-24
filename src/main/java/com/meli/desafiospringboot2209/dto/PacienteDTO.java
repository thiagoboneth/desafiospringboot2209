package com.meli.desafiospringboot2209.dto;

public class PacienteDTO {

    private String especie;
    private String raca;
    private String cor;
    private String dataNascimento;
    private String nome;
    private String sexo;
    private String cpfProprietario;
    private String numeroColeira;

    public PacienteDTO() {

    }

    public PacienteDTO comEspecie(String especie) {
        this.especie = especie;
        return this;
    }

    public PacienteDTO comRaca(String raca) {
        this.raca = raca;
        return this;
    }

    public PacienteDTO comCor(String cor) {
        this.cor = cor;
        return this;
    }

    public PacienteDTO comDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public PacienteDTO comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public PacienteDTO comSexo(String sexo) {
        this.sexo = sexo;
        return this;
    }

    public PacienteDTO comCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
        return this;
    }

    public PacienteDTO comNumeroColeira(String numeroColeira) {
        this.numeroColeira = numeroColeira;
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
