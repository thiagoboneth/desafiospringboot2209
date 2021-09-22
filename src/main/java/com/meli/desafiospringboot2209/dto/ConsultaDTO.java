package com.meli.desafiospringboot2209.dto;

import com.meli.desafiospringboot2209.entity.Consulta;
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

    public static Consulta converte(ConsultaDTO consultaDTO) {
        return new Consulta()
                .comPaciente(consultaDTO.getPaciente())
                .comMotivo(consultaDTO.getMotivo())
                .comVeterinario(consultaDTO.getVeterinario())
                .comDiagnostico(consultaDTO.getDiagnostico())
                .comTratamento(consultaDTO.getTratamento());
    }

    public static ConsultaDTO converte(Consulta consulta) {
        PacienteDTO pacienteDTO = PacienteDTO.converte(consulta.getPaciente());
        return new ConsultaDTO(
                consulta.getPaciente(),
                consulta.getMotivo(),
                consulta.getVeterinario(),
                consulta.getDiagnostico(),
                consulta.getTratamento());
    }
}
