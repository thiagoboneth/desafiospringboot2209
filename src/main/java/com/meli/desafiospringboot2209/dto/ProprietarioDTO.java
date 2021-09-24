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

    public ProprietarioDTO(String nome, String sobrenome, String dataNascimento,String endereco,String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.telefone = telefone;

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
