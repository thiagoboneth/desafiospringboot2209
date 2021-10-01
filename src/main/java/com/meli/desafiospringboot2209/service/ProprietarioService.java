package com.meli.desafiospringboot2209.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.persistence.ProprietarioPersistence;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioService {

    private ProprietarioPersistence proprietarioPersistence;

    List<Proprietario> proprietariosLista = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new Gson();

    public ProprietarioService(ProprietarioPersistence proprietarioPersistence) {
        this.proprietarioPersistence = proprietarioPersistence;
    }

/*    public Proprietario obterProprietario(String cpf){
        List<Proprietario> proprietarios = proprietarioPersistence.getList();
        Optional<Proprietario> any = proprietarios.stream().filter(v -> v.getCpf().equals(cpf)).findAny();
        if (!any.isPresent()) {
            throw new RuntimeException("Número do cpf não pode ser nulo");
        }
        return any.get();
    }*/

    public void cadastrarProprietario(Proprietario proprietario) {
        try{
            if (proprietarioJaCadastrado(proprietario.getCpf())) {
                throw new RuntimeException("Proprietário já cadastrado");
            }
            this.proprietarioPersistence.salvarProprietarioNoArquivo(proprietario);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error ao cadastrar o proprietário");
        }
    }

    public boolean proprietarioJaCadastrado(String cpf) {
        return this.proprietarioPersistence.proprietarioJaCadastrado(cpf);
    }



/*    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }*/

    public List<Proprietario> buscarProprietario() {
        //mapearObjeto();

        try {
            proprietariosLista = objectMapper.readValue(new File("db/proprietarios"), new TypeReference<List<Proprietario>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proprietariosLista;
    }

    public void remover(String cpf) {

    }

}
