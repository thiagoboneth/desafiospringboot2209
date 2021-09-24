package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ProprietarioPersistence {

    List<ProprietarioDTO> listaProprietarios = new ArrayList<>();
    ProprietarioDTO proprietarioDTO = new ProprietarioDTO();
    ObjectMapper objectMapper = new ObjectMapper();

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public ProprietarioDTO salvarProprietarioNoArquivo(ProprietarioDTO proprietarioDTO) {
        mapearObjeto();
        try {
            listaProprietarios.add(proprietarioDTO);
            objectMapper.writeValue(new File("db/proprietario.json"), listaProprietarios);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return proprietarioDTO;
    }

    public List<ProprietarioDTO> buscarproprietario() {
        mapearObjeto();
        try {
            listaProprietarios = objectMapper.readValue(new File("db/proprietario.json"), new TypeReference<List<ProprietarioDTO>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaProprietarios;
    }

}
