package com.meli.persistence;

import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.persistence.ConsultaPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ConsultaPersistenceTest {

    @Test
    void deve_Retornar_Dados_doProprietario_naConsulta() throws IOException {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);

        ArrayList<ConsultaDTO> lista = new ArrayList<>();

        ConsultaDTO consulta = new ConsultaDTO(
                "15",
                "5",
                "Quebrou a pata",
                "Pata enfaixada",
                "Repouso",
                "2",
                "123.456.789-58",
                "Isac",
                LocalDate.now().toString());

        lista.add(consulta);

        Mockito.when(mock.dadosProprietario(Mockito.any(ConsultaDTO.class))).thenReturn(consulta);
        Mockito.when(mock.buscarConsulta()).thenReturn(lista);
    }

    @Test
    void deve_Salvar_aConsulta_noArquivo() {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);

        ArrayList<ConsultaDTO> lista = new ArrayList<>();

        ConsultaDTO consulta = new ConsultaDTO(
                "16",
                "4",
                "Paciente com rachaduras nas quatro patas",
                "As ferraduras estao machucando as patas e gerando rachaduras",
                "Remover as ferraduras das patas, lixar as patas, passar pomada e retonar com as ferraduras",
                "4560", "001.002.003-00", "Maria",
                LocalDate.now().toString());

        lista.add(consulta);

        Mockito.when(mock.salvarConsultaNoArquivo(Mockito.any(ConsultaDTO.class))).thenReturn(consulta);
        Mockito.when(mock.buscarConsulta()).thenReturn(lista);
    }

    @Test
    void deve_listar_asConsultas_doDia() throws IOException {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);

        ArrayList<ConsultaDTO> lista = new ArrayList<>();

        ConsultaDTO consulta = new ConsultaDTO(
                "16",
                "4",
                "Paciente com rachaduras nas quatro patas",
                "As ferraduras estao machucando as patas e gerando rachaduras",
                "Remover as ferraduras das patas, lixar as patas, passar pomada e retonar com as ferraduras",
                "4560", "001.002.003-00", "Maria",
                LocalDate.now().toString());

        lista.add(consulta);

        Mockito.when(mock.consultasDoDia("28-09-2021")).thenReturn(lista);
    }

/*    @Test
    void deve_listar_asConsultas_doDia() throws IOException {
        ConsultaPersistence consulta = new ConsultaPersistence();

        List<ConsultaDTO> lista = consulta.consultasDoDia("25-09-2021");

        Assert.assertEquals("15", lista.get(0).getNumeroConsulta());


    }*/
}
