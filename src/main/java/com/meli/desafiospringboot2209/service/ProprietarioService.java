package com.meli.desafiospringboot2209.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.persistence.ProprietarioPersistence;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioService {

    private ProprietarioPersistence proprietarioPersistence;

    List<Proprietario> proprietariosLista = new ArrayList<>();

    String arquivo = "proprietarios.json";
    String caminho = "db";
    String cP = caminho + "/" + arquivo;
    ObjectMapper objectMapper = new ObjectMapper();

    public ProprietarioService(ProprietarioPersistence proprietarioPersistence) {
        this.proprietarioPersistence = proprietarioPersistence;
    }

    // Método POST - Falta verificar cadastro com parametros nulos
    public void cadastrarProprietario(Proprietario proprietario) {

        if (!proprietarioJaCadastrado(proprietario.getCpf())) {
            try{
                proprietarioPersistence.verificaNull(proprietario);
                proprietarioPersistence.salvarProprietarioNoArquivo(proprietario);
            } catch (RuntimeException e) {
                throw new RuntimeException("Erro ao cadastrar o proprietário");
            }
        } else {
            throw new RuntimeException("Proprietário já cadastrado");
        }
    }

    // Método GET OK
    public List<Proprietario> buscarProprietario() {
        try {
            proprietariosLista = objectMapper.readValue(new File(cP), new TypeReference<List<Proprietario>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        ordemListaProprietariosCrescente();
        return proprietariosLista;
    }

    // Método PUT - Falta verificar alteracao com parametros nulos
    public void alterarProprietario(Proprietario proprietario) {
        proprietarioPersistence.verificaNull(proprietario);
        proprietarioPersistence.alterarProprietario(proprietario);
    }

    // Método DELETE
    public void removerProprietario(String cpf) {
        proprietarioPersistence.proprietarioJaCadastrado(cpf);
        proprietarioPersistence.removerProprietario(cpf);
    }

    public void ordemListaProprietariosCrescente() {
        proprietariosLista.sort((Comparator.comparing(Proprietario::getCpf)));
    }

    public boolean proprietarioJaCadastrado(String cpf) {
        return this.proprietarioPersistence.proprietarioJaCadastrado(cpf);
    }

    public Proprietario obterProprietario(String cpf){
        List<Proprietario> proprietarios = proprietarioPersistence.getList(cpf);
        Optional<Proprietario> any = proprietarios.stream().filter(v -> v.getCpf().equals(cpf)).findAny();
        if (!any.isPresent()) {
            throw new RuntimeException("Número do cpf não pode ser nulo");
        }
        return any.get();
    }

}
