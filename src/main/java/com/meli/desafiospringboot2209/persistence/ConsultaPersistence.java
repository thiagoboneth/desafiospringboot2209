package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
import com.meli.desafiospringboot2209.dto.VeterinarioDTO;
import com.meli.desafiospringboot2209.util.ReadFileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaPersistence {

    // caminho doa arquivos
    String arquivo = "consultas.json";
    String caminho = "db";
    String cC = caminho + "/" + arquivo;

    List<ConsultaDTO> listaConsultas = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new Gson();

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public ConsultaDTO salvarConsultaNoArquivo(ConsultaDTO consultaDTO) {
        mapearObjeto();
        listaConsultas = buscarConsulta();

        try {
            if (consultaJaCadastrada(consultaDTO.getNumeroConsulta())) {
                throw new RuntimeException("Consulta já cadastrada");
            }
            listaConsultas.add(consultaDTO);
            objectMapper.writeValue(new File(cC), listaConsultas);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error na escrita do arquivo");
        }
        return consultaDTO;
    }

    public boolean consultaJaCadastrada(String numeroConsulta) throws IOException {
        listaConsultas = buscarConsulta();
        if (listaConsultas.size() > 0) {
            for (ConsultaDTO consultaDTO : listaConsultas) {
                if (consultaDTO.getNumeroConsulta().equals(numeroConsulta)) {
                    return true;
                }
                System.out.println(consultaDTO.getNumeroConsulta());
            }
            return false;
        } else {
            return false;
        }
    }

    public List<ConsultaDTO> buscarConsulta() {
        mapearObjeto();
        try {
            listaConsultas = objectMapper.readValue(new File(cC), new TypeReference<List<ConsultaDTO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaConsultas;
    }

    public void alterarConsulta(ConsultaDTO payLoad) {
        try {
            String json = ReadFileUtil.readFile(cC);
            Gson gson = new Gson();

            ConsultaDTO registros = payLoad;
            String numeroConsulta = registros.getNumeroConsulta();

            List<ConsultaDTO> consultaDTOS = gson.fromJson(json, new TypeToken<List<ConsultaDTO>>() {
            }.getType());
            for (ConsultaDTO item : consultaDTOS) {
                if (item.getNumeroConsulta().equals(numeroConsulta)) {
                    item.comNumeroColeira(registros.getNumeroColeira());
                    item.comMotivo(registros.getMotivo());
                    item.comDiagnnostico(registros.getDiagnostico());
                    item.comTratamento(registros.getTratamento());
                    item.comNumeroConsulta(registros.getNumeroConsulta());
                    break;
                }
            }
            objectMapper.writeValue(new File(cC), consultaDTOS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao alterar ID");
        }
    }

    /*public boolean proprietarioRegistradoEmConsulta(String cpf){
        try {
            String proprietarioArquivo = ReadFileUtil.readFile("db/proprietario.json");
            List<ProprietarioDTO> proprietarioDTOS = gson.fromJson(proprietarioArquivo, new TypeToken<List<ProprietarioDTO>>(){}.getType());
            for (ProprietarioDTO item: proprietarioDTOS) {
                if (item.getCpf().equals(cpf)){
                    return true;
                }else {
                    return false;
                }

            }
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean veterinarioRegistradoEmConsulta(String numeroRegistro){
        try {
            String veterinarioArquivo = ReadFileUtil.readFile("db/veterinario.json");
            List<VeterinarioDTO> veterinarioDTOS = gson.fromJson(veterinarioArquivo, new TypeToken<List<ProprietarioDTO>>(){}.getType());
            for (VeterinarioDTO item: veterinarioDTOS) {
                if (item.getNumeroRegistro().equals(numeroRegistro)){
                    return true;
                }else {
                    return false;
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }*/
    public void removerConsultaPorId(String id) {
        try {
            String consultaArquivo = ReadFileUtil.readFile("db/consultas.json");
            List<ConsultaDTO> consultaDTOS = gson.fromJson(consultaArquivo, new TypeToken<List<ConsultaDTO>>() {
            }.getType());

            for (ConsultaDTO item : consultaDTOS) {
                if (item.getNumeroConsulta().equals(id)) {
                    consultaDTOS.remove(item);
                    break;
                }
            }

            objectMapper.writeValue(new File(cC), consultaDTOS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar ID");
        }
    }
}
