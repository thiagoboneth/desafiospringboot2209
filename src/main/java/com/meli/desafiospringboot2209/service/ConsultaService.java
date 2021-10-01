package com.meli.desafiospringboot2209.service;

import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.ConsultaPersistence;
import com.meli.desafiospringboot2209.persistence.PacientePersistence;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {


    List<ConsultaDTO> listaConsultas = new ArrayList<>();


    private PacientePersistence pacientePersistence;
    private ConsultaPersistence consultaPersistence;


    public ConsultaService(PacientePersistence pacientePersistence, ConsultaPersistence consultaPersistence){
        this.pacientePersistence = pacientePersistence;
        this.consultaPersistence = consultaPersistence;
    }

    /**
     * associa o proprietario do animal em uma consulta
     * @param consulta
     * @throws IOException
     */
//    private void dadosProprietario(Consulta consulta) throws IOException {
//        String coleira = consulta.getNumeroColeira();
//        List<Paciente> pacientes = this.pacientePersistence.getList();
//        Optional<Paciente> pacienteOptional = pacientes.stream().filter(paciente -> paciente.getNumeroColeira().equals(coleira)).findAny();
//        if(pacienteOptional.isPresent())
//            consulta.setProprietario(pacienteOptional.get().getProprietario());
//    }


    /**
     * deve retornar um objeto da consulta marcada contendo todos os dados da consulta. Nao deve ser void
     * @param consulta
     */

    public boolean verificaNull(Consulta consulta) {
        return consulta.getNumeroColeira() == null || consulta.getVeterinario().getNumeroRegistro() == null;
    }


    public void marcaConsulta(Consulta consulta){
       try {
           if (verificaNull(consulta)) {
               throw new RuntimeException("É necessário o número da coleira e o número de registro do veterinário");
           }

           if (consultaJaCadastrada(consulta.getNumeroConsulta())) {
               throw new RuntimeException("Consulta já cadastrada");
           }
           this.consultaPersistence.salvarConsultaNoArquivo(consulta);
       }catch (RuntimeException | IOException e){
           throw new RuntimeException("Error em marcar consula");
       }
    }

    public boolean consultaJaCadastrada(String numeroConsulta) throws IOException {
        return this.consultaPersistence.consultaJaCadastrada(numeroConsulta);
    }

    public boolean verificaNull(ConsultaDTO consultaDTO) {
        if (consultaDTO.getNumeroColeira() == null || consultaDTO.getNumeroRegistroVeterinario() == null) {
            return true;
        } else {
            return false;
        }
    }

    public List<Consulta> consultasDoDia(LocalDate data) throws IOException {
        return this.consultaPersistence.consultasDoDia(data);
    }

    public List<Consulta> buscarConsulta(String ordem) {
        List<Consulta> lista = this.consultaPersistence.getList();
        if(ordem.equals("C"))
            lista.sort((Consulta c1, Consulta c2) -> c1.getDataHora().compareTo(c2.getDataHora()));
        if(ordem.equals("D"))
            lista.sort((Consulta c1, Consulta c2) -> c2.getDataHora().compareTo(c1.getDataHora()));
        return lista;
    }

    public void alterarConsulta(Consulta consulta) {
        this.consultaPersistence.alterarConsulta(consulta);

    }

    public void removerConsultaPorId(String id) {
        this.consultaPersistence.removerConsultaPorId(id);
    }

    /**
     * Retorna o total de consultas por veterinario
     * @return
     * @throws IOException
     */
    public List<Consulta> listarTotalCadaVeterinario(Veterinario veterinario) throws IOException {
        return this.consultaPersistence.getList(veterinario.getNumeroRegistro());
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

}
