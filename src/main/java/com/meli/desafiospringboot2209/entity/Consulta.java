package com.meli.desafiospringboot2209.entity;

import java.time.LocalDate;

public class Consulta {
    LocalDate dataHora;
    public Paciente paciente;
    public String motivo;
    public Veterinario veterinario;
    public String diagnostico;
    public String tratamento;

    public Consulta(LocalDate dataHora, Paciente paciente, String motivo, Veterinario veterinario, String diagnostico, String tratamento) {
        this.dataHora = dataHora;
        this.paciente = paciente;
        this.motivo = motivo;
        this.veterinario = veterinario;
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
    }

    public Consulta() {

    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getMotivo() {
        return motivo;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamento() {
        return tratamento;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "dataHora=" + dataHora +
                ", paciente=" + paciente +
                ", motivo='" + motivo + '\'' +
                ", veterinario=" + veterinario +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamento='" + tratamento + '\'' +
                '}';
    }
}