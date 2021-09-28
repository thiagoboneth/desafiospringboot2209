package com.meli.desafiospringboot2209.dto;

import java.time.LocalDateTime;

public class ConsultaDTO {

    private String numeroConsulta;
    private String numeroColeira;
    private String motivo;
    private String diagnostico;
    private String tratamento;
    private String numeroRegistroVeterinario;
    private String cpfProprietario;
    private String nomeProprietario;
    private String dataHora = LocalDateTime.now().toString();

    public ConsultaDTO(String numeroConsulta, String numeroColeira, String motivo, String diagnostico, String tratamento, String numeroRegistroVeterinario, String cpfProprietario, String nomeProprietario, String dataHora) {
        this.numeroConsulta = numeroConsulta;
        this.numeroColeira = numeroColeira;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
        this.numeroRegistroVeterinario = numeroRegistroVeterinario;
        this.cpfProprietario = cpfProprietario;
        this.nomeProprietario = nomeProprietario;
        this.dataHora = dataHora;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public String getNumeroConsulta() {
        return numeroConsulta;
    }

    public String getNumeroColeira() {
        return numeroColeira;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamento() {
        return tratamento;
    }

    public String getNumeroRegistroVeterinario() {
        return numeroRegistroVeterinario;
    }


    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public String getDataHora() {
        return dataHora;
    }


    public ConsultaDTO comCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
        return this;
    }

    public ConsultaDTO comNumeroColeira(String numeroColeira) {
        this.numeroColeira = numeroColeira;
        return this;
    }

    public ConsultaDTO comMotivo(String motivo) {
        this.motivo = motivo;
        return this;
    }

    public ConsultaDTO comDiagnnostico(String diagnostico) {
        this.diagnostico = diagnostico;
        return this;
    }

    public ConsultaDTO comTratamento(String tratamento) {
        this.tratamento = tratamento;
        return this;
    }

    public ConsultaDTO comNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
        return this;
    }
    public ConsultaDTO comNumeroRegistroVeterinario(String numeroRegistroVeterinario) {
        this.numeroRegistroVeterinario = numeroRegistroVeterinario;
        return this;
    }
}