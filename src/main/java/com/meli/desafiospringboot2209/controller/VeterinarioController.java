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

    //cadastrar Ok
    @PostMapping("/cadastrar")
    public ResponseEntity<VeterinarioDTO> cadastrarVeterinario(@RequestBody VeterinarioDTO veterinarioDTO, UriComponentsBuilder uriBuilder) {
        Veterinario veterinario = VeterinarioDTO.converte(veterinarioDTO);
        veterinarioService.cadastrarVeterinario(veterinario);
        URI uri = uriBuilder.path("/veterinarios/{codigo}").buildAndExpand(veterinarioDTO.getNumeroRegistro()).toUri();
        return ResponseEntity.created(uri).body(veterinarioDTO);
    }
    //listar Ok
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

    @DeleteMapping("/deletar/{numeroRegistro}")
    public void removerVeterinario(@PathVariable String numeroRegistro){
        veterinarioService.removerVeterinarioPorRegistro(numeroRegistro);
    }


   /* private VeterinarioPersistence veterinarioPersistence = new VeterinarioPersistence();

    @PostMapping("/cadastra")
    public ResponseEntity<VeterinarioDTO> cadastraVeterinario(@RequestBody VeterinarioDTO veterinarioDTO, UriComponentsBuilder uriBuilder) {
        veterinarioPersistence.salvarVeterinarioNoArquivo(veterinarioDTO);
        URI uri = uriBuilder.path("/veterinarios/{codigo}").buildAndExpand(veterinarioDTO.getNumeroRegistro()).toUri();
        return ResponseEntity.created(uri).body(veterinarioDTO);
    }

    @GetMapping("listar")
    public List<VeterinarioDTO> mostrarVeterinario() {
        return veterinarioPersistence.buscarVeterinario();
    }

    @PutMapping("/alterar/{codigo}")
    public ResponseEntity<VeterinarioDTO> alterarVeterinario(@RequestBody VeterinarioDTO veterinarioDTO, UriComponentsBuilder uriBuilder) {
        veterinarioPersistence.alterarVeterinario(veterinarioDTO);
        URI uri = uriBuilder.path("/alterado/{codigo}").buildAndExpand(veterinarioDTO.getNumeroRegistro()).toUri();
        return ResponseEntity.created(uri).body(veterinarioDTO);
    }

    @DeleteMapping("/deleta/{codigo}")
    public v*//*oid removerVeterinario(@PathVariable String codigo){
        veterinarioPersistence.removerMedicoPorId(codigo);
    }
*/}