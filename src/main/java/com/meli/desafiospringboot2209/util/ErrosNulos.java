package com.meli.desafiospringboot2209.util;

public class ErrosNulos {
    public static void erros(Integer quantidadeErros, int contNull, String mensagemNull, int contOk){
    if(contNull == quantidadeErros && contOk == 0)
    {throw new RuntimeException("E necessario pelomenos 1 parametro alem "+mensagemNull+" para poder alterar.");}else
            if (contNull == 0 && contOk == quantidadeErros)
    {throw new RuntimeException("E necessario que pelomenos 1 parametro seja diferente para alterar.");}
    }
}
