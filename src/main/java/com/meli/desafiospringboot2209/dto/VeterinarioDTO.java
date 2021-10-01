package com.meli.desafiospringboot2209.dto;


public class VeterinarioDTO {

    private String cpf;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String numeroRegistro;
    private String especialidade;

    public VeterinarioDTO(String cpf, String nome, String sobrenome, String dataNascimento, String numeroRegistro, String especialidade) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.numeroRegistro = numeroRegistro;
        this.especialidade = especialidade;
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

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public String getEspecialidade() {
        return especialidade;
    }



    public VeterinarioDTO comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public VeterinarioDTO comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public VeterinarioDTO comSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public VeterinarioDTO comDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }


    public VeterinarioDTO comEspecialidade(String especialidade) {
        this.especialidade = especialidade;
        return this;
    }
}
