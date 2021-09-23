package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.desafiospringboot2209.dto.VeterinarioDTO;
import com.meli.desafiospringboot2209.service.VeterinarioService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class VeterinarioPersistence {

    List<VeterinarioDTO> listaVeterinarios = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public VeterinarioDTO salvarVeterinarioNoArquivo(VeterinarioDTO veterinarioDTO) {
        mapearObjeto();
        listaVeterinarios = buscarVeterinario();

        try {
            if (veterinarioJaCadastrado(veterinarioDTO.getNumeroRegistro())) {
                throw new RuntimeException("Veterinario já cadastrado");
            }
            listaVeterinarios.add(veterinarioDTO);
            objectMapper.writeValue(new File("db/veterinario.json"), listaVeterinarios);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return veterinarioDTO;
    }

    public List<VeterinarioDTO> buscarVeterinario() {
        mapearObjeto();
        try {
            listaVeterinarios = objectMapper.readValue(new File("db/veterinario.json"), new TypeReference<List<VeterinarioDTO>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaVeterinarios;
    }

    public boolean veterinarioJaCadastrado(String numeroRegistro) throws IOException {
       listaVeterinarios = buscarVeterinario();
        System.out.println(numeroRegistro);
        if (listaVeterinarios.size() > 0) {
            System.out.println(numeroRegistro);
            for (VeterinarioDTO veterinarioDTO : listaVeterinarios) {
                if (veterinarioDTO.getNumeroRegistro().equals(numeroRegistro)) {
                    return true;
                }
                System.out.println(veterinarioDTO.getNumeroRegistro());
            }
            return false;
        } else {
            return false;
        }

    }
}
