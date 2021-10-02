package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.util.ReadFileUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class ConsultaPersistence implements Repository<Consulta>{

    ReadFileUtil readFileUtil = new ReadFileUtil();

    String arquivo = "consultas.json";
    String caminho = "db";
    String cC = caminho + "/" + arquivo;

    ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new Gson();

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

/*    public Consulta criarConsulta(Consulta consulta){
        //Proprietario proprietario = new Proprietario();
    }*/

    public Consulta salvarConsultaNoArquivo(Consulta consulta) {
        List<Consulta> consultas = getList();
        consultas.add(consulta);
        try {
            objectMapper.writeValue(new File(cC), consultas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return consulta;
    }

    public boolean consultaJaCadastrada(String numeroConsulta) throws IOException {
        List<Consulta> listaConsultas = getList();
        if (listaConsultas.size() > 0) {
            Optional<Consulta> any = listaConsultas.stream().filter(c -> c.getNumeroConsulta().equals(numeroConsulta)).findAny();
            if(!any.isPresent()){
                throw new RuntimeException("Numero da consulta nao eiste");
            }
            return true;
        }
        return false;
    }

    public List<Consulta> consultasDoDia(LocalDate data) throws IOException {
        List<Consulta> consultas = getList();
        List<Consulta> consultasDoDia = consultas.stream().filter(c -> c.getDataHora().equals(data)).collect(Collectors.toList());
        consultasDoDia.sort((Consulta c1, Consulta c2) -> c1.getDataHora().compareTo(c2.getDataHora()));
        return consultasDoDia;
    }

    private Consulta atualizaConsulta(Consulta consultaExistente, Consulta consultaNova){
        consultaExistente.setNumeroColeira(consultaNova.getNumeroColeira());
        //consultaExistente.setProprietario(consultaNova.getProprietario());
        consultaExistente.setDataHora(consultaNova.getDataHora());
        consultaExistente.setDiagnostico(consultaNova.getDiagnostico());
        consultaExistente.setMotivo(consultaNova.getMotivo());
        consultaExistente.setTratamento(consultaNova.getTratamento());
        //consultaExistente.setPaciente(consultaNova.getPaciente());
        consultaExistente.setVeterinario(consultaNova.getVeterinario());
        return consultaExistente;
    }


    public void alterarConsulta(Consulta consulta) {
        List<Consulta> todasAsConsultas = getList();
        Optional<Consulta> aConsulta = todasAsConsultas.stream().filter(c -> c.getNumeroConsulta().equals(consulta.getNumeroConsulta())).findAny();
        if(aConsulta.isPresent()){
            Consulta c = atualizaConsulta(aConsulta.get(), consulta);
            todasAsConsultas.set(todasAsConsultas.indexOf(c), c);
        }
        try {
            objectMapper.writeValue(new File(cC), todasAsConsultas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removerConsultaPorId(String numeroConsulta) {
        List<Consulta> consultas = getList();
        Optional<Consulta> any = consultas.stream().filter(c -> c.getNumeroConsulta().equals(numeroConsulta)).findAny();
        if(any.isPresent())
            consultas.remove(any.get());
        try {
            objectMapper.writeValue(new File(cC), consultas);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar ID");
        }
    }

    public List<String> listarTotalCadaVeterinario() throws IOException {
        String consultaArquivo = ReadFileUtil.readFile("db/consultas.json");
        List<ConsultaDTO> consultaDTOS = gson.fromJson(consultaArquivo, new TypeToken<List<ConsultaDTO>>() {}.getType());

        List<String> contagemConsultasMedicos= new ArrayList<>();

        for (ConsultaDTO item : consultaDTOS) {
            long count = consultaDTOS.stream().filter(x -> x.getNumeroRegistroVeterinario().equals(item.getNumeroRegistroVeterinario())).count();

            if (contagemConsultasMedicos.contains("Registro do Médico: "+item.getNumeroRegistroVeterinario() + ", Número de consultas realizadas por ele: " + count) == false ) {

                contagemConsultasMedicos.add("Registro do Médico: "+item.getNumeroRegistroVeterinario() + ", Número de consultas realizadas por ele: " + count);
            }
        }
        return contagemConsultasMedicos;
    }

    public void ordenaConsultaPorNome(List<ConsultaDTO> consultas){
        //Collections.sort(consultas,((o1, o2) -> o1.getNomeProprietario().compareToIgnoreCase(o2.getNomeProprietario())));
    }


//    public List<ConsultaDTO> consultaPaciente() throws IOException {
//
//        String consultaPacienteArquivo = ReadFileUtil.readFile("db/consultas.json");
//        List<ConsultaDTO> consultaPacienteDTOS = gson.fromJson(consultaPacienteArquivo, new TypeToken<List<ConsultaDTO>>() {}.getType());
//
//        List<ConsultaDTO> consultas = consultaPacienteDTOS.stream()
//                                .sorted(Comparator.comparing(ConsultaDTO::getNomeProprietario))
//                                        .collect(Collectors.toList());
//         ordenaConsultaPorNome(consultas);
//
//        return consultas;
//
//
//    }

    @Override
    public List<Consulta> getList() {
        List<Consulta> consultas = new ArrayList<>();
        try {
            String consultaArquivo = ReadFileUtil.readFile("db/consultas.json");
            consultas = gson.fromJson(consultaArquivo, new TypeToken<List<Consulta>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return consultas;
    }

    public List<Consulta> getList(String registro){
        List<Consulta> list = getList();
        List<Consulta> consultasDoVeterinario = list.stream().filter(c -> c.getVeterinario().getNumeroRegistro().equals(registro)).collect(Collectors.toList());
        return consultasDoVeterinario;
    }
/*    @Override
  public List<Paciente> getList(String NumeroDaColeira){
        List<Paciente> list = getList();
        List<Paciente> consultasDoPaciente = list.stream().filter(c -> c.getNumeroColeira().equals(NumeroDaColeira)).collect(Collectors.toList());
        return consultasDoPaciente;
    }*/

/*    public List<Proprietario>getListProprietario(String registro){
        List<Proprietario> list = getListProprietario();
        
    }*/
}
