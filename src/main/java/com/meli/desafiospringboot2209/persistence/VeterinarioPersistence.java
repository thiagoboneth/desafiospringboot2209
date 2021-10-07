package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.entity.Consulta;
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
        List<Veterinario> veterinarios = getList();
        veterinarios.add(veterinario);
        try {
            objectMapper.writeValue(new File(cC), veterinarios);
            return veterinario;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return veterinario;
    }

    // Usado no Método Post
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
    public void verificaNull(Veterinario veterinario) {
        if (veterinario.getCpf() == null
                || veterinario.getNome() == null
                || veterinario.getSobrenome() == null
                || veterinario.getDataNascimento() == null
                || veterinario.getNumeroRegistro() == null
                || veterinario.getEspecialidade() == null) {
            throw new RuntimeException("Não permitido cadastrar");
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
    public boolean alterarVeterinario(Veterinario veterinario) {
        try {
            String json = ReadFileUtil.readFile(cC);
            Gson gson = new Gson();
            Veterinario registro = veterinario;
            String NumeroRegistro = registro.getNumeroRegistro();

            List<Veterinario> veterinarioList = gson.fromJson(json, new TypeToken<List<Veterinario>>() {
            }.getType());
            for (Veterinario item : veterinarioList) {
                if (item.getNumeroRegistro().equals(NumeroRegistro)) {
                    item.comCpf(registro.getCpf());
                    item.comNome(registro.getNome());
                    item.comSobrenome(registro.getSobrenome());
                    item.comDataNascimento(registro.getDataNascimento());
                    item.comEspecialidade(registro.getEspecialidade());
                    break;
                }
            }
            objectMapper.writeValue(new File(cC), veterinarioList);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao alterar o Veterinario");
        }
        return true;
    }

    //Método Delete
    public boolean removerVeterinarioPorRegistro(String numeroRegistro) {
        List<Veterinario> veterinariosList = getList();
        Optional<Veterinario> any = veterinariosList.stream().filter(c -> c.getNumeroRegistro().equals(numeroRegistro)).findAny();
        if(any.isPresent())
            veterinariosList.remove(any.get());
        try {
            objectMapper.writeValue(new File(cC), veterinariosList);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar ID");
        }
        return true;
    }
    /*public void removerVeterinarioPorRegistro(String numeroRegistro) {
        if (!veterinarioRegistradoEmConsulta(numeroRegistro)) {
            List<Veterinario> veterinariosList = getList();
            Optional<Veterinario> any = veterinariosList.stream().filter(c -> c.getNumeroRegistro().equals(numeroRegistro)).findAny();
            if (any.isPresent())
                veterinariosList.remove(any.get());
            try {
                objectMapper.writeValue(new File(cC), veterinariosList);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao deletar ID");
            }
        } else {
            throw new RuntimeException("Não é permitido excluir o veterinario,ele está cadastrada em uma consulta.");
        }
    }
*/
    // Usado no Método Delete
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
        }
        return false;
    }

}
