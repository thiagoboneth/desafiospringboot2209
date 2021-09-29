package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.PacienteDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.exception.PersistenceException;
import com.meli.desafiospringboot2209.utils.ReadFileUtil;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class ConsultaPersistence {

    String arquivoConsultas = "consultas.json";
    String arquivoPacientes = "pacientes.json";
    String caminho = "db";
    String cConsultas= caminho + "/" + arquivoConsultas;
    String cPacientes = caminho + "/" + arquivoPacientes;

    List<Consulta> listaConsultas = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new Gson();

    // Fica
    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    // Fica
    public List<Consulta> buscarConsulta() {
        mapearObjeto();
        try {
            listaConsultas = objectMapper.readValue(new File(cConsultas), new TypeReference<List<Consulta>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        //ordemListaConsultaDescrescente();
        return listaConsultas;
    }

    // Fica
    public void dadosProprietario(Consulta consulta) throws IOException {
        String consultaArquivo = ReadFileUtil.readFile(cPacientes);
        List<Paciente> paciente = gson.fromJson(consultaArquivo, new TypeToken<List<Paciente>>() {
        }.getType());
    }

    // Fica (POST) - injecao de dependencia em ConsultaService
    public Consulta cadastro(Consulta consulta) throws IOException {
        mapearObjeto();
        listaConsultas = buscarConsulta();

        listaConsultas.add(consulta);

        dadosProprietario(consulta);

        objectMapper.writeValue(new File(cConsultas), listaConsultas);

        return consulta;
    }

    // Fica (PUT)
    public void altera(Consulta consulta) {
        try {
            String json = ReadFileUtil.readFile(cConsultas);
            Gson gson = new Gson();

            List<Consulta> consultas = gson.fromJson(json, new TypeToken<List<Consulta>>() {
            }.getType());

            objectMapper.writeValue(new File(cConsultas), consultas);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Erro ao alterar ID");
        }
    }

    // Fica
    public void removerConsultaPorId(String id) {
        try {
            String consultaArquivo = ReadFileUtil.readFile(cConsultas);
            List<Consulta> consultas = gson.fromJson(consultaArquivo, new TypeToken<List<Consulta>>() {
            }.getType());

            for (Consulta item : consultas) {
                if (item.getNumeroConsulta().equals(id)) {
                    consultas.remove(item);
                    break;
                }
            }

            objectMapper.writeValue(new File(cConsultas), consultas);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Erro ao deletar ID");
        }
    }

}
