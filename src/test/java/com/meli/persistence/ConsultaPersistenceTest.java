package com.meli.persistence;

import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.entity.Proprietario;
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

        Proprietario proprietario = new Proprietario()
                .comCpf("531.088.254-20")
                .comNome("Saint")
                .comSobrenome("Santos")
                .comDataNascimento("25/10/92")
                .comEndereco("Rua Lacoruna")
                .comTelefone("(48)988406591");

        Paciente paciente = new Paciente()
                .comEspecie("Coelho da montanha")
                .comRaca("Coelho")
                .comCor("Branco")
                .comDataNascimento("25/10/92")
                .comNome("Coelhao")
                .comSexo("Masculino")
                .comCpfProprietario("531.088.254-20")
                .comProprietario(proprietario)
                .comEspecie("Coelho")
                .comNumeroDaColeira("77");

        Veterinario veterinario = new Veterinario()
                .comCpf("123.456.789-20")
                .comNome("Pedro")
                .comSobrenome("Souza")
                .comDataNascimento("24/10/82")
                .comNumeroRegistro("1020")
                .comEspecialidade("Animais Silvestres");

        Consulta consulta = new Consulta()
                .comNumeroDaConsulta("16")
                .comPaciente(paciente)
                .comMotivo("Dor de Cabeça")
                .comVeterinario(veterinario)
                .comDiagnostico("Animal com forte dores decorrente a uma infecção")
                .comTratamento("Tomar medicação")
                .comColeira("77");

        list.add(consulta);

        Mockito.when(mock.salvarConsultaNoArquivo(consulta)).thenReturn(consulta);

        ConsultaService consultaService = new ConsultaService(mock);

        Boolean retorno = consultaService.marcaConsulta(consulta);

        Assertions.assertTrue(retorno);
    }

}

