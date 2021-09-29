package com.meli.desafiospringboot2209.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;


public class ReadFileUtil {

    public static String readFile(String file) throws IOException {
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

    public static Gson gson() {
        Gson gson = new Gson();
        return gson;
    }

/*    public List<ConsultaDTO> criaJson(String json) throws IOException {
        String nomeArquivo = readFile(json);
        gson();
        List<ConsultaDTO> listConsultas = gson().fromJson(new TypeToken<List<ConsultaDTO>>());
        mapearObjeto().writeValue(new File(json));

    }*/
}
