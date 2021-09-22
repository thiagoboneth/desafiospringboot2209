package com.meli.desafiospringboot2209;

import com.meli.desafiospringboot2209.util.Listagem;

import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException
    {
        Listagem listagem = new Listagem();

        listagem.mostraProprietario();
        listagem.mostraVeterinario();
        listagem.mostraPaciente();
        listagem.mostraConsultas();


        listagem.leObjetoProprietario();
        listagem.leObjetoVeterinario();
        listagem.leObjetoPaciente();
        listagem.leObjetoConsultas();


        System.out.println("---------------teste veterinario---------------");


        listagem.retornaObjetoVeterinario();

        System.out.println("---------------teste Proprietario---------------");

        listagem.retornaObjetoProprietario();


        System.out.println("---------------teste Paciente---------------");

        //listagem.retornaObjetoPaciente();

        System.out.println("---------------teste Consulta---------------");

        //listagem.retornaObjetoConsultas();


        System.out.println("Terminar arquivo.txt");

        System.out.println("---------------teste Comparator Proprietario ---------------");

        listagem.mostraProprietarioCrescente();

        System.out.println("---------------teste Comparator Consulta ---------------");

        //listagem.mostraConsultaData();

    }
}

