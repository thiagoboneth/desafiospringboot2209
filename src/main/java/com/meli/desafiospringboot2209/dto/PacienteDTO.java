package com.meli.desafiospringboot2209.dto;

import com.meli.desafiospringboot2209.entity.Proprietario;

public class PacienteDTO {

    private String especie;
    private String raca;
    private String cor;
    private String dataNascimento;
    private String nome;
    private String sexo;
    private Proprietario proprietario;

    public PacienteDTO(String especie, String raca, String cor, String dataNascimento, String nome, String sexo, Proprietario proprietario) {
        this.especie = especie;
        this.raca = raca;
        this.cor = cor;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.sexo = sexo;
        this.proprietario = proprietario;
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

    public Proprietario getProprietario() {
        return proprietario;
    }

}
