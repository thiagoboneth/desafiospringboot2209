package com.meli.desafiospringboot2209.service;


import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.persistence.ProprietarioPersistence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioService {

    private ProprietarioPersistence proprietarioPersistence;

    public ProprietarioService(ProprietarioPersistence proprietarioPersistence) {
        this.proprietarioPersistence = proprietarioPersistence;
    }

    public Proprietario obterProprietario(String cpf){
        List<Proprietario> veterinarios = proprietarioPersistence.getList();
        Optional<Proprietario> any = veterinarios.stream().filter(v -> v.getCpf().equals(cpf)).findAny();
        return any.get();
    }
}