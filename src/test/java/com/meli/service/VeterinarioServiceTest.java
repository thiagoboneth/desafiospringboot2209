package com.meli.service;

import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.VeterinarioPersistence;
import com.meli.desafiospringboot2209.service.VeterinarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class VeterinarioServiceTest {

    @Test
    void deve_Cadastrar_oVeterinario() {
        VeterinarioPersistence mock = Mockito.mock(VeterinarioPersistence.class);

        List<Veterinario> veterinarioList = new ArrayList<>();
        Veterinario veterinario = new Veterinario(
                "001.000.000-10",
                "Filipe",
                "Gomes",
                "18/09/1991",
                "1010",
                "Animais de Fazenda");


        veterinarioList.add(veterinario);

        Mockito.when(mock.salvarVeterinarioNoArquivo(veterinario)).thenReturn(veterinario);
        Mockito.when(mock.getList()).thenReturn(veterinarioList);

        VeterinarioService veterinarioService = new VeterinarioService(mock);
        boolean retorno = veterinarioService.cadastrarVeterinario(veterinario);
        assertTrue(retorno);
    }
    @Test
    void deve_mostrarVeterinario() {
        VeterinarioPersistence mock = Mockito.mock(VeterinarioPersistence.class);

        List<Veterinario> veterinarioList = new ArrayList<>();
        Veterinario veterinario= new Veterinario(
                "001.000.000-22",
                "Louise",
                "Hein",
                "25/12/88",
                "Avenida Atlas nº32",
                "9910000000");

        veterinarioList.add(veterinario);

        Mockito.when(mock.salvarVeterinarioNoArquivo(veterinario)).thenReturn(veterinario);
        Mockito.when(mock.getList()).thenReturn(veterinarioList);
        assertEquals("001.000.000-22", veterinario.getCpf());
    }

    @Test
    void deve_Alterar_oVeterinario() {
        VeterinarioPersistence mock = Mockito.mock(VeterinarioPersistence.class);

        List<Veterinario> veterinarioList = new ArrayList<>();
        Veterinario veterinario = new Veterinario(
                "001.030.00-99",
                "Carlos",
                "de Nobrega da Praca é Nossa",
                "07/05/56",
                "1015",
                "Animais Domésticos");

        veterinarioList.add(veterinario);

        Mockito.when(mock.alterarVeterinario(veterinario)).thenReturn(true);
        Mockito.when(mock.getList()).thenReturn(veterinarioList);

        VeterinarioService veterinarioService = new VeterinarioService(mock);
        boolean retorno = veterinarioService.alterarVeterinario(veterinario);
        assertTrue(retorno);
    }
    @Test
    void deve_Deletar_oVeterinario() {
        VeterinarioPersistence mock = Mockito.mock(VeterinarioPersistence.class);

        List<Veterinario> veterinarioList = new ArrayList<>();
        Veterinario veterinario = new Veterinario(
                "001.000.00-99",
                "Carlos",
                "de Nobrega da Praca é Nossa",
                "07/05/56",
                "1015",
                "Animais de Fazenda");

         veterinarioList.add(veterinario);

        Mockito.when(mock.removerVeterinarioPorRegistro(veterinario.toString())).thenReturn(true);
        Mockito.when(mock.getList()).thenReturn(veterinarioList);

        VeterinarioService veterinarioService = new VeterinarioService(mock);
        boolean retorno =  veterinarioService.removerVeterinarioPorRegistro(veterinario.toString());
        assertTrue(retorno);
    }
}

