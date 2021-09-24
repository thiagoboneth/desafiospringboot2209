package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProprietarioPersistence {

    List<ProprietarioDTO> listaProprietarios = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public ProprietarioDTO salvarProprietarioNoArquivo(ProprietarioDTO proprietarioDTO) {
        mapearObjeto();
        try {
            if (proprietarioJaCadastrado(proprietarioDTO.getCpf())) {
                throw new RuntimeException("Proprietario já cadastrado");
            }
            listaProprietarios.add(proprietarioDTO);
            objectMapper.writeValue(new File("db/proprietario.json"), listaProprietarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proprietarioDTO;
    }

    public boolean proprietarioJaCadastrado(String cpf) throws IOException {
        listaProprietarios = buscarProprietario();
        if (listaProprietarios.size() > 0) {
            for (ProprietarioDTO proprietarioDTO : listaProprietarios) {
                if (proprietarioDTO.getCpf().equals(cpf)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public List<ProprietarioDTO> buscarProprietario() {
        mapearObjeto();
        try {
            listaProprietarios = objectMapper.readValue(new File("db/proprietario.json"), new TypeReference<List<ProprietarioDTO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaProprietarios;
    }

    public void removerProprietarioPorCpf(String cpf) {
        try {
            String json = readFile("db/proprietario.json");
            Gson gson = new Gson();
            List<ProprietarioDTO> veterinarioDTOS = gson.fromJson(json, new TypeToken<List<ProprietarioDTO>>() {
            }.getType());
            for (ProprietarioDTO item : veterinarioDTOS) {
                if (item.getCpf().equals(cpf)) {
                    veterinarioDTOS.remove(item);
                    break;
                }
            }
            objectMapper.writeValue(new File("db/proprietario.json"), veterinarioDTOS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar ID");
        }
    }

    public void atualizarProprietario(ProprietarioDTO payLoad) {
        try {
            String json = readFile("db/proprietario.json");
            Gson gson = new Gson();

            ProprietarioDTO registro = payLoad;
            String cpf = registro.getCpf();

            List<ProprietarioDTO> proprietarioDTOS = gson.fromJson(json, new TypeToken<List<ProprietarioDTO>>() {
            }.getType());
            for (ProprietarioDTO item : proprietarioDTOS) {
                if (item.getCpf().equals(cpf)) {
                    item.comNome(registro.getNome());
                    item.comSobrenome(registro.getSobrenome());
                    item.comDataNascimento(registro.getDataNascimento());
                    item.comEndereco(registro.getEndereco());
                    item.comTelefone(registro.getTelefone());
                    break;
                }
            }
            objectMapper.writeValue(new File("db/proprietario.json"), proprietarioDTOS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao alterar ID");
        }
    }

    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

}
