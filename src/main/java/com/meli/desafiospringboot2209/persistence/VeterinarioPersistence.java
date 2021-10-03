package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.VeterinarioDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.util.ReadFileUtil;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VeterinarioPersistence implements GetList {

    String arquivo = "veterinarios.json";
    String caminho = "db";
    String cC = caminho + "/" + arquivo;
    Gson gson = new Gson();
    ObjectMapper objectMapper = new ObjectMapper();

    // Método Post
    public Veterinario salvarVeterinarioNoArquivo(Veterinario veterinario) {
        if (!verificaNull(veterinario)) {
            List<Veterinario> veterinarios = getList();
            veterinarios.add(veterinario);
            try {
                objectMapper.writeValue(new File(cC), veterinarios);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return veterinario;

    }

    // Método Post
    public boolean veterinarioJaCadastrado(String numeroRegistro, String cpf) {
        List<Veterinario> veterinarios = getList();
        if (veterinarios.size() > 0) {
            Optional<Veterinario> anyNumeroRegistro = veterinarios.stream().filter(c -> c.getNumeroRegistro().equals(numeroRegistro)).findAny();
            Optional<Veterinario> anyCpf = veterinarios.stream().filter(c -> c.getCpf().equals(cpf)).findAny();
            return anyNumeroRegistro.isPresent() || anyCpf.isPresent();
        }
        return false;
    }

    //Métodos Post e Put
    public boolean verificaNull(Veterinario veterinario) {
        if (veterinario.getCpf() == null
                || veterinario.getNome() == null
                || veterinario.getSobrenome() == null
                || veterinario.getDataNascimento() == null
                || veterinario.getNumeroRegistro() == null
                || veterinario.getEspecialidade() == null) {
            return true;
        } else {
            return false;
        }
    }

    // Método Get
    public List<Veterinario> getList() {
        List<Veterinario> veterinarios = new ArrayList<>();
        try {
            String consultaArquivo = ReadFileUtil.readFile("db/veterinarios.json");
            veterinarios = gson.fromJson(consultaArquivo, new TypeToken<List<Veterinario>>() {
            }.getType());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return veterinarios;
    }

    //Método Put
    public void alterarVeterinario(Veterinario veterinario) {
        try {
            String json = ReadFileUtil.readFile(cC);
            Gson gson = new Gson();
            Veterinario registro = veterinario;
            String NumeroRegistro = registro.getNumeroRegistro();

            List<VeterinarioDTO> veterinarioDTOS = gson.fromJson(json, new TypeToken<List<VeterinarioDTO>>() {
            }.getType());
            for (VeterinarioDTO item : veterinarioDTOS) {
                if (item.getNumeroRegistro().equals(NumeroRegistro)) {
                    item.comCpf(registro.getCpf());
                    item.comNome(registro.getNome());
                    item.comSobrenome(registro.getSobrenome());
                    item.comDataNascimento(registro.getDataNascimento());
                    item.comEspecialidade(registro.getEspecialidade());
                    break;
                }
            }
            objectMapper.writeValue(new File(cC), veterinarioDTOS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao alterar ID");
        }
    }

    //Método Delete
    public void removerVeterinarioPorRegistro(String numeroRegistro) {
        if (!veterinarioRegistradoEmConsulta(numeroRegistro)) {
            List<Veterinario> veterinariosList = getList();
            Optional<Veterinario> any = veterinariosList.stream().filter(c -> c.getNumeroRegistro().equals(numeroRegistro)).findAny();
            if (any.isPresent())
                veterinariosList.remove(any.get());
            try {
                objectMapper.writeValue(new File(cC), veterinariosList);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao deletar Veterinário");
            }
        } else {
            throw new RuntimeException("Não é permitido excluir o veterinario,ele está cadastrada em uma consulta.");
        }

    }

    // Método Delete
    public boolean veterinarioRegistradoEmConsulta(String NumeroRegistro) {
        try {
            String veterinarioConsultaArquivo = ReadFileUtil.readFile("db/consultas.json");
            List<Consulta> consultasList = gson.fromJson(veterinarioConsultaArquivo, new TypeToken<List<Consulta>>() {
            }.getType());
            for (Consulta item : consultasList) {
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
