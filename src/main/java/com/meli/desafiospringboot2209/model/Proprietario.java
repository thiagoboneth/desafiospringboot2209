package com.meli.desafiospringboot2209.model;

public class Proprietario extends Pessoa {
    public String endereco;
    public String telefone;

    public Proprietario(String cpf, String nome, String sobrenome, String dataNascimento ,String endereco, String telefone) {
        super(cpf,nome,sobrenome,dataNascimento);
        this.endereco = endereco;
        this.telefone = telefone;
    }


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

    public String toString() {
        return "Proprietario{proprietario='" + ", endereco=" + endereco + ", telefone=" + telefone +  "}";
    }

}
