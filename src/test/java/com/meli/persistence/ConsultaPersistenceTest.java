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
                "2", "123.456.789-58", "Isac",
                LocalDate.now().toString());

        lista.add(consulta);

        Mockito.when(mock.dadosProprietario(Mockito.any(ConsultaDTO.class))).thenReturn(consulta);
        Mockito.when(mock.buscarConsulta()).thenReturn(lista);
    }
}
