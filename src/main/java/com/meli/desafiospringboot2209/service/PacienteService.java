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
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService{

    private PacientePersistence pacientePersistence;

    List<Paciente> pacientesLista = new ArrayList<>();

    String arquivo = "pacientes.json";
    String caminho = "db";
    String cP = caminho + "/" + arquivo;
    ObjectMapper objectMapper = new ObjectMapper();

    public PacienteService(PacientePersistence pacienteService) {
        this.pacientePersistence = pacienteService;
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
        try {
            pacientesLista = objectMapper.readValue(new File(cP), new TypeReference<List<Paciente>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ordemListaProprietariosCrescente();
        return pacientesLista;
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

/*    public void ordemListaProprietariosCrescente() {
        pacientePersistence.sort((Comparator.comparing(Paciente::getNumeroColeira)));
    }*/


    public Paciente obterPaciente(String numeroColeira){
        List<Paciente> pacientes = pacientePersistence.getList();
        Optional<Paciente> any = pacientes.stream().filter(paciente -> paciente.getNumeroColeira().equals(numeroColeira)).findAny();
        if (!any.isPresent()){
            throw new RuntimeException("Numero de coleira nulo");
        }
        return any.get();
    }
}
