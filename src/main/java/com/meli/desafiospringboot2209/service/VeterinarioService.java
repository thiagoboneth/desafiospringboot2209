package com.meli.desafiospringboot2209.service;

import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.VeterinarioPersistence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioService {

    private VeterinarioPersistence veterinarioPersistence;

    public VeterinarioService(VeterinarioPersistence veterinarioPersistence) {
        this.veterinarioPersistence = veterinarioPersistence;
    }

    public Veterinario obterVeterinario(String numeroRegistro){
        List<Veterinario> veterinarios = veterinarioPersistence.getList();
        Optional<Veterinario> any = veterinarios.stream().filter(v -> v.getNumeroRegistro().equals(numeroRegistro)).findAny();
        return any.get();
    }
}
