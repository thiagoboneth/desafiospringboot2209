package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.dto.PacienteDTO;
import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
import com.meli.desafiospringboot2209.exception.PersistenceException;
import com.meli.desafiospringboot2209.utils.ErrosNulos;
import com.meli.desafiospringboot2209.utils.ReadFileUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaPersistence {

    String arquivoConsultas = "consultas.json";
    String arquivoPacientes = "pacientes.json";
    String caminho = "db";
    String cConsultas= caminho + "/" + arquivoConsultas;
    String cPacientes = caminho + "/" + arquivoPacientes;

    List<ConsultaDTO> listaConsultas = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new Gson();

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    // Teste unitário OK
    public ConsultaDTO dadosProprietario(ConsultaDTO consultaDTO) throws IOException {

        String coleira = consultaDTO.getNumeroColeira();

        String consultaArquivo = ReadFileUtil.readFile(cPacientes);
        List<PacienteDTO> pacienteDTOS = gson.fromJson(consultaArquivo, new TypeToken<List<PacienteDTO>>() {
        }.getType());

        for (PacienteDTO item : pacienteDTOS) {
            if (item.getNumeroColeira().equals(coleira)) {
                consultaDTO.comCpfProprietario(item.getCpfProprietario());
                break;
            }
        }

        String cpfProprietario = consultaDTO.getCpfProprietario();

        String consultaProprietarioArquivo = ReadFileUtil.readFile("db/proprietario.json");
        List<ProprietarioDTO> proprietarioDTOS = gson.fromJson(consultaProprietarioArquivo, new TypeToken<List<ProprietarioDTO>>() {
        }.getType());

        for (ProprietarioDTO item1 : proprietarioDTOS) {
            if (item1.getCpf().equals(cpfProprietario)) {
                consultaDTO.comNomeProprietario(item1.getNome());
                break;
            }
        }
        return consultaDTO;
    }

    // Teste unitário OK
    public ConsultaDTO salvarConsultaNoArquivo(ConsultaDTO consultaDTO) {
        mapearObjeto();
        listaConsultas = buscarConsulta();

        try {
            if (verificaNull(consultaDTO)) {
                throw new PersistenceException("É necessário o número da coleira e o número de registro do veterinário");
            }

            if (consultaJaCadastrada(consultaDTO.getNumeroConsulta())) {
                throw new PersistenceException("Consulta já cadastrada");
            }

            listaConsultas.add(consultaDTO);

            dadosProprietario(consultaDTO);

            objectMapper.writeValue(new File(cConsultas), listaConsultas);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Error na escrita do arquivo");
        }
        return consultaDTO;
    }

    public boolean consultaJaCadastrada(String numeroConsulta) {
        listaConsultas = buscarConsulta();
        if (listaConsultas.size() > 0) {
            for (ConsultaDTO consultaDTO : listaConsultas) {
                if (consultaDTO.getNumeroConsulta().equals(numeroConsulta)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean verificaNull(ConsultaDTO consultaDTO) {
        return consultaDTO.getNumeroColeira() == null || consultaDTO.getNumeroRegistroVeterinario() == null;
    }

    public void ordemListaConsultaDescrescente(){
        listaConsultas.sort(((o1, o2) -> o2.getDataHora().compareTo(o1.getDataHora())));
    }

    public void ordemListaConsultaCrescente(){
        listaConsultas.sort((Comparator.comparing(ConsultaDTO::getDataHora)));
    }

    public List<ConsultaDTO> consultasDoDia(String data) throws IOException {

        String[] diaConvertida = data.split("-");

        int ano = Integer.parseInt(diaConvertida[2]);
        int mes = Integer.parseInt(diaConvertida[1]);
        int dia = Integer.parseInt(diaConvertida[0]);

        LocalDate dataConvertida = LocalDate.of(ano,mes,dia);

        String json = ReadFileUtil.readFile(cConsultas);
        Gson gson = new Gson();

        List<ConsultaDTO> consultaDTOS = gson.fromJson(json, new TypeToken<List<ConsultaDTO>>(){}.getType());

        List<ConsultaDTO> consultas = consultaDTOS.stream()
                .filter(item -> LocalDateTime.parse(item.getDataHora()).toLocalDate().equals(dataConvertida))
                .sorted(Comparator.comparing(ConsultaDTO::getDataHora))
                .collect(Collectors.toList());

        ordemListaConsultaCrescente();
        return consultas;
    }


    public List<ConsultaDTO> buscarConsulta() {
        mapearObjeto();
        try {
            listaConsultas = objectMapper.readValue(new File(cConsultas), new TypeReference<List<ConsultaDTO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        ordemListaConsultaDescrescente();
        return listaConsultas;
    }

    public void alterarConsulta(ConsultaDTO payLoad) {
        try {
            String json = ReadFileUtil.readFile(cConsultas);
            Gson gson = new Gson();

            String numeroConsulta = payLoad.getNumeroConsulta();

            List<ConsultaDTO> consultaDTOS = gson.fromJson(json, new TypeToken<List<ConsultaDTO>>() {
            }.getType());

            Integer contNull = 0;
            Integer contOk = 0;

            for (ConsultaDTO item : consultaDTOS) {
                if (payLoad.getNumeroConsulta() == null)
                {throw new PersistenceException("Impossivel aterar sem o numero da consulta");}

                if (item.getNumeroConsulta().equals(numeroConsulta)) {

                    if (payLoad.getMotivo() != null){
                        if(payLoad.getMotivo().equals(item.getMotivo())){contOk++;}
                        item.comMotivo(payLoad.getMotivo());
                    }else {
                        payLoad.comMotivo(item.getMotivo());contNull++;}

                    if (payLoad.getDiagnostico() != null)
                    {if(payLoad.getDiagnostico().equals(item.getDiagnostico())){contOk++;}
                        item.comDiagnnostico(payLoad.getDiagnostico());
                    }else {
                        payLoad.comDiagnnostico(item.getDiagnostico());contNull++;}

                    if (payLoad.getTratamento() != null)
                    {if(payLoad.getTratamento().equals(item.getTratamento())){contOk++;}
                        item.comTratamento(payLoad.getTratamento());
                    }else {
                        payLoad.comTratamento(item.getTratamento());contNull++;}


                    if (payLoad.getNumeroColeira() != null)
                    {if(payLoad.getNumeroColeira().equals(item.getNumeroColeira())){contOk++;}
                        item.comNumeroColeira(payLoad.getNumeroColeira());
                    }else {
                        payLoad.comNumeroColeira(item.getNumeroColeira());contNull++;}

                    if (payLoad.getNumeroRegistroVeterinario() != null)
                    {if(payLoad.getNumeroRegistroVeterinario().equals(item.getNumeroRegistroVeterinario())){contOk++;}
                        item.comNumeroRegistroVeterinario(payLoad.getNumeroRegistroVeterinario());
                    }else {
                        payLoad.comNumeroRegistroVeterinario(item.getNumeroRegistroVeterinario());contNull++;}

                    payLoad.comCpfProprietario(item.getCpfProprietario());
                    payLoad.comNomeProprietario(item.getNomeProprietario());

                    ErrosNulos.erros(5,contNull," numero da consulta ",contOk);
                    break;
                }
            }
            objectMapper.writeValue(new File(cConsultas), consultaDTOS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Erro ao alterar ID");
        }
    }

    public void removerConsultaPorId(String id) {
        try {
            String consultaArquivo = ReadFileUtil.readFile(cConsultas);
            List<ConsultaDTO> consultaDTOS = gson.fromJson(consultaArquivo, new TypeToken<List<ConsultaDTO>>() {
            }.getType());

            for (ConsultaDTO item : consultaDTOS) {
                if (item.getNumeroConsulta().equals(id)) {
                    consultaDTOS.remove(item);
                    break;
                }
            }

            objectMapper.writeValue(new File(cConsultas), consultaDTOS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Erro ao deletar ID");
        }
    }

    public List<String> listarTotalCadaVeterinario() throws IOException {
        String consultaArquivo = ReadFileUtil.readFile(cConsultas);
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
        consultas.sort(((o1, o2) -> o1.getNomeProprietario().compareToIgnoreCase(o2.getNomeProprietario())));
    }

    public List<ConsultaDTO> consultaPaciente() throws IOException {

        String consultaPacienteArquivo = ReadFileUtil.readFile(cConsultas);
        List<ConsultaDTO> consultaPacienteDTOS = gson.fromJson(consultaPacienteArquivo, new TypeToken<List<ConsultaDTO>>() {}.getType());

        List<ConsultaDTO> consultas = consultaPacienteDTOS.stream()
                .sorted(Comparator.comparing(ConsultaDTO::getNomeProprietario))
                .collect(Collectors.toList());
        ordenaConsultaPorNome(consultas);

        return consultas;
    }
}
