package com.meli.desafiospringboot2209.entity;

public class Paciente {
    public String especie;
    public String raca;
    public String cor;
    public String dataNascimento;
    public String nome;
    public String sexo;
    public Proprietario proprietario;

    public Paciente(String especie, String raca, String cor, String dataNascimento, String nome, String sexo, Proprietario proprietario) {

        this.especie = especie;
        this.raca = raca;
        this.cor = cor;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.sexo = sexo;
        this.proprietario = proprietario;
    }

    public Paciente() {

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

    @Override
    public String toString() {
        return "Paciente{" +
                "especie='" + especie + '\'' +
                ", raca='" + raca + '\'' +
                ", cor='" + cor + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", proprietario=" + proprietario +
                '}';
    }
}