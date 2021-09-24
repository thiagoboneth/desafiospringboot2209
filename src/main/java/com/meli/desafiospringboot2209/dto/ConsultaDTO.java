package com.meli.desafiospringboot2209.dto;

public class ConsultaDTO {

    private String numeroConsulta;
    private String numeroColeira;
    private String motivo;
    private String diagnostico;
    private String tratamento;
    private String numeroRegistroVeterinario;

    public ConsultaDTO() {

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

    public ConsultaDTO comNumeroConsulta(String numeroConsulta) {
        this.numeroConsulta = numeroConsulta;
        return this;
    }
}
