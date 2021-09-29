package com.meli.desafiospringboot2209.dto;

import com.meli.desafiospringboot2209.entity.Consulta;

public class ConsultaDTO {

    private String numeroConsulta;
    private String numeroColeira;
    private String motivo;
    private String diagnostico;
    private String tratamento;
    private String numeroRegistroVeterinario;

    public ConsultaDTO(String numeroConsulta, String numeroColeira, String motivo, String diagnostico, String tratamento, String numeroRegistroVeterinario) {
        this.numeroConsulta = numeroConsulta;
        this.numeroColeira = numeroColeira;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
        this.numeroRegistroVeterinario = numeroRegistroVeterinario;
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

    public ConsultaDTO comNumeroRegistroVeterinario(String numeroRegistroVeterinario) {
        this.numeroRegistroVeterinario = numeroRegistroVeterinario;
        return this;
    }

    public static Consulta converte(Consulta consulta) {
        return new Consulta(
                consulta.getNumeroConsulta(),
                consulta.getNumeroColeira(),
                consulta.getMotivo(),
                consulta.getDiagnostico(),
                consulta.getTratamento(),
                consulta.getNumeroRegistroVeterinario());
    }

    public static Consulta converte(ConsultaDTO payload) {
        return new Consulta(
                payload.getNumeroConsulta(),
                payload.getNumeroColeira(),
                payload.getMotivo(),
                payload.getDiagnostico(),
                payload.getTratamento(),
                payload.getNumeroRegistroVeterinario());
    }
}