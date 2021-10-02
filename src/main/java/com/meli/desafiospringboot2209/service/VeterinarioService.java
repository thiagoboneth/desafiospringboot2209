package com.meli.desafiospringboot2209.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.dto.VeterinarioDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.VeterinarioPersistence;
import com.meli.desafiospringboot2209.util.ReadFileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioService {

    String arquivo = "veterinarios.json";
    String caminho = "db";
    String cC = caminho + "/" + arquivo;
    Gson gson = new Gson();
    List<Veterinario> listaVeterinarios = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();


    private VeterinarioPersistence veterinarioPersistence;

    public VeterinarioService(VeterinarioPersistence veterinarioPersistence) {
        this.veterinarioPersistence = veterinarioPersistence;
    }

    public Veterinario obterVeterinario(String numeroRegistro) {
        List<Veterinario> veterinarios = veterinarioPersistence.getList();
        Optional<Veterinario> any = veterinarios.stream().filter(v -> v.getNumeroRegistro().equals(numeroRegistro)).findAny();
        return any.get();
    }

    public void cadastrarVeterinario(Veterinario veterinario) {
        if (!veterinarioJaCadastrado(veterinario.getNumeroRegistro(),veterinario.getCpf())) {
            try {
                this.veterinarioPersistence.salvarVeterinarioNoArquivo(veterinario);
            } catch (RuntimeException e) {
                throw new RuntimeException("Erro ao cadastrar o Veterinário");
            }
        } else {
            throw new RuntimeException("Veterinário já cadastrado");
        }
    }

    public void ordemListaConsultaDescrescente() {
        listaVeterinarios.sort((Comparator.comparing(Veterinario::getNumeroRegistro)));
    }

    public List<Veterinario> buscarVeterinario() {
        // veterinarioPersistence.mapearObjeto();
        try {
            listaVeterinarios = objectMapper.readValue(new File(cC), new TypeReference<List<Veterinario>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        ordemListaConsultaDescrescente();
        return listaVeterinarios;
    }

    public boolean veterinarioJaCadastrado(String numeroRegistro, String cpf) {
        return veterinarioPersistence.veterinarioJaCadastrado(numeroRegistro, cpf);
    }

    public boolean verificaNull(VeterinarioDTO veterinarioDTO) {
        return veterinarioDTO.getCpf() == null
                || veterinarioDTO.getNome() == null
                || veterinarioDTO.getSobrenome() == null
                || veterinarioDTO.getDataNascimento() == null
                || veterinarioDTO.getNumeroRegistro() == null
                || veterinarioDTO.getEspecialidade() == null;
    }

        public void alterarVeterinario(Veterinario veterinario) {
        veterinarioPersistence.alterarVeterinario(veterinario);
    }
    public void removerVeterinarioPorRegistro(String numeroRegistro) {
        veterinarioPersistence.removerVeterinarioPorRegistro(numeroRegistro);
    }

    public boolean consultaVeterinarioRegistrada(String NumeroRegistro) {
        try {
            String veterinarioConsultaArquivo = ReadFileUtil.readFile("db/consultas.json");
            List<Consulta> consultas = gson.fromJson(veterinarioConsultaArquivo, new TypeToken<List<Consulta>>() {
            }.getType());
            for (Consulta item : consultas) {
                if (item.getVeterinario().getNumeroRegistro().equals(NumeroRegistro)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;

    }
}
