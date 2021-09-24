package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
import com.meli.desafiospringboot2209.dto.VeterinarioDTO;
import com.meli.desafiospringboot2209.entity.Veterinario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

    public void removerMedicoPorId(String id){
        try {
            String json = readFile("db/veterinario.json");
            Gson gson = new Gson();
            List<VeterinarioDTO> veterinarioDTOS = gson.fromJson(json, new TypeToken<List<VeterinarioDTO>>(){}.getType());
            for (VeterinarioDTO item: veterinarioDTOS) {
                if (item.getNumeroRegistro().equals(id)){
                    veterinarioDTOS.remove(item);
                    break;
                }
            }
            objectMapper.writeValue(new File("db/veterinario.json"),veterinarioDTOS );
            }catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao deletar ID");
            }
        }

    public void alterarVeterinario(VeterinarioDTO payLoad) {
        try {
            String json = readFile("db/veterinario.json");
            Gson gson = new Gson();

            VeterinarioDTO registros = payLoad;
            String NumeroRegistro = registros.getNumeroRegistro();

            List<VeterinarioDTO> veterinarioDTOS = gson.fromJson(json, new TypeToken<List<VeterinarioDTO>>(){}.getType());
            for (VeterinarioDTO item: veterinarioDTOS) {
                if (item.getNumeroRegistro().equals(NumeroRegistro)){
                    item.comCpf(registros.getCpf());
                    item.comNome(registros.getNome());
                    item.comSobrenome(registros.getSobrenome());
                    item.comDataNascimento(registros.getDataNascimento());
                    item.comEspecialidade(registros.getEspecialidade());
                    break;
                }
            }
            objectMapper.writeValue(new File("db/veterinario.json"),veterinarioDTOS );
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao alterar ID");
        }
    }



    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader (file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
}
