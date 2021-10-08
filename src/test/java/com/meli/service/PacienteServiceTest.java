package com.meli.service;

import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.ConsultaPersistence;
import com.meli.desafiospringboot2209.persistence.PacientePersistence;
import com.meli.desafiospringboot2209.persistence.ProprietarioPersistence;
import com.meli.desafiospringboot2209.service.ConsultaService;
import com.meli.desafiospringboot2209.service.PacienteService;
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

public class PacienteServiceTest {
    @Test
    void deve_Cadastrar_Paciente() throws IOException {

        PacientePersistence mock = Mockito.mock(PacientePersistence.class);

        List<Paciente> list = new ArrayList<>();

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


        list.add(paciente);

        Mockito.when(mock.salvarPacienteNoArquivo(paciente)).thenReturn(true);

        PacienteService pacienteService = new PacienteService(mock);

       pacienteService.cadastrarPaciente(paciente);
        
        assertNotNull(paciente.comEspecie(paciente.getEspecie()));
        assertNotNull(paciente.comRaca(paciente.getRaca()));
        assertNotNull(paciente.comCor(paciente.getCor()));
        assertNotNull(paciente.comDataNascimento(paciente.getDataNascimento()));
        assertNotNull(paciente.comNome(paciente.getNome()));
        assertNotNull(paciente.comSexo(paciente.getSexo()));
        assertNotNull(paciente.comProprietario(paciente.getProprietario()));
        assertNotNull(paciente.comEspecie(paciente.getEspecie()));
        assertNotNull(paciente.comNumeroColeira(paciente.getNumeroColeira()));
    }

    @Test
    void deve_mostrar_Paciente() throws IOException {
        PacientePersistence mock = Mockito.mock(PacientePersistence.class);

        List<Paciente> list = new ArrayList<>();

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


        list.add(paciente);

        Mockito.when(mock.salvarPacienteNoArquivo(paciente)).thenReturn(true);
        Mockito.when(mock.getList()).thenReturn(list);
        assertEquals("77", paciente.getNumeroColeira());
    }

    @Test
    void deve_Alterar_o_paciente() throws IOException {
        PacientePersistence mock = Mockito.mock(PacientePersistence.class);

        List<Paciente> list = new ArrayList<>();
        List<Paciente> alterada = new ArrayList<>();

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

        list.add(paciente);


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
                .comEspecie("Coelhos")
                .comNumeroColeira("77");

        alterada.add(paciente1);



        Mockito.when(mock.alterarPaciente(paciente)).thenReturn(true);
        Mockito.when(mock.getList()).thenReturn(list);

        PacienteService pacienteService = new PacienteService(mock);
        boolean retorno =  pacienteService.alterarPaciente(paciente1);
        assertTrue(retorno);
    }

    @Test
    void deve_Deletar_o_paciente() throws IOException {
        PacientePersistence mock = Mockito.mock(PacientePersistence.class);

        List<Paciente> list = new ArrayList<>();

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

        list.add(paciente);

        Mockito.when(mock.removerPacientePorId(paciente.getNumeroColeira())).thenReturn(true);
        Mockito.when(mock.getList()).thenReturn(list);

        PacienteService pacienteService = new PacienteService(mock);
        boolean retorno = pacienteService.removerPaciente(paciente.getNumeroColeira());
        assertTrue(retorno);
    }
}
