package com.meli.desafiospringboot2209.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.PacienteDTO;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.persistence.PacientePersistence;
import com.meli.desafiospringboot2209.persistence.VeterinarioPersistence;
import com.meli.desafiospringboot2209.util.ReadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService{

    @Autowired
    private PacientePersistence pacientePersistence;

    String arquivo = "pacientes.json";
    String caminho = "db";
    String cP = caminho + "/" + arquivo;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public PacienteService(PacientePersistence pacientePersistence) {
        this.pacientePersistence = pacientePersistence;
    }


    // Método POST - Falta verificar cadastro com parametros nulos

    public boolean cadastrarPaciente(Paciente paciente) throws IOException {

        if (!pacienteJaCadastrado(paciente.getNumeroColeira())) {
            try{
                pacientePersistence.verificaNull(paciente);
                pacientePersistence.salvarPacienteNoArquivo(paciente);
                return true;
            } catch (RuntimeException e) {
                throw new RuntimeException("Erro ao cadastrar o proprietário");
            }
        } else {
            throw new RuntimeException("Proprietário já cadastrado");
        }
    }

    // Método DELETE OK
    public boolean pacienteJaCadastrado(String numeroDaColeira) throws IOException {

        return this.pacientePersistence.pacienteJaCadastrado(numeroDaColeira);
    }


    // Método GET OK
    public List<Paciente> buscarPaciente() {
        return this.pacientePersistence.buscarPaciente();
    }

    // Método PUT - Falta verificar alteracao com parametros nulos
    public boolean alterarPaciente(Paciente paciente) {
        pacientePersistence.verificaNull(paciente);
        pacientePersistence.alterarPaciente(paciente);
        return true;
    }

    // Método DELETE
    public boolean removerPaciente(String NumeroColeira) throws IOException {
        if(pacienteJaCadastrado(NumeroColeira));
        return pacientePersistence.removerPacientePorId(NumeroColeira);
    }

    public Paciente obterPaciente(String numeroColeira){
        List<Paciente> pacientes = pacientePersistence.getList();
        Optional<Paciente> any = pacientes.stream().filter(paciente -> paciente.getNumeroColeira().equals(numeroColeira)).findAny();
        if (!any.isPresent()){
            throw new RuntimeException("Numero de coleira nulo");
        }
        return any.get();
    }
}
