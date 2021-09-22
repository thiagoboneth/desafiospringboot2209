package com.meli.desafiospringboot2209.model;

import java.util.List;

public interface DAO<T>{

    void salva(T objeto);
    void salva(List<T> listagem);
    T obter(String id);
    List<T> listagem();
}

