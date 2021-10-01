package com.meli.desafiospringboot2209.service;

import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.persistence.PacientePersistence;


import java.util.List;
import java.util.Optional;

public class PacienteService {

    private PacientePersistence pacientePersistence;

    public PacienteService(PacientePersistence pacientePersistence){
        this.pacientePersistence = pacientePersistence;
    }

    public Paciente obterPaciente(String numeroColeira){
        List<Paciente> pacientes = pacientePersistence.getList();
        Optional<Paciente> any = pacientes.stream().filter(paciente -> paciente.getNumeroColeira().equals(numeroColeira)).findAny();
        return any.get();
    }
}
