package com.meli.desafiospringboot2209.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.util.ReadFileUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Consulta {

    private String numeroConsulta;
    private String dataHora = LocalDateTime.now().toString();
    private Paciente paciente;
    private Proprietario proprietario;
    private String motivo;
    private Veterinario veterinario;
    private String diagnostico;
    private String tratamento;
    private String numeroColeita;

    public Consulta() throws IOException {

    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getNumeroColeita() {
        return numeroColeita;
    }

    public String getDataHora() {
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

    public Consulta comDataHora(String dataHora) {
        this.dataHora = dataHora;
        return this;
    }

    public Consulta comPaciente(Paciente paciente) {
        this.paciente = paciente;
        return this;
    }

    public Consulta comColeira(String numeroColeira){
        this.numeroColeita = numeroColeira;
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
    public Consulta comNumeroDaConsulta(String numeroConsulta) {
        this.numeroConsulta = numeroConsulta;
        return this;
    }


    public String getNumeroColeira() {
        return numeroColeita;
    }

    public void setNumeroColeira(String numeroColeita) {
        this.numeroColeita = numeroColeita;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public String getNumeroConsulta() {
        return numeroConsulta;
    }

    public void setNumeroConsulta(String numeroConsulta) {
        this.numeroConsulta = numeroConsulta;
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