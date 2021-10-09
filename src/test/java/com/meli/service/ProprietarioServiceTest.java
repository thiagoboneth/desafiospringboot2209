package com.meli.service;

import static org.junit.jupiter.api.Assertions.*;

import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.persistence.ProprietarioPersistence;
import com.meli.desafiospringboot2209.service.ProprietarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class ProprietarioServiceTest {

    @Test
    void deveCadastrarProprietario() {
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);

        List<Proprietario> lista = new ArrayList<>();
        Proprietario proprietario = new Proprietario(
                "007.001.999-21",
                "James",
                "Bond",
                "28/07/73",
                "London ",
                "9900000000");

        lista.add(proprietario);

        Mockito.when(mock.salvarProprietarioNoArquivo(Mockito.any(Proprietario.class))).thenReturn(proprietario);
        Mockito.when(mock.getList()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mock);
        proprietarioService.buscarProprietario();
        boolean retorno = proprietarioService.cadastrarProprietario(proprietario);
        
        assertTrue(retorno); 
    }
    
    @Test
    void naoDeveCadastrarProprietarioQuandoReceberValoresNull() {
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);

        List<Proprietario> lista = new ArrayList<>();
        Proprietario proprietario = new Proprietario(
                null,
                null,
                null,
                null,
                null,
                null);

        lista.add(proprietario);

        Mockito.when(mock.verificaNull(proprietario)).thenReturn(true);
        Mockito.when(mock.salvarProprietarioNoArquivo(Mockito.any(Proprietario.class))).thenReturn(proprietario);
        Mockito.when(mock.getList()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mock);
        proprietarioService.cadastrarProprietario(proprietario);

        assertNull(proprietario.getCpf());
        assertNull(proprietario.getNome());
        assertNull(proprietario.getSobrenome());
        assertNull(proprietario.getDataNascimento());
        assertNull(proprietario.getEndereco());
        assertNull(proprietario.getTelefone());
    }

    @Test
    void deveMostrarProprietario() {
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);
        List<Proprietario> proprietarioList = new ArrayList<>();
        Proprietario proprietario = new Proprietario(
                "001.000.000-22",
                "Louise",
                "Hein",
                "25/12/88",
                "Avenida Atlas nº32",
                "9910000000");
        
        proprietarioList.add(proprietario);
        
        
        Mockito.when(mock.salvarProprietarioNoArquivo(proprietario)).thenReturn(proprietario);
        Mockito.when(mock.getList()).thenReturn(proprietarioList);
        Mockito.doReturn(mock.proprietarioJaCadastrado(proprietario.getCpf()));
        
        ProprietarioService proprietarioService = new ProprietarioService(mock);
        proprietarioService.buscarProprietario();
        
        assertEquals("001.000.000-22", proprietario.getCpf());
    }

    @Test
    void deveAlterarProprietario() {
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);

        List<Proprietario> lista = new ArrayList<>();
        Proprietario proprietario = new Proprietario(
                "001.000.00-99",
                "Carlos",
                "de Nobrega da Praca é Nossa",
                "07/05/56",
                "Avenida das Nacoes Nº 1001",
                "9911335578");

        lista.add(proprietario);

        Mockito.when(mock.alterarProprietario(proprietario)).thenReturn(true);
        Mockito.when(mock.getList()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mock);
        boolean retorno =  proprietarioService.alterarProprietario(proprietario);
        assertTrue(retorno);
    }
    
    @Test
    void naoDeveAlterarProprietario() {
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);

        List<Proprietario> lista = new ArrayList<>();
        Proprietario proprietario = new Proprietario(
                "001.000.00-99",
                null,
                "de Nobrega da Praca é Nossa",
                "07/05/56",
                "Avenida das Nacoes Nº 1001",
                "9911335578");

        lista.add(proprietario);

        Mockito.when(mock.verificaNull(proprietario)).thenReturn(true);
        Mockito.when(mock.alterarProprietario(proprietario)).thenReturn(true);

        ProprietarioService proprietarioService = new ProprietarioService(mock);
        boolean retorno =  proprietarioService.alterarProprietario(proprietario);
        assertFalse(retorno);
    }
    
    @Test
    void deveDeletarProprietario() {
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);

        List<Proprietario> lista = new ArrayList<>();
        Proprietario proprietario = new Proprietario(
                "001.000.00-99",
                "Carlos",
                "de Nobrega da Praca é Nossa",
                "07/05/56",
                "Avenida das Nacoes Nº 1001",
                "9911335578");

        lista.add(proprietario);

        Mockito.when(mock.removerProprietario(proprietario.toString())).thenReturn(true);
        Mockito.when(mock.getList()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mock);
        Proprietario proprietario1 = proprietarioService.obterProprietario(proprietario.getCpf());
        proprietarioService.removerProprietario(proprietario.getCpf());
        assertNotNull(proprietario1.getCpf());
    }
}
