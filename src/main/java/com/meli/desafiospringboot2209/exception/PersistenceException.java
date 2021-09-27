package com.meli.desafiospringboot2209.exception;

public class PersistenceException extends RuntimeException{

    public PersistenceException(String mensagem) {
        super(mensagem);
    }
}
