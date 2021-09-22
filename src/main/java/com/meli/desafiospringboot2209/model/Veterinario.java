package com.meli.desafiospringboot2209.model;

public class Veterinario extends Pessoa{
    public int idMedico;
    public String numeroRegistro;
    public String especialidade;

    public Veterinario(String cpf, String nome, String sobrenome, String dataNascimento, int idMedico, String numeroRegistro, String especialidade) {
        super(cpf,nome,sobrenome,dataNascimento);
        this.idMedico = idMedico;
        this.numeroRegistro = numeroRegistro;
        this.especialidade = especialidade;
    }

    public Veterinario() {

    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public String getEspecialidade() {
        return especialidade;
    }


    public String toString() {
        return "Veterinario{idMedico='" + idMedico + ", numeroRegistro=" + numeroRegistro+ ", especialidade=" + especialidade + "}";
    }

}
