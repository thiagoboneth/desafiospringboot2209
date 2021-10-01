package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.dto.PacienteDTO;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.util.ReadFileUtil;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class PacientePersistence implements GetList<Paciente> {

    String arquivo = "pacientes.json";
    String caminho = "db";
    String cC = caminho+"/"+arquivo;
    Gson gson = new Gson();

    List<PacienteDTO> listaPacientes = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public PacienteDTO salvarPacienteNoArquivo(PacienteDTO pacienteDTO) {
        mapearObjeto();
        listaPacientes = buscarPaciente();

        try {
            if (verificaNull(pacienteDTO)) {
                throw new RuntimeException("Os campos não podem ser nulos");
            }

            if (pacienteJaCadastrado(pacienteDTO.getNumeroColeira())) {
                throw new RuntimeException("paciente já cadastrado");
            }

            listaPacientes.add(pacienteDTO);
            objectMapper.writeValue(new File(cC), listaPacientes);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return pacienteDTO;
    }

    public void ordemListaPacienteCrescente(){
        Collections.sort(listaPacientes,((o1, o2) -> o1.getCpfProprietario().compareTo(o2.getCpfProprietario())));
    }

    public List<PacienteDTO> buscarPaciente() {
        mapearObjeto();
        try {
            listaPacientes = objectMapper.readValue(new File(cC), new TypeReference<List<PacienteDTO>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        ordemListaPacienteCrescente();
        return listaPacientes;
    }

    public boolean pacienteJaCadastrado(String numeroColeira) throws IOException {
        listaPacientes = buscarPaciente();
        System.out.println(numeroColeira);
        if (listaPacientes.size() > 0) {
            System.out.println(numeroColeira);
            for (PacienteDTO pacienteDTO : listaPacientes) {
                if (pacienteDTO.getNumeroColeira().equals(numeroColeira)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean verificaNull(PacienteDTO pacienteDTO) {
        if (pacienteDTO.getEspecie() == null
                || pacienteDTO.getRaca() == null
                || pacienteDTO.getCor() == null
                || pacienteDTO.getDataNascimento() == null
                || pacienteDTO.getNome() == null
                || pacienteDTO.getSexo() == null
                || pacienteDTO.getNumeroColeira() == null) {
           return true;
        } else {
            return false;
        }
    }

    public void removerPacientePorId(String id){
        try {
            String json = ReadFileUtil.readFile(cC);
            Gson gson = new Gson();
            List<PacienteDTO> pacienteDTOS = gson.fromJson(json, new TypeToken<List<PacienteDTO>>(){}.getType());
            for (PacienteDTO item: pacienteDTOS) {
                if (item.getNumeroColeira().equals(id)){
                    if(ConsultaPacienteRegistrada(id)){
                        throw new RuntimeException("Impossivel excluir, existe uma consulta agendada");
                    }
                    pacienteDTOS.remove(item);
                    break;
                }
            }
            objectMapper.writeValue(new File(cC),pacienteDTOS);
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar ID");
        }
    }


    public void alterarPaciente(PacienteDTO payLoad) {
        try {
            String json = ReadFileUtil.readFile(cC);
            Gson gson = new Gson();

            PacienteDTO registros = payLoad;
            String NumeroColeira = registros.getNumeroColeira();

            List<PacienteDTO> pacienteDTOS = gson.fromJson(json, new TypeToken<List<PacienteDTO>>() {
            }.getType());
            for (PacienteDTO item : pacienteDTOS) {
                if (item.getNumeroColeira().equals(NumeroColeira)) {
                    item.comEspecie(registros.getEspecie());
                    item.comRaca(registros.getRaca());
                    item.comCor(registros.getCor());
                    item.comDataNascimento(registros.getDataNascimento());
                    item.comNome(registros.getNome());
                    item.comSexo(registros.getSexo());
                    item.comCpfProprietario(registros.getCpfProprietario());
                    break;
                }
            }
            objectMapper.writeValue(new File(cC), pacienteDTOS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao alterar ID");
        }
    }


    public boolean ConsultaPacienteRegistrada(String NumeroColeira){
        try {
            String pacienteConsultaArquivo = ReadFileUtil.readFile("db/consultas.json");
            List<ConsultaDTO> ConsultaDTOS = gson.fromJson(pacienteConsultaArquivo, new TypeToken<List<ConsultaDTO>>(){}.getType());
            for (ConsultaDTO item: ConsultaDTOS) {
                if (item.getNumeroColeira().equals(NumeroColeira)){
                    return true;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public List<Paciente> getList() {
        List<Paciente> pacientes = new ArrayList<>();
        try {
            String consultaArquivo = ReadFileUtil.readFile("db/pacientes.json");
            pacientes = gson.fromJson(consultaArquivo, new TypeToken<List<Paciente>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    public void salvarConsultaNoArquivo(Paciente paciente) {
    }
}

