package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.dto.PacienteDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.util.ReadFileUtil;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class PacientePersistence implements GetList<Paciente> {

    String arquivo = "pacientes.json";
    String caminho = "db";
    String cC = caminho + "/" + arquivo;

    ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new Gson();

    public Paciente salvarConsultaNoArquivo(Paciente paciente) {
        List<Paciente> listaPaciente = getList();
        listaPaciente.add(paciente);
        try {
            objectMapper.writeValue(new File(cC), listaPaciente);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paciente;
    }


    @Override
    public List<Paciente> getList() {
        List<Paciente> pacientes = new ArrayList<>();
        try {
            String consultaArquivo = ReadFileUtil.readFile("db/pacientes.json");
            pacientes = gson.fromJson(consultaArquivo, new TypeToken<List<Paciente>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    public void salvarConsultaNoArquivo(Paciente paciente) {
    }
}

