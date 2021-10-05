package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.util.ReadFileUtil;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProprietarioPersistence implements GetList<Proprietario> {

    String arquivo = "proprietarios.json";
    String caminho = "db";
    String cP = caminho + "/" + arquivo;
    Gson gson = new Gson();

    ObjectMapper objectMapper = new ObjectMapper();

    // Método POST OK
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

    // Método PUT OK
    public boolean alterarProprietario(Proprietario proprietario) {
        try {
            String json = ReadFileUtil.readFile(cP);
            Gson gson = new Gson();

            Proprietario registro = proprietario;
            String cpf = registro.getCpf();

            List<Proprietario> proprietarios = gson.fromJson(json, new TypeToken<List<Proprietario>>() {
            }.getType());
            for (Proprietario item : proprietarios) {
                if (item.getCpf().equals(cpf)) {
                    item.comNome(registro.getNome());
                    item.comSobrenome(registro.getSobrenome());
                    item.comDataNascimento(registro.getDataNascimento());
                    item.comEndereco(registro.getEndereco());
                    item.comTelefone(registro.getTelefone());
                    break;
                }
            }
            objectMapper.writeValue(new File(cP), proprietarios);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao alterar o proprietário");
        }
        return true;
    }

    // Método DELETE OK
    public boolean proprietarioJaCadastrado(String cpf) {
        List<Proprietario> listasProprietarios = getList();

        if (listasProprietarios.size() > 0) {
            Optional<Proprietario> any = listasProprietarios.stream().filter(p -> p.getCpf().equals(cpf)).findAny();
            return any.isPresent();
        }
        return false;
    }

    // Método DELETE - Falta implementar a verificacao se o paciente está em uma consulta, logo não pode deletar o proprietário
    public boolean removerProprietario(String cpf) {
        List<Proprietario> proprietarioList = getList();
        Optional<Proprietario> any = proprietarioList.stream().filter(c -> c.getCpf().equals(cpf)).findAny();
        try {
            any.ifPresent(proprietarioList::remove);
            objectMapper.writeValue(new File(cP), proprietarioList);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar o proprietário");
        }
        return true;
    }

    public boolean proprietarioRegistradoNaConsulta(String Cpf) {
        try {
            String proprietarioConsultaArquivo = ReadFileUtil.readFile("db/consultas.json");
            List<Consulta> consultaList = gson.fromJson(proprietarioConsultaArquivo, new TypeToken<List<Consulta>>() {
            }.getType());
            for (Consulta item : consultaList) {
                if (item.getProprietario().getCpf().equals(Cpf)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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

    public List<Proprietario> getList(String cpf) {
        List<Proprietario> list = getList();
        List<Proprietario> proprietarios = list.stream().filter(c -> c.getCpf().equals(cpf)).collect(Collectors.toList());
        return proprietarios;
    }

    public void verificaNull(Proprietario proprietario) {
        if (proprietario.getCpf() == null
                || proprietario.getNome() == null
                || proprietario.getSobrenome() == null
                || proprietario.getDataNascimento() == null
                || proprietario.getEndereco() == null
                || proprietario.getTelefone() == null) {
            throw new RuntimeException("Não é permitido cadastrar o proprietário com parâmetros nulos");
        }
    }
}
