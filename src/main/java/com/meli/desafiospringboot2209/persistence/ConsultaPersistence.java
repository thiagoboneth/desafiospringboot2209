package com.meli.desafiospringboot2209.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.dto.PacienteDTO;
import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
import com.meli.desafiospringboot2209.util.ReadFileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaPersistence {

    String arquivo = "consultas.json";
    String caminho = "db";
    String cC = caminho + "/" + arquivo;

    List<ConsultaDTO> listaConsultas = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new Gson();

    private void mapearObjeto() {
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private void dadosProprietario(ConsultaDTO consultaDTO) throws IOException {

        String coleira = consultaDTO.getNumeroColeira();

        String consultaArquivo = ReadFileUtil.readFile("db/paciente.json");
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
    }


    public ConsultaDTO salvarConsultaNoArquivo(ConsultaDTO consultaDTO) {
        mapearObjeto();
        listaConsultas = buscarConsulta();

        try {
            if (verificaNull(consultaDTO)) {
                throw new RuntimeException("É necessário o número da coleira e o número de registro do veterinário");
            }

            if (consultaJaCadastrada(consultaDTO.getNumeroConsulta())) {
                throw new RuntimeException("Consulta já cadastrada");
            }

            listaConsultas.add(consultaDTO);

            dadosProprietario(consultaDTO);

            objectMapper.writeValue(new File(cC), listaConsultas);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error na escrita do arquivo");
        }
        return consultaDTO;
    }

    public boolean consultaJaCadastrada(String numeroConsulta) throws IOException {
        listaConsultas = buscarConsulta();
        if (listaConsultas.size() > 0) {
            for (ConsultaDTO consultaDTO : listaConsultas) {
                if (consultaDTO.getNumeroConsulta().equals(numeroConsulta)) {
                    return true;
                }
                System.out.println(consultaDTO.getNumeroConsulta());
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean verificaNull(ConsultaDTO consultaDTO) {
        if (consultaDTO.getNumeroColeira() == null || consultaDTO.getNumeroRegistroVeterinario() == null) {
            return true;
        } else {
            return false;
        }
    }

    public void ordemListaConsultaDescrescente(){
        Collections.sort(listaConsultas,((o1, o2) -> o2.getDataHora().compareTo(o1.getDataHora())));
    }

    public void ordemListaConsultaCrescente(){
        Collections.sort(listaConsultas,((o1, o2) -> o1.getDataHora().compareTo(o2.getDataHora())));
    }

    public List<ConsultaDTO> consultasDoDia(String data) throws IOException {

        String[] diaConvertida = data.split("-");

        int ano = Integer.parseInt(diaConvertida[2]);
        int mes = Integer.parseInt(diaConvertida[1]);
        int dia = Integer.parseInt(diaConvertida[0]);

        LocalDate dataConvertida = LocalDate.of(ano,mes,dia);

        String json = readFile("db/consultas.json");
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
            listaConsultas = objectMapper.readValue(new File(cC), new TypeReference<List<ConsultaDTO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        ordemListaConsultaDescrescente();
        return listaConsultas;
    }

    public void alterarConsulta(ConsultaDTO payLoad) {
        try {
            String json = ReadFileUtil.readFile(cC);
            Gson gson = new Gson();

            ConsultaDTO registros = payLoad;
            String numeroConsulta = registros.getNumeroConsulta();

            List<ConsultaDTO> consultaDTOS = gson.fromJson(json, new TypeToken<List<ConsultaDTO>>() {
            }.getType());

            Integer contNull = 0;
            Integer contOk = 0;

            for (ConsultaDTO item : consultaDTOS) {
                if (registros.getNumeroConsulta() == null)
                {throw new RuntimeException("Impossivel aterar sem o numero da consulta");}

                if (item.getNumeroConsulta().equals(numeroConsulta)) {

                    if (registros.getMotivo() != null){
                      if(registros.getMotivo().equals(item.getMotivo())){contOk++;}
                        item.comMotivo(registros.getMotivo());
                    }else {registros.comMotivo(item.getMotivo());contNull++;}

                    if (registros.getDiagnostico() != null)
                    {if(registros.getDiagnostico().equals(item.getDiagnostico())){contOk++;}
                        item.comDiagnnostico(registros.getDiagnostico());
                    }else {registros.comDiagnnostico(item.getDiagnostico());contNull++;}

                    if (registros.getTratamento() != null)
                    {if(registros.getTratamento().equals(item.getTratamento())){contOk++;}
                        item.comTratamento(registros.getTratamento());
                    }else {registros.comTratamento(item.getTratamento());contNull++;}


                    if (registros.getNumeroColeira() != null)
                    {if(registros.getNumeroColeira().equals(item.getNumeroColeira())){contOk++;}
                        item.comNumeroColeira(registros.getNumeroColeira());
                    }else {registros.comNumeroColeira(item.getNumeroColeira());contNull++;}

                    if (registros.getNumeroRegistroVeterinario() != null)
                    {if(registros.getNumeroRegistroVeterinario().equals(item.getNumeroRegistroVeterinario())){contOk++;}
                        item.comNumeroRegistroVeterinario(registros.getNumeroRegistroVeterinario());
                    }else {registros.comNumeroRegistroVeterinario(item.getNumeroRegistroVeterinario());contNull++;}

                    registros.comCpfProprietario(item.getCpfProprietario());
                    registros.comNomeProprietario(item.getNomeProprietario());

                    if(contNull == 5 && contOk == 0)
                    {throw new RuntimeException("E necessario pelomenos 1 parametro alem numero da consulta para poder alterar.");}else
                        if (contNull == 0 && contOk == 5)
                    {throw new RuntimeException("E necessario que pelomenos 1 parametro seja diferente para alterar.");}
                        break;
                }
            }
            objectMapper.writeValue(new File(cC), consultaDTOS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao alterar ID");
        }
    }

    public void removerConsultaPorId(String id) {
        try {
            String consultaArquivo = ReadFileUtil.readFile("db/consultas.json");
            List<ConsultaDTO> consultaDTOS = gson.fromJson(consultaArquivo, new TypeToken<List<ConsultaDTO>>() {
            }.getType());

            for (ConsultaDTO item : consultaDTOS) {
                if (item.getNumeroConsulta().equals(id)) {
                    consultaDTOS.remove(item);
                    break;
                }
            }

            objectMapper.writeValue(new File(cC), consultaDTOS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar ID");
        }
    }

    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
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
        Collections.sort(consultas,((o1, o2) -> o1.getNomeProprietario().compareToIgnoreCase(o2.getNomeProprietario())));
    }


    public List<ConsultaDTO> consultaPaciente() throws IOException {

        String consultaPacienteArquivo = ReadFileUtil.readFile("db/consultas.json");
        List<ConsultaDTO> consultaPacienteDTOS = gson.fromJson(consultaPacienteArquivo, new TypeToken<List<ConsultaDTO>>() {}.getType());

        List<ConsultaDTO> consultas = consultaPacienteDTOS.stream()
                                .sorted(Comparator.comparing(ConsultaDTO::getNomeProprietario))
                                        .collect(Collectors.toList());
         ordenaConsultaPorNome(consultas);

        return consultas;


    }

}
