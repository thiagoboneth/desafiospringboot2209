package com.meli.desafiospringboot2209.entity;

public class Paciente {

    private String especie;
    private String raca;
    private String cor;
    private String dataNascimento;
    private String nome;
    private String sexo;
    private Proprietario proprietario;

    private String numeroColeira;
    private boolean consutado = false;



    public Paciente() {
    }

    public String getNumeroColeira() {
        return numeroColeira;
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

    // Interfaces fluentes
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

    public Paciente comProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
        return this;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "especie='" + especie + '\'' +
                ", raca='" + raca + '\'' +
                ", cor='" + cor + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", nome='" + nome + '\'' +
                ", sexo=" + sexo +
                ", proprietario=" + proprietario +
                '}';
    }
}