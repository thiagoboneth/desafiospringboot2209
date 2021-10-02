package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.dto.VeterinarioDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.util.ReadFileUtil;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class VeterinarioPersistence implements Repository{

    String arquivo = "veterinarios.json";
    String caminho = "db";
    String cC = caminho+"/"+arquivo;
    Gson gson = new Gson();

    List<VeterinarioDTO> listaVeterinarios = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public Veterinario salvarVeterinarioNoArquivo(Veterinario veterinario) {
        List<Veterinario> veterinarios = getList();
        veterinarios.add(veterinario);
        try {
            objectMapper.writeValue(new File(cC), veterinarios);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return veterinario;
    }
    public void ordenaVeterinarioNaOrdemCrescente(){
        Collections.sort(listaVeterinarios,((o1, o2) -> o1.getNumeroRegistro().compareTo(o2.getNumeroRegistro())));
    }

    // Método Cadastrar Ok
    public boolean veterinarioJaCadastrado(String numeroRegistro, String cpf){
        List<Veterinario> veterinarios = getList();
        if (veterinarios.size() > 0) {
            Optional<Veterinario> anyNumeroRegistro = veterinarios.stream().filter(c -> c.getNumeroRegistro().equals(numeroRegistro)).findAny();
            Optional<Veterinario> anyCpf = veterinarios.stream().filter(c -> c.getCpf().equals(cpf)).findAny();
            return anyNumeroRegistro.isPresent() || anyCpf.isPresent();
        }
        return false;
    }

    public List<VeterinarioDTO> buscarVeterinario() {
        mapearObjeto();
        try {
            listaVeterinarios = objectMapper.readValue(new File(cC), new TypeReference<List<VeterinarioDTO>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        ordenaVeterinarioNaOrdemCrescente();
        return listaVeterinarios;
    }

    public boolean verificaNull(VeterinarioDTO veterinarioDTO) {
        if (veterinarioDTO.getCpf() == null
                || veterinarioDTO.getNome() == null
                || veterinarioDTO.getSobrenome() == null
                || veterinarioDTO.getDataNascimento() == null
                || veterinarioDTO.getNumeroRegistro() == null
                || veterinarioDTO.getEspecialidade() == null) {
            return true;
        } else {
            return false;
        }
    }

    public void removerVeterinarioPorRegistro(String numeroRegistro) {
        List<Veterinario> veterinariosList = getList();
        Optional<Veterinario> any = veterinariosList.stream().filter(c -> c.getNumeroRegistro().equals(numeroRegistro)).findAny();
        if(any.isPresent())
            veterinariosList.remove(any.get());
        try {
            objectMapper.writeValue(new File(cC), veterinariosList);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar ID");
        }
    }

    public void alterarVeterinario(Veterinario veterinario) {
        try {
            String json = ReadFileUtil.readFile(cC);
            Gson gson = new Gson();

            Veterinario registro = veterinario;
            String NumeroRegistro = registro.getNumeroRegistro();

            List<VeterinarioDTO> veterinarioDTOS = gson.fromJson(json, new TypeToken<List<VeterinarioDTO>>(){}.getType());
            for (VeterinarioDTO item: veterinarioDTOS) {
                if (item.getNumeroRegistro().equals(NumeroRegistro)){
                    item.comCpf(registro.getCpf());
                    item.comNome(registro.getNome());
                    item.comSobrenome(registro.getSobrenome());
                    item.comDataNascimento(registro.getDataNascimento());
                    item.comEspecialidade(registro.getEspecialidade());
                    break;
                }
            }
            objectMapper.writeValue(new File(cC),veterinarioDTOS );
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao alterar ID");
        }
    }

    public boolean ConsultaVeterinarioRegistrada(String NumeroRegistro){
        try {
            String veterinarioConsultaArquivo = ReadFileUtil.readFile("db/consultas.json");
            List<ConsultaDTO> ConsultaDTOS = gson.fromJson(veterinarioConsultaArquivo, new TypeToken<List<ConsultaDTO>>(){}.getType());
            for (ConsultaDTO item: ConsultaDTOS) {
                if (item.getNumeroRegistroVeterinario().equals(NumeroRegistro)){
                    return true;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public List<Veterinario> getList() {
        List<Veterinario> veterinarios = new ArrayList<>();
        try {
            String consultaArquivo = ReadFileUtil.readFile("db/veterinarios.json");
            veterinarios = gson.fromJson(consultaArquivo, new TypeToken<List<Veterinario>>() {}.getType());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return veterinarios;
    }
}
