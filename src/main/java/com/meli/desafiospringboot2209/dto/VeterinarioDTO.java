package com.meli.desafiospringboot2209.dto;

import com.meli.desafiospringboot2209.entity.Veterinario;

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

    public static Veterinario converte(VeterinarioDTO veterinarioDTO) {
        return new Veterinario()
                .comCpf(veterinarioDTO.getCpf())
                .comNome(veterinarioDTO.getNome())
                .comSobrenome(veterinarioDTO.getSobrenome())
                .comDataNascimento(veterinarioDTO.getDataNascimento())
                .comNumeroRegistro(veterinarioDTO.getNumeroRegistro())
                .comEspecialidade(veterinarioDTO.getEspecialidade());
    }

    public static VeterinarioDTO converte(Veterinario veterinario) {
        return new VeterinarioDTO(
                veterinario.getCpf(),
                veterinario.getNome(),
                veterinario.getSobrenome(),
                veterinario.getDataNascimento(),
                veterinario.getNumeroRegistro(),
                veterinario.getEspecialidade());
    }
}
