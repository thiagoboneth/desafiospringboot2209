package com.meli.desafiospringboot2209.util;

import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.entity.Veterinario;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Listagem {
    ArrayList<Proprietario> pessoasObjeto = new ArrayList<>();
    ArrayList<Consulta> consultasObjeto = new ArrayList<>();

    ArrayList<String> pessoas = new ArrayList<>();
    ArrayList<String> proprietario = new ArrayList<>();
    ArrayList<String> veterinario = new ArrayList<>();
    ArrayList<String> paciente = new ArrayList<>();
    ArrayList<String> consulta = new ArrayList<>();


    public void mostraProprietarioCrescente() {
        Collections.sort(pessoasObjeto, new Comparator<Proprietario>() {
            @Override
            public int compare(Proprietario o1, Proprietario o2) {
                return o1.getSobrenome().compareTo(o2.getSobrenome());
            }
        });
        pessoasObjeto.forEach(System.out::println);
    }

    public void mostraConsultaData(){
        Collections.sort(consultasObjeto, new Comparator<Consulta>(){

            @Override
            public int compare(Consulta o1, Consulta o2) {
                return o2.getDataHora().compareTo(o1.getDataHora());
            }
        });
        consultasObjeto.forEach(System.out::println);
    }



    public void mostraConsultas() throws IOException {

        Scanner scanner = new Scanner(new FileReader("db/consultas.csv")).useDelimiter("\\n");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }

    public void mostraProprietario() throws IOException {

        Scanner scanner = new Scanner(new FileReader("db/proprietario.csv")).useDelimiter("\\n");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }

    public void mostraVeterinario() throws IOException {

        Scanner scanner = new Scanner(new FileReader("db/veterinarios.csv")).useDelimiter("\\n");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }

    public void mostraPaciente() throws IOException {

        Scanner scanner = new Scanner(new FileReader("db/paciente.csv")).useDelimiter("\\n");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }


    public void leObjetoConsultas() throws IOException {
        Scanner scanner1 = new Scanner(new FileReader("db/consultas.csv")).useDelimiter("\\n");
        while (scanner1.hasNext()) {
            consulta.add(scanner1.next());
        }
    }

    public void leObjetoProprietario() throws IOException {
        Scanner scanner1 = new Scanner(new FileReader("db/proprietario.csv")).useDelimiter("\\n");
        while (scanner1.hasNext()) {
            proprietario.add(scanner1.next());
        }
    }

    public void leObjetoVeterinario() throws IOException {
        Scanner scanner1 = new Scanner(new FileReader("db/veterinarios.csv")).useDelimiter("\\n");
        while (scanner1.hasNext()) {
            veterinario.add(scanner1.next());
        }
    }

    public void leObjetoPaciente() throws IOException {
        Scanner scanner1 = new Scanner(new FileReader("db/paciente.csv")).useDelimiter("\\n");
        while (scanner1.hasNext()) {
            paciente.add(scanner1.next());
        }
    }


    public void retornaObjetoConsultas() {
        consulta.forEach(System.out::println);
        consulta.forEach(x -> System.out.println(mapearParaConsultas(x).toString()));
    }

    public void retornaObjetoVeterinario() {
        veterinario.forEach(System.out::println);
        veterinario.forEach(x -> System.out.println(mapearParaVeterinario(x).toString()));
    }

    public void retornaObjetoProprietario() {
        proprietario.forEach(System.out::println);
        proprietario.forEach(x -> System.out.println(mapearParaProprietario(x).toString()));
    }
    public void retornaObjetoPaciente() {
        paciente.forEach(System.out::println);
        paciente.forEach(x -> System.out.println(mapearParaPaciente(x).toString()));
    }

    public Consulta mapearParaConsultas(String linha) {
        String[] valores = linha.replaceAll("\"","").split(",");
        Consulta consulta = new Consulta();
        Veterinario veterinario = new Veterinario();
        Consulta consulta1 = new Consulta(consulta.getDataHora(), consulta.getPaciente(), consulta.getMotivo(), consulta.getVeterinario(), consulta.getDiagnostico(), consulta.getTratamento());
        consultasObjeto.add(consulta1);
        return consulta;
    }

    public Veterinario mapearParaVeterinario(String linha) {
        String[] valores = linha.replaceAll("\"","").split(",");
        Veterinario veterinario = new Veterinario(valores[0],valores[1],valores[2],valores[3],Integer.valueOf(valores[4]),valores[5],valores[6]);
        return veterinario;
    }
    public Paciente mapearParaPaciente(String linha) {
        String[] valores = linha.replaceAll("\"","").split(",");
        Paciente paciente = new Paciente();
        Paciente animal = new Paciente(paciente.getEspecie(), paciente.getRaca(), paciente.getCor(), paciente.getDataNascimento(), paciente.getNome(), paciente.getSexo(), paciente.getProprietario());
        return animal;
    }
    public Proprietario mapearParaProprietario(String linha) {
        String[] valores = linha.replaceAll("\"","").split(",");
        Proprietario proprietario = new Proprietario(valores[0],valores[1],valores[2],valores[3],valores[4],valores[5] );
        return proprietario;

    }
}
