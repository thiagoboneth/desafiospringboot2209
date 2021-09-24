package com.meli.desafiospringboot2209.dto;

import com.meli.desafiospringboot2209.entity.Proprietario;

public class PacienteDTO {

    private String especie;
    private String raca;
    private String cor;
    private String dataNascimento;
    private String nome;
    private String sexo;
    //  private Proprietario proprietario;
    private String proprietario;

    // indentificacao do animal
    private String numeroColeira;
    //variavel para definir se o paciente4e ests em uma consulta
    private boolean consutado = false;

    // estou passando uma string do proprietario nao o objeto
    public PacienteDTO(String especie, String raca, String cor, String dataNascimento, String nome, String sexo, String proprietario) {
        this.especie = especie;
        this.raca = raca;
        this.cor = cor;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.sexo = sexo;
        this.proprietario = proprietario;
      //  this.proprietario = proprietario;
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

    public PacienteDTO comProprietario(String proprietario) {
        this.proprietario = proprietario;
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

    public String getProprietario() {
        return proprietario;
    }

    public String getNumeroColeira() {
        return numeroColeira;
    }
}
