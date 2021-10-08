package com.meli.desafiospringboot2209.service;

import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.ConsultaPersistence;
import com.meli.desafiospringboot2209.persistence.PacientePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaService {
	
    List<ConsultaDTO> listaConsultas = new ArrayList<>();

    @Autowired
    private PacientePersistence pacientePersistence;

    @Autowired
    private ConsultaPersistence consultaPersistence;

     
    @Autowired
    public ConsultaService(ConsultaPersistence consultaPersistence, PacientePersistence pacientePersistence){
        this.consultaPersistence = consultaPersistence;
        this.pacientePersistence = pacientePersistence;
    }
    
   
    //public ConsultaService() {
    //
    //}


    /**
     * deve retornar um objeto da consulta marcada contendo todos os dados da consulta. Nao deve ser void
     * @param consulta
     */

    public boolean marcaConsulta(Consulta consulta){
        try {
            if(consultaJaCadastrada(consulta.getNumeroConsulta())){
            	throw new RuntimeException("Erro");
            }else {
                this.consultaPersistence.salvarConsultaNoArquivo(consulta);
                return true;
            }
        }catch (RuntimeException | IOException e){
            throw new RuntimeException("Consulta já cadastrada");
        }
    }

    public boolean consultaJaCadastrada(String numeroConsulta) throws IOException {
        return this.consultaPersistence.consultaJaCadastrada(numeroConsulta);
    }

    public List<Consulta> consultasDoDia(String data) throws IOException {
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

    public boolean alterarConsulta(Consulta consulta) throws IOException {
        this.consultaPersistence.alterarConsulta(consulta);
        return true;
    }

    public boolean removerConsultaPorId(String id) {
        this.consultaPersistence.removerConsultaPorId(id);
        return true;
    }

    /**
     * Retorna o total de consultas por veterinario
     * @return
     * @throws IOException
     */

    public List<Consulta> listarTotalCadaVeterinario(Veterinario veterinario) throws IOException {
        return this.consultaPersistence.getList(veterinario.getNumeroRegistro());
    }


/*    public List<ConsultaDTO> consultaPaciente() throws IOException {
    // RECEBER CONSULTA paiente da persistencia
        return consultas;
    }*/

}

