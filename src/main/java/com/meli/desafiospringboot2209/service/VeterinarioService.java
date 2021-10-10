package com.meli.desafiospringboot2209.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.VeterinarioPersistence;
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
    List<Veterinario> listaVeterinarios = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    private VeterinarioPersistence veterinarioPersistence;

    public VeterinarioService(VeterinarioPersistence veterinarioPersistence) {
        this.veterinarioPersistence = veterinarioPersistence;
    }

    public VeterinarioService() {

    }

    //Método Post
    public boolean cadastrarVeterinario(Veterinario veterinario) {
        if (!veterinarioJaCadastrado(veterinario.getNumeroRegistro(), veterinario.getCpf())) {
            try {
                veterinarioPersistence.verificaNull(veterinario);
                veterinarioPersistence.salvarVeterinarioNoArquivo(veterinario);

            } catch (RuntimeException e) {throw new RuntimeException("Não é permitido cadastrar o veterinario com algum parâmetro nulo"); }
        } else { throw new RuntimeException("Veterinário já cadastrado"); }
        return true;
    }

    //Método Get
    public List<Veterinario> buscarVeterinario() {
        try {
            listaVeterinarios = objectMapper.readValue(new File(cC), new TypeReference<List<Veterinario>>() {});
        } catch (IOException e) { e.printStackTrace();
        }
        ordemListaConsultaCrescente();
        return listaVeterinarios;
    }

    //Usado no Método Get
    public void ordemListaConsultaCrescente() {
        listaVeterinarios.sort((Comparator.comparing(Veterinario::getNumeroRegistro)));
    }

    //Usado no Método Get
    public boolean veterinarioJaCadastrado(String numeroRegistro, String cpf) {
        return veterinarioPersistence.veterinarioJaCadastrado(numeroRegistro, cpf);
    }

    //Método Put
    public boolean alterarVeterinario(Veterinario veterinario) {
        try {
            veterinarioPersistence.verificaNull(veterinario);
            veterinarioPersistence.alterarVeterinario(veterinario); }catch (RuntimeException e){ throw new RuntimeException("Não é permitido Alterar o veterinario passando algum parâmetro nulo");
        }
        return true;
      }

    //Método Delete
    public boolean removerVeterinario(String numeroRegistro) {
        try {
            veterinarioPersistence.removerVeterinario(numeroRegistro);
        }catch (RuntimeException e){ throw new RuntimeException("Não é possível deletar um veterinário"); }
        return true;
    }

    //Usado em Consulta
    public Veterinario obterVeterinario(String numeroRegistro) {
        List<Veterinario> veterinarios = veterinarioPersistence.getList();
        Optional<Veterinario> any = veterinarios.stream().filter(v -> v.getNumeroRegistro().equals(numeroRegistro)).findAny();
        if (!any.isPresent()) { throw new RuntimeException("Numero de veterinário nulo");
        }
        return any.get();
    }
}
