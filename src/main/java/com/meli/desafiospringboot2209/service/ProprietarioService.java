package com.meli.desafiospringboot2209.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.persistence.ProprietarioPersistence;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public ProprietarioService(ProprietarioPersistence proprietarioPersistence) {
        this.proprietarioPersistence = proprietarioPersistence;
    }

    // Método POST - OK
    public boolean cadastrarProprietario(Proprietario proprietario) {

        if (!proprietarioJaCadastrado(proprietario.getCpf())) {
            try{
                proprietarioPersistence.verificaNull(proprietario);
                proprietarioPersistence.salvarProprietarioNoArquivo(proprietario);
            } catch (RuntimeException e) {
                throw new RuntimeException("Não é permitido cadastrar o proprietário com parâmetros nulos");
            }
        } else {
            throw new RuntimeException("Proprietário já cadastrado");
        }
        return true;
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

    // Método PUT - OK
    public boolean alterarProprietario(Proprietario proprietario) {
        try{
            proprietarioPersistence.verificaNull(proprietario);
            proprietarioPersistence.alterarProprietario(proprietario);
        } catch (RuntimeException e) {
            throw new RuntimeException("Não é permitido cadastrar o proprietário com parâmetros nulos");
        }
        return true;
    }

    // Método DELETE - Falta implementar a verificacao se o paciente está em uma consulta, logo não pode deletar o proprietário
    public boolean removerProprietario(String cpf) {
        try {
            proprietarioPersistence.proprietarioJaCadastrado(cpf);
            proprietarioPersistence.removerProprietario(cpf);
        } catch (RuntimeException e) {
            throw new RuntimeException("Não é possível deletar um proprietário ao qual o paciente já está com consulta marcada");
        }
        return true;
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
