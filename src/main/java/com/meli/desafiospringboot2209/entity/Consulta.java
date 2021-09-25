package com.meli.desafiospringboot2209.entity;

import java.time.LocalDate;

public class Consulta {

    private LocalDate dataHora;
    private Paciente paciente;
    private String motivo;
    private Veterinario veterinario;
    private String diagnostico;
    private String tratamento;
    private String cpfProprietario;

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

    public Consulta comDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
        return this;
    }

    public Consulta comPaciente(Paciente paciente) {
        this.paciente = paciente;
        return this;
    }

    public Consulta comMotivo(String motivo) {
        this.motivo = motivo;
        return this;
    }

    public Consulta comVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
        return this;
    }

    public Consulta comDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
        return this;
    }

    public Consulta comTratamento(String tratamento) {
        this.tratamento = tratamento;
        return this;
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