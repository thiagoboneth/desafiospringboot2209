package com.meli.desafiospringboot2209.service;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.persistence.PacientePersistence;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private PacientePersistence pacientePersistence;

    public PacienteService(PacientePersistence pacientePersistence){
        this.pacientePersistence = pacientePersistence;
    }

    public Paciente obterPaciente(String numeroColeira){
        List<Paciente> pacientes = pacientePersistence.getList();
        Optional<Paciente> any = pacientes.stream().filter(paciente -> paciente.getNumeroColeira().equals(numeroColeira)).findAny();
        if (!any.isPresent()){
            throw new RuntimeException("Numero de coleira nulo");
        }
        return any.get();
    }
    public void cadastrarPaciente(Paciente paciente){

        this.pacientePersistence.salvarConsultaNoArquivo(paciente);
    }
}
