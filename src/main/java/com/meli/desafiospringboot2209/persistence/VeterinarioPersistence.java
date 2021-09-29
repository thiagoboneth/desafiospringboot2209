package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.dto.VeterinarioDTO;
import com.meli.desafiospringboot2209.exception.PersistenceException;
import com.meli.desafiospringboot2209.utils.ErrosNulos;
import com.meli.desafiospringboot2209.utils.ReadFileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VeterinarioPersistence {

    String arquivo = "veterinario.json";
    String caminho = "db";
    String cC = caminho+"/"+arquivo;
    Gson gson = new Gson();

    List<VeterinarioDTO> listaVeterinarios = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void salvarVeterinarioNoArquivo(VeterinarioDTO veterinarioDTO) {
        mapearObjeto();
        listaVeterinarios = buscarVeterinario();

        try {
            if (verificaNull(veterinarioDTO)) {
                throw new PersistenceException("Os campos não podem ser nulos");
            }

            if (veterinarioJaCadastrado(veterinarioDTO.getNumeroRegistro())) {
                throw new PersistenceException("Veterinario já cadastrado");
            }
            listaVeterinarios.add(veterinarioDTO);
            objectMapper.writeValue(new File(cC), listaVeterinarios);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void ordemListaConsultaDescrescente(){
        listaVeterinarios.sort((Comparator.comparing(VeterinarioDTO::getNumeroRegistro)));
    }


    public List<VeterinarioDTO> buscarVeterinario() {
        mapearObjeto();
        try {
            listaVeterinarios = objectMapper.readValue(new File(cC), new TypeReference<List<VeterinarioDTO>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        ordemListaConsultaDescrescente();
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

    public boolean verificaNull(VeterinarioDTO veterinarioDTO) {
        return veterinarioDTO.getCpf() == null
                || veterinarioDTO.getNome() == null
                || veterinarioDTO.getSobrenome() == null
                || veterinarioDTO.getDataNascimento() == null
                || veterinarioDTO.getNumeroRegistro() == null
                || veterinarioDTO.getEspecialidade() == null;
    }

    public void removerMedicoPorId(String id){
        try {
            String json = ReadFileUtil.readFile(cC);
            Gson gson = new Gson();
            List<VeterinarioDTO> veterinarioDTOS = gson.fromJson(json, new TypeToken<List<VeterinarioDTO>>(){}.getType());
            for (VeterinarioDTO item: veterinarioDTOS) {
                if (item.getNumeroRegistro().equals(id)){
                    if(ConsultaVeterinarioRegistrada(id)){
                        throw new PersistenceException("Impossivel excluir, existe uma consulta agendada");
                    }
                    veterinarioDTOS.remove(item);
                    break;
                }
            }
            objectMapper.writeValue(new File(cC),veterinarioDTOS );
        }catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Erro ao deletar ID");
        }
    }

    public void alterarVeterinario(VeterinarioDTO payLoad) {
        try {
            String json = ReadFileUtil.readFile(cC);
            Gson gson = new Gson();

            VeterinarioDTO registros = payLoad;
            String NumeroRegistro = registros.getNumeroRegistro();

            List<VeterinarioDTO> veterinarioDTOS = gson.fromJson(json, new TypeToken<List<VeterinarioDTO>>(){}.getType());

            Integer contNull = 0;
            Integer contOk = 0;

            for (VeterinarioDTO item: veterinarioDTOS) {
                if (registros.getNumeroRegistro() == null)
                {throw new PersistenceException("Impossivel aterar sem o numero do Registro");}

                if (item.getNumeroRegistro().equals(NumeroRegistro)){

                    if (registros.getCpf() != null){
                        if(registros.getCpf().equals(item.getCpf())){contOk++;}
                        item.comCpf(registros.getCpf());
                    }else {registros.comCpf(item.getCpf());contNull++;}

                    if (registros.getNome() != null){
                        if(registros.getNome().equals(item.getNome())){contOk++;}
                        item.comNome(registros.getNome());
                    }else {registros.comNome(item.getNome());contNull++;}

                    if (registros.getSobrenome() != null){
                        if(registros.getSobrenome().equals(item.getSobrenome())){contOk++;}
                        item.comSobrenome(registros.getSobrenome());
                    }else {registros.comSobrenome(item.getSobrenome());contNull++;}

                    if (registros.getDataNascimento() != null){
                        if(registros.getDataNascimento().equals(item.getDataNascimento())){contOk++;}
                        item.comDataNascimento(registros.getDataNascimento());
                    }else {registros.comDataNascimento(item.getDataNascimento());contNull++;}

                    if (registros.getEspecialidade() != null){
                        if(registros.getEspecialidade().equals(item.getEspecialidade())){contOk++;}
                        item.comEspecialidade(registros.getEspecialidade());
                    }else {registros.comEspecialidade(item.getEspecialidade());contNull++;}

                    ErrosNulos.erros(5,contNull,"do Registro",contOk);
                    break;
                }
            }
            objectMapper.writeValue(new File(cC),veterinarioDTOS );
        }catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Erro ao alterar ID");
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


}
