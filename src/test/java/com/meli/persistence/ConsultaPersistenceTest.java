package com.meli.persistence;

import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.ConsultaPersistence;
import com.meli.desafiospringboot2209.service.ConsultaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ConsultaPersistenceTest {

    @Test
    void deveCadastrarPaciente() throws IOException {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);

        List<Consulta> list = new ArrayList<>();
        Paciente paciente = new Paciente()
                .comNome("Coelhao")
                .comEspecie("Coelho")
                .comNumeroDaColeira("77");

        Veterinario veterinario = new Veterinario()
                .comNome("Pedro")
                .comNumeroRegistro("1020")
                .comEspecialidade("Animais Silvestres");

        Consulta consulta = new Consulta()
                .comNumeroDaConsulta("16")
                .comPaciente(paciente)
                .comMotivo("Dor de Cabeça")
                .comVeterinario(veterinario);

        list.add(consulta);

        Mockito.when(mock.salvarConsultaNoArquivo(consulta)).thenReturn(consulta);

        ConsultaService consultaService = new ConsultaService();

        Boolean retorno = consultaService.marcaConsulta(consulta);

        Assertions.assertTrue(retorno);

    }

}

