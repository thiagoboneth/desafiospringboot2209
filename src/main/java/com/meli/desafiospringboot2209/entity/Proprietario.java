package com.meli.desafiospringboot2209.entity;

public class Proprietario{

    private String cpf;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String endereco;
    private String telefone;

    public Proprietario() {
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public Proprietario comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Proprietario comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Proprietario comSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public Proprietario comDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
