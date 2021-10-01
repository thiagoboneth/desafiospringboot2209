package com.meli.desafiospringboot2209.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.service.PacienteService;
import com.meli.desafiospringboot2209.service.VeterinarioService;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Veterinario;

import java.io.IOException;
import java.time.LocalDateTime;


/**
 *
 * {
 *   "numeroConsulta": "300",
 *   "numeroColeira": "5",
 *   "motivo" : "Quebrou a pata",
 *   "diagnostico" : "Pata enfaixada",
 *   "tratamento" : "repouso",
 *   "numeroRegistroVeterinario" : "2"
 * }
 *
 *
 *
 */
public class ConsultaDTO {

    private String numeroConsulta;
    private String numeroColeira;
    private String motivo;
    private String diagnostico;
    private String tratamento;
    private String numeroRegistroVeterinario;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cpfProprietario;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String nomeProprietario;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String dataHora = LocalDateTime.now().toString();


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


//this.numeroConsulta = numeroConsulta;
//        this.numeroColeira = numeroColeira;
//        this.motivo = motivo;
//        this.diagnostico = diagnostico;
//        this.tratamento = tratamento;
//        this.numeroRegistroVeterinario = numeroRegistroVeterinario;

    public static Consulta converte(ConsultaDTO dto, VeterinarioService veterinarioService, PacienteService pacienteService){
        Veterinario veterinario = veterinarioService.obterVeterinario(dto.getNumeroRegistroVeterinario());
        Paciente paciente = pacienteService.obterPaciente(dto.getNumeroColeira());
        try {
            return new Consulta()
                    .comColeira(dto.getNumeroColeira())
                    .comMotivo(dto.getMotivo())
                    .comDiagnostico(dto.getDiagnostico())
                    .comTratamento(dto.getTratamento())
                    .comVeterinario(veterinario)
                    .comPaciente(paciente);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}