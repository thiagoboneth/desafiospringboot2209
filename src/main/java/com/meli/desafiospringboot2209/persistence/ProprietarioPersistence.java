package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
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

    List<ProprietarioDTO> listaProprietarios = new ArrayList<>();
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
    public void alterarProprietario(Proprietario proprietario) {
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

    // Método DELETE OK
    public void removerProprietario(String cpf) {
        if (!proprietarioRegistradoNaConsulta(cpf)) {
            List<Proprietario> proprietarioList = getList();
            Optional<Proprietario> any = proprietarioList.stream().filter(c -> c.getCpf().equals(cpf)).findAny();
            if (any.isPresent())
                proprietarioList.remove(any.get());
            try {
                objectMapper.writeValue(new File(cP), proprietarioList);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao deletar proprietário");
            }

        } else {
            throw new RuntimeException("Não é possível deletar um proprietário ao qual o paciente já está com consulta marcada");
        }
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

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

        public boolean verificaNull(Proprietario proprietario) {
        if (proprietario.getCpf() == null
                || proprietario.getNome() == null
                || proprietario.getSobrenome() == null
                || proprietario.getDataNascimento() == null
                || proprietario.getEndereco() == null
                || proprietario.getTelefone() == null) {
            try {
                return true;
            } catch (RuntimeException e) {
                throw new RuntimeException("Não é permitido cadastrar o proprietário com parâmetros nulos");
            }
        } else {
            return false;
        }
    }

/*    public List<ProprietarioDTO> buscarProprietario() {
        mapearObjeto();
        try {
            listaProprietarios = objectMapper.readValue(new File(cP), new TypeReference<List<ProprietarioDTO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaProprietarios;
    }*/
}
