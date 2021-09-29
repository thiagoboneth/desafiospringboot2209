package com.meli.desafiospringboot2209.validator;

import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.Service.Validator;

public class ValidaConsulta implements Validator<Consulta> {


    Integer contNull = 0;
    Integer contOk = 0;

    @Override
    public void valida(Consulta consulta) {
/*        if (consulta.getNumeroConsulta() == null)
        {throw new PersistenceException("Impossivel aterar sem o numero da consulta");}

        if (item.getNumeroConsulta().equals(consulta.getNumeroConsulta())) {

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

            if (contNull == 5 && contOk == 0) {
                throw new PersistenceException("É necessário pelo menos 1 parâmetro além do número da consulta para poder alterar.");
            } else if (contNull == 0 && contOk == 5) {
                throw new PersistenceException("É necessário que pelo menos 1 parâmetro seja diferente para alterar.");
            }
        }*/


    }
}
