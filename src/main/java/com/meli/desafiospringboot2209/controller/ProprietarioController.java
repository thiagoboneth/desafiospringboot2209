package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    @Autowired
    private ProprietarioService proprietarioService;

    // Endpoint - Falta verificar cadastro com parametros nulos
    @PostMapping("/cadastrar")
    public ResponseEntity<ProprietarioDTO> cadastraProprietario(@RequestBody ProprietarioDTO proprietarioDTO, UriComponentsBuilder uriBuilder) {
        Proprietario proprietario = ProprietarioDTO.converte(proprietarioDTO);
        proprietarioService.cadastrarProprietario(proprietario);
        URI uri = uriBuilder.path("/proprietarios/{codigo}").buildAndExpand(proprietarioDTO.getCpf()).toUri();
        return ResponseEntity.created(uri).body(proprietarioDTO);
    }

    // Endpoint - OK
    @GetMapping("/listar")
    public List<Proprietario> mostrarProprietario() {
        return proprietarioService.buscarProprietario();
    }

    // Endipoint - Falta verificar alteracao com parametros nulos
    @PutMapping("/alterar/{cpf}")
    public ResponseEntity<Proprietario> alterarProprietario(@RequestBody Proprietario proprietario, UriComponentsBuilder uriBuilder) {
        proprietarioService.alterarProprietario(proprietario);
        URI uri = uriBuilder.path("/alterado/{cpf}").buildAndExpand(proprietario.getCpf()).toUri();
        return ResponseEntity.created(uri).body(proprietario);
    }

    // Endipoint - OK
    @DeleteMapping("/deletar/{cpf}")
    public void removerProprietario(@PathVariable String cpf) {
        proprietarioService.removerProprietario(cpf);
    }
}
