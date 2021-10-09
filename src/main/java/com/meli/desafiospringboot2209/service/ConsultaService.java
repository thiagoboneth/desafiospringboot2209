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
    /**
     * @param marcaConsulta - espera um boleano
     * @return marca uma consulta e retorna uma ecxecao caso ja exista uma consulta cadastrada
     * @author Grupo 4 - Tester Pedro Augusto
     */

    public boolean marcaConsulta(Consulta consulta){
        try {
            if(consultaJaCadastrada(consulta.getNumeroConsulta())){
            	throw new RuntimeException("Erro"); }else {
                this.consultaPersistence.salvarConsultaNoArquivo(consulta);
                return true;
            }
        }catch (RuntimeException | IOException e){throw new RuntimeException("Consulta já cadastrada");}
    }

    /**
     * @param consultaJaCadastrada - espera um numero de consulta
     * @return retorna um bollean false caso a consulta ja exista
     * @author Grupo 4 - Tester Pedro Augusto
     */

    public boolean consultaJaCadastrada(String numeroConsulta) throws IOException {
        return this.consultaPersistence.consultaJaCadastrada(numeroConsulta);
    }


    /**
     * @param consultasDoDia - espera uma data
     * @return retorna uma lista das consultas ja existente
     * @author Grupo 4 - Tester Pedro Augusto
     */

    public List<Consulta> consultasDoDia(String data) throws IOException {
        return this.consultaPersistence.consultasDoDia(data);
    }

    /**
     * @param buscarConsulta - espera a ordem da consulta crecente ou decrecente
     * @return retorna uma lista das consultas na ordem escolhida
     * @author Grupo 4 - Tester Pedro Augusto
     */
    public List<Consulta> buscarConsulta(String ordem) {
        List<Consulta> lista = this.consultaPersistence.getList();
        if(ordem.equals("C"))
            lista.sort((Consulta c1, Consulta c2) -> c1.getDataHora().compareTo(c2.getDataHora()));
        if(ordem.equals("D"))
            lista.sort((Consulta c1, Consulta c2) -> c2.getDataHora().compareTo(c1.getDataHora()));
        return lista;
    }

    /**
     * @param alterarConsulta - espera uma consulta com os dados n=modificados
     * @return retorna retorna um verdadeiro ou falso dependendo do acontecimento
     * @author Grupo 4 - Tester Pedro Augusto
     */
    public boolean alterarConsulta(Consulta consulta) throws IOException {
        this.consultaPersistence.alterarConsulta(consulta);
        return true;
    }

    /**
     * @param removerConsultaPorId - espera o numero da consulta
     * @return retorna retorna um verdadeiro ou falso dependendo se foi excluido
     * @author Grupo 4 - Tester Pedro Augusto
     */
    public boolean removerConsultaPorId(String id) {
        this.consultaPersistence.removerConsultaPorId(id);
        return true;
    }

}

