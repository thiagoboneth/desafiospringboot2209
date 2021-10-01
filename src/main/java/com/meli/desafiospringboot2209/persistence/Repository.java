package com.meli.desafiospringboot2209.persistence;

import java.util.List;

public interface Repository<T>{

    List<T> getList();

}

