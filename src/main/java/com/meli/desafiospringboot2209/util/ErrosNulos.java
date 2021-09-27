package com.meli.desafiospringboot2209.util;

import com.meli.desafiospringboot2209.exception.PersistenceException;

public class ErrosNulos {
    public static void erros(Integer quantidadeErros, int contNull, String mensagemNull, int contOk){
    if(contNull == quantidadeErros && contOk == 0)
    {throw new PersistenceException("É necessário pelo menos 1 parâmetro além " + mensagemNull + " para poder alterar.");}else
            if (contNull == 0 && contOk == quantidadeErros)
    {throw new PersistenceException("É necessário que pelo menos 1 parâmetro seja diferente para alterar.");}
    }
}
