package com.meli.desafiospringboot2209.dto;

import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.entity.Veterinario;

public class ConsultaDTO {

    private Paciente paciente;
    private String motivo;
    private Veterinario veterinario;
    private String diagnostico;
    private String tratamento;

    public ConsultaDTO(Paciente paciente, String motivo, Veterinario veterinario, String diagnostico, String tratamento) {
        this.paciente = paciente;
        this.motivo = motivo;
        this.veterinario = veterinario;
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
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

}
