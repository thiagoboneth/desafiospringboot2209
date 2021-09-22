package com.meli.desafiospringboot2209.entity;

public class Veterinario extends Pessoa{

    public Pessoa pessoa;
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
    public Veterinario comPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
                "pessoa=" + pessoa +
                ", idMedico=" + idMedico +
                ", numeroRegistro='" + numeroRegistro + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }
}
