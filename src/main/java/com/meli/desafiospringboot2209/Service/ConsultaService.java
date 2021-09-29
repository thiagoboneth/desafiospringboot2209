package com.meli.desafiospringboot2209.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.dto.PacienteDTO;
import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.exception.PersistenceException;
import com.meli.desafiospringboot2209.persistence.ConsultaPersistence;
import com.meli.desafiospringboot2209.utils.ErrosNulos;
import com.meli.desafiospringboot2209.utils.ReadFileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ConsultaService {

    private ConsultaPersistence persistence;

    private List<Validator> validadores = new ArrayList<Validator>();

    String arquivoConsultas = "consultas.json";
    String arquivoPacientes = "pacientes.json";
    String caminho = "db";
    String cConsultas= caminho + "/" + arquivoConsultas;
    String cPacientes = caminho + "/" + arquivoPacientes;

    List<Consulta> listaConsultas = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new Gson();

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

/*    public ConsultaDTO dadosProprietario(Consulta consulta) throws IOException {

        String coleira = consulta.getNumeroColeira();

        persistence.dadosProprietario(consulta);


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
    }*/



    public List<Consulta> buscarConsulta() {
        mapearObjeto();
        try {
            listaConsultas = objectMapper.readValue(new File(cConsultas), new TypeReference<List<Consulta>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        //ordemListaConsultaDescrescente();
        return listaConsultas;
    }

    public boolean consultaJaCadastrada(String numeroConsulta) {
        listaConsultas = buscarConsulta();
        if (listaConsultas.size() > 0) {
            for (Consulta consulta : listaConsultas) {
                if (consulta.getNumeroConsulta().equals(numeroConsulta)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean verificaNull(Consulta consulta) {
        return consulta.getNumeroColeira() == null || consulta.getNumeroRegistroVeterinario() == null;
    }

/*    public void ordemListaConsultaDescrescente(){
        listaConsultas.sort(((o1, o2) -> o2.getDataHora().compareTo(o1.getDataHora())));
    }*/

/*    public void ordemListaConsultaCrescente(){
        listaConsultas.sort((Comparator.comparing(ConsultaDTO::getDataHora)));
    }*/

/*
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
*/

    // Fica
    public void alterarConsulta(Consulta consulta) {
        try {
            String json = ReadFileUtil.readFile(cConsultas);
            Gson gson = new Gson();

            String numeroConsulta = consulta.getNumeroConsulta();

            List<Consulta> consultaLista = gson.fromJson(json, new TypeToken<List<Consulta>>() {
            }.getType());

            Integer contNull = 0;
            Integer contOk = 0;

            for (Consulta item : consultaLista) {
                if (consulta.getNumeroConsulta() == null)
                {throw new PersistenceException("Impossivel aterar sem o numero da consulta");}

                if (item.getNumeroConsulta().equals(numeroConsulta)) {

                    if (consulta.getMotivo() != null){
                        if(consulta.getMotivo().equals(item.getMotivo())){contOk++;}
                        item.comMotivo(consulta.getMotivo());
                    }else {
                        consulta.comMotivo(item.getMotivo());contNull++;}

                    if (consulta.getDiagnostico() != null)
                    {if(consulta.getDiagnostico().equals(item.getDiagnostico())){contOk++;}
                        item.comDiagnnostico(consulta.getDiagnostico());
                    }else {
                        consulta.comDiagnnostico(item.getDiagnostico());contNull++;}

                    if (consulta.getTratamento() != null)
                    {if(consulta.getTratamento().equals(item.getTratamento())){contOk++;}
                        item.comTratamento(consulta.getTratamento());
                    }else {
                        consulta.comTratamento(item.getTratamento());contNull++;}


                    if (consulta.getNumeroColeira() != null)
                    {if(consulta.getNumeroColeira().equals(item.getNumeroColeira())){contOk++;}
                        item.comNumeroColeira(consulta.getNumeroColeira());
                    }else {
                        consulta.comNumeroColeira(item.getNumeroColeira());contNull++;}

                    if (consulta.getNumeroRegistroVeterinario() != null)
                    {if(consulta.getNumeroRegistroVeterinario().equals(item.getNumeroRegistroVeterinario())){contOk++;}
                        item.comNumeroRegistroVeterinario(consulta.getNumeroRegistroVeterinario());
                    }else {
                        consulta.comNumeroRegistroVeterinario(item.getNumeroRegistroVeterinario());contNull++;}

                    consulta.comCpfProprietario(item.getCpfProprietario());
                    consulta.comNomeProprietario(item.getNomeProprietario());

                    ErrosNulos.erros(5,contNull," numero da consulta ",contOk);
                    break;
                }
            }

            persistence.altera(consulta);

        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Erro ao alterar ID");
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

    public void ordenaConsultaPorNome(List<Consulta> consultas){
        consultas.sort(((o1, o2) -> o1.getNomeProprietario().compareToIgnoreCase(o2.getNomeProprietario())));
    }

/*    public List<ConsultaDTO> consultaPaciente() throws IOException {

        String consultaPacienteArquivo = ReadFileUtil.readFile(cConsultas);
        List<ConsultaDTO> consultaPacienteDTOS = gson.fromJson(consultaPacienteArquivo, new TypeToken<List<ConsultaDTO>>() {}.getType());

        List<ConsultaDTO> consultas = consultaPacienteDTOS.stream()
                .sorted(Comparator.comparing(ConsultaDTO::getNomeProprietario))
                .collect(Collectors.toList());
        ordenaConsultaPorNome(consultas);

        return consultas;
    }*/

    // Fica
    public void cadastrar(Consulta consulta) {
        try {
            if (verificaNull(consulta)) {
                throw new PersistenceException("É necessário o número da coleira e o número de registro do veterinário");
            }

            if (consultaJaCadastrada(consulta.getNumeroConsulta())) {
                throw new PersistenceException("Consulta já cadastrada");
            }

            persistence.cadastro(consulta);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Error na escrita do arquivo");
        }

    }
}
