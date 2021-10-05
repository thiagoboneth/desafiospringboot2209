package com.meli.desafiospringboot2209.dto;

import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.service.ProprietarioService;

public class PacienteDTO {

    private String especie;
    private String raca;
    private String cor;
    private String dataNascimento;
    private String nome;
    private String sexo;
    private String cpfProprietario;
    private String numeroColeira;

    public PacienteDTO() {

    }

    public PacienteDTO comEspecie(String especie) {
        this.especie = especie;
        return this;
    }

    public PacienteDTO comRaca(String raca) {
        this.raca = raca;
        return this;
    }

    public PacienteDTO comCor(String cor) {
        this.cor = cor;
        return this;
    }

    public PacienteDTO comDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public PacienteDTO comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public PacienteDTO comSexo(String sexo) {
        this.sexo = sexo;
        return this;
    }

    public PacienteDTO comCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
        return this;
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

    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public String getNumeroColeira() {
        return numeroColeira;
    }

    public static Paciente converte(PacienteDTO dto, ProprietarioService proprietarioService){
        Proprietario proprietario = proprietarioService.obterProprietario(dto.getCpfProprietario());
        return new Paciente()
                .comEspecie(dto.getEspecie())
                .comRaca(dto.getRaca())
                .comCor(dto.getCor())
                .comDataNascimento(dto.getDataNascimento())
                .comNome(dto.getNome())
                .comSexo(dto.getSexo())
                .comProprietario(proprietario)
                .comNumeroColeira(dto.getNumeroColeira());
    }
}