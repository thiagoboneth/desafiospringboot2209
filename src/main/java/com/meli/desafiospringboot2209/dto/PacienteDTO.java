package com.meli.desafiospringboot2209.dto;

import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.service.PacienteService;
import com.meli.desafiospringboot2209.service.ProprietarioService;
import com.meli.desafiospringboot2209.service.VeterinarioService;

import java.io.IOException;

public class PacienteDTO {

    private String especie;
    private String raca;
    private String cor;
    private String dataNascimento;
    private String nome;
    private String sexo;
    private String cpfProprietario;
    private Proprietario proprietario;
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

    public PacienteDTO comProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
        return this;
    }

    public PacienteDTO comNumeroColeira(String numeroColeira) {
        this.numeroColeira = numeroColeira;
        return this;
    }
    public PacienteDTO comCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
        return this;
    }

    public String getCpfProprietario() {
        return cpfProprietario;
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

    public Proprietario getProprietario() {
        return proprietario;
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
                .comNumeroDaColeira(dto.getNumeroColeira())
                .comCpfProprietario(dto.getCpfProprietario())
                .comProprietario(proprietario);
    }

//      "especie" : "Coelho",
//              "raca" : "Coelho da montanha",
//              "cor" : "Branco e cinza",
//              "dataNascimento" : "01/01/2019",
//              "nome" : "Perna",
//              "sexo" : "Masculino",
//              "Proprietario" : [
//    {
//        "cpf" : "123.456.789-582",
//            "nome" : "Isac",
//            "sobrenome" : "Oliveira",
//            "dataNascimento" : "01/05/91",
//            "endereco" : "Rua 4190",
//            "telefone" : "9988997765"
//    }
//  ],
//          "numeroColeira" : "1"


    @Override
    public String toString() {
        return "PacienteDTO{" +
                "especie='" + especie + '\'' +
                ", raca='" + raca + '\'' +
                ", cor='" + cor + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", proprietario=" + proprietario +
                ", numeroColeira='" + numeroColeira + '\'' +
                '}';
    }
}
