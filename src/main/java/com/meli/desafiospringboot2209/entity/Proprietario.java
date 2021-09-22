package com.meli.desafiospringboot2209.entity;

public class Proprietario{

    private Pessoa pessoa;
    private String endereco;
    private String telefone;

    public Proprietario() {

    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Interfaces fluentes
    public Proprietario comPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public Proprietario comEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public Proprietario comTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    @Override
    public String toString() {
        return "Proprietario{" +
                "pessoa=" + pessoa +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
