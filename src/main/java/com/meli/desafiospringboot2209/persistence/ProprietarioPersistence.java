package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.util.ReadFileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class ProprietarioPersistence implements Repository<Proprietario>{

    String arquivo = "proprietarios.json";
    String caminho = "db";
    String cP = caminho+"/"+arquivo;
    Gson gson = new Gson();

    List<ProprietarioDTO> listaProprietarios = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public Proprietario salvarProprietarioNoArquivo(Proprietario proprietario) {
        List<Proprietario> proprietarios = getList();
        proprietarios.add(proprietario);
        try {
            objectMapper.writeValue(new File(cP), proprietarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proprietario;
    }

/*    public boolean verificaNull(ProprietarioDTO proprietarioDTO) {
        if (proprietarioDTO.getCpf() == null
                || proprietarioDTO.getNome() == null
                || proprietarioDTO.getSobrenome() == null
                || proprietarioDTO.getDataNascimento() == null
                || proprietarioDTO.getEndereco() == null
                || proprietarioDTO.getTelefone() == null) {
            return true;
        } else {
            return false;
        }
    }*/

/*    public List<ProprietarioDTO> buscarProprietario() {
        mapearObjeto();
        try {
            listaProprietarios = objectMapper.readValue(new File(cP), new TypeReference<List<ProprietarioDTO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaProprietarios;
    }*/

    public void removerProprietarioPorCpf(String cpf) {
        try {
            String json = ReadFileUtil.readFile(cP);
            Gson gson = new Gson();
            List<Proprietario> proprietario = gson.fromJson(json, new TypeToken<List<Proprietario>>() {
            }.getType());
            for (Proprietario item : proprietario) {
                if (item.getCpf().equals(cpf)) {
                    //(ConsultaProprietarioRegistrada(cpf)){
                    if(false){
                        throw new RuntimeException("Impossivel excluir, existe uma consulta agendada");
                    }
                    proprietario.remove(item);
                    break;
                }
            }
            objectMapper.writeValue(new File(cP), proprietario);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar ID");
        }
    }


/*    public void atualizarProprietario(ProprietarioDTO payLoad) {
        try {
            String json = ReadFileUtil.readFile(cP);
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
            objectMapper.writeValue(new File(cP), proprietarioDTOS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao alterar ID");
        }
    }*/

/*    public boolean ConsultaProprietarioRegistrada(String Cpf){
        try {
            String proprietarioConsultaArquivo = ReadFileUtil.readFile("db/consultas.json");
            List<ConsultaDTO> ConsultaS = gson.fromJson(proprietarioConsultaArquivo, new TypeToken<List<ConsultaDTO>>(){}.getType());
            for (ConsultaDTO item: ConsultaS) {
                if (item.getCpfProprietario().equals(Cpf)){
                    return true;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }*/

    public boolean proprietarioJaCadastrado(String cpf) {
        List<Proprietario> listasProprietarios = getList();
        if (listasProprietarios.size() > 0) {
            Optional<Proprietario> any = listasProprietarios.stream().filter(p -> p.getCpf().equals(cpf)).findAny();
            return any.isPresent();
        }
        return false;
    }

    @Override
    public List<Proprietario> getList() {
        List<Proprietario> proprietarios = new ArrayList<>();
        try {
            String consultaArquivo = ReadFileUtil.readFile("db/proprietarios.json");
            proprietarios = gson.fromJson(consultaArquivo, new TypeToken<List<Proprietario>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proprietarios;
    }

    public List<Proprietario> getList(String cpf){
        List<Proprietario> list = getList();
        List<Proprietario> proprietarios = list.stream().filter(c -> c.getCpf().equals(cpf)).collect(Collectors.toList());
        return proprietarios;
    }
}
