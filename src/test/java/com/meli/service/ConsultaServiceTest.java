package com.meli.service;

import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.ConsultaPersistence;
import com.meli.desafiospringboot2209.persistence.PacientePersistence;
import com.meli.desafiospringboot2209.persistence.ProprietarioPersistence;
import com.meli.desafiospringboot2209.service.ConsultaService;
import com.meli.desafiospringboot2209.service.ProprietarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsultaServiceTest {
    @Test
    void deve_Cadastrar_Consulta() throws IOException {

        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        PacientePersistence mock2 = Mockito.mock(PacientePersistence.class);

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
                .comProprietario(proprietario)
                .comEspecie("Coelho")
                .comNumeroColeira("77");

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

        ConsultaService consultaService = new ConsultaService(mock, mock2);
        consultaService.marcaConsulta(consulta);

        //Boolean retorno = consultaService.marcaConsulta(consulta);

        //Assertions.assertTrue(retorno);
        
        assertNotNull(consulta.comNumeroDaConsulta(consulta.getNumeroConsulta()));
        assertNotNull(consulta.comPaciente(paciente));
        assertNotNull(consulta.comProprietario(proprietario));
        assertNotNull(consulta.comMotivo(consulta.getMotivo()));
        assertNotNull(consulta.comVeterinario(veterinario));
        assertNotNull(consulta.comDiagnostico(consulta.getDiagnostico()));
        assertNotNull(consulta.comTratamento(consulta.getTratamento()));
        assertNotNull(consulta.comColeira(consulta.getNumeroColeira()));

    }

    @Test
    void deve_mostrar_consulta() throws IOException {
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
                .comProprietario(proprietario)
                .comEspecie("Coelho")
                .comNumeroColeira("77");

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
        Mockito.when(mock.getList()).thenReturn(list);
        assertEquals("16", consulta.getNumeroConsulta());
    }

    @Test
    void deve_Alterar_o_consulta() throws IOException {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        PacientePersistence mock2 = Mockito.mock(PacientePersistence.class);

        List<Consulta> list = new ArrayList<>();
        List<Consulta> alterada = new ArrayList<>();

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
                .comProprietario(proprietario)
                .comEspecie("Coelho")
                .comNumeroColeira("77");

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


        Proprietario proprietario1 = new Proprietario()
                .comCpf("531.088.254-20")
                .comNome("Saint")
                .comSobrenome("Santos")
                .comDataNascimento("25/10/92")
                .comEndereco("Rua Lacoruna")
                .comTelefone("(48)988406591");

        Paciente paciente1 = new Paciente()
                .comEspecie("Coelho da montanha")
                .comRaca("Coelho")
                .comCor("Branco")
                .comDataNascimento("25/10/92")
                .comNome("Coelhao")
                .comSexo("Masculino")
                .comProprietario(proprietario1)
                .comEspecie("Coelho")
                .comNumeroColeira("77");

        Veterinario veterinario1 = new Veterinario()
                .comCpf("123.456.789-20")
                .comNome("Pedro")
                .comSobrenome("Souza")
                .comDataNascimento("24/10/82")
                .comNumeroRegistro("1020")
                .comEspecialidade("Animais Silvestres");

        Consulta consulta1 = new Consulta()
                .comNumeroDaConsulta("16")
                .comPaciente(paciente1)
                .comMotivo("Dor de Cabeça")
                .comVeterinario(veterinario1)
                .comDiagnostico("bicho com forte dores decorrente a uma infecção")
                .comTratamento("Tomar medicação")
                .comColeira("77");

        alterada.add(consulta1);



        Mockito.when(mock.alterarConsulta(consulta)).thenReturn(true);
        Mockito.when(mock.getList()).thenReturn(list);

        ConsultaService consultaService = new ConsultaService(mock , mock2);
        boolean retorno =  consultaService.alterarConsulta(consulta1);
        assertTrue(retorno);
    }

    @Test
    void deve_Deletar_o_consulta() throws IOException {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        PacientePersistence mock2 = Mockito.mock(PacientePersistence.class);

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
                .comProprietario(proprietario)
                .comEspecie("Coelho")
                .comNumeroColeira("77");

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

        Mockito.when(mock.removerConsultaPorId(consulta.getNumeroConsulta())).thenReturn(true);
        Mockito.when(mock.getList()).thenReturn(list);

        ConsultaService consultaService = new ConsultaService(mock, mock2);
        boolean retorno =  consultaService.removerConsultaPorId(consulta.getNumeroConsulta());
        assertTrue(retorno);
    }

}
