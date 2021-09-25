package com.meli.desafiospringboot2209.dto;


public class ProprietarioDTO {
    private String cpf;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String endereco;
    private String telefone;

    public ProprietarioDTO() {
    }

    public ProprietarioDTO comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ProprietarioDTO comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ProprietarioDTO comSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public ProprietarioDTO comDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public ProprietarioDTO comEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public ProprietarioDTO comTelefone(String telefone) {
        this.telefone = telefone;
        return this;
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

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

}
