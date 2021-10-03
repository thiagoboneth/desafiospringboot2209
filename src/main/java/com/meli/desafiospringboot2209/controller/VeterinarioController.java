package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.dto.VeterinarioDTO;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/veterinario")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    //Post Ok
    @PostMapping("/cadastrar")
    public ResponseEntity<VeterinarioDTO> cadastrarVeterinario(@RequestBody VeterinarioDTO veterinarioDTO, UriComponentsBuilder uriBuilder) {
        Veterinario veterinario = VeterinarioDTO.converte(veterinarioDTO);
        veterinarioService.cadastrarVeterinario(veterinario);
        URI uri = uriBuilder.path("/veterinarios/{codigo}").buildAndExpand(veterinarioDTO.getNumeroRegistro()).toUri();
        return ResponseEntity.created(uri).body(veterinarioDTO);
    }

    //Get Ok
    @GetMapping("/listar")
    public List<Veterinario> mostrarVeterinario() {
        return veterinarioService.buscarVeterinario();
    }

    //Put ok
    @PutMapping("/alterar/{numeroRegistro}")
    public ResponseEntity<Veterinario> alterarVeterinario(@RequestBody Veterinario veterinario, UriComponentsBuilder uriBuilder) {
        veterinarioService.alterarVeterinario(veterinario);
        URI uri = uriBuilder.path("/alterado/{numeroRegistro}").buildAndExpand(veterinario.getNumeroRegistro()).toUri();
        return ResponseEntity.created(uri).body(veterinario);
    }

    //Delete ok
    @DeleteMapping("/deletar/{numeroRegistro}")
    public void removerVeterinario(@PathVariable String numeroRegistro) {
        veterinarioService.removerVeterinarioPorRegistro(numeroRegistro);
    }
}