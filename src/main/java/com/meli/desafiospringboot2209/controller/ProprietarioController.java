package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
import com.meli.desafiospringboot2209.persistence.ProprietarioPersistence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    private ProprietarioPersistence proprietarioPersistence = new ProprietarioPersistence();

    @GetMapping("/listar") //---->ok<------
    public List<ProprietarioDTO> mostrarVeterinarios() {

        return proprietarioPersistence.buscarProprietario();
    }

    @PostMapping("/cadastrar") //---->ok<------
    public ResponseEntity<ProprietarioDTO> cadastraProprietario(@RequestBody ProprietarioDTO payLoad, UriComponentsBuilder uriBuilder) {
        proprietarioPersistence.salvarProprietarioNoArquivo(payLoad);
        URI uri = uriBuilder.path("/proprietarios/{codigo}").buildAndExpand(payLoad.getCpf()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }

    @DeleteMapping("/deletar/{cpf}") //---->ok<------

    public void cadastro(@PathVariable String cpf) {
        proprietarioPersistence.removerProprietarioPorCpf(cpf);
    }

    @PutMapping("/alterar/{cpf}") //---->ok<------
    public ResponseEntity<ProprietarioDTO> alterarVeterinario(@RequestBody ProprietarioDTO payLoad, UriComponentsBuilder uriBuilder) {
        proprietarioPersistence.atualizarProprietario(payLoad);
        URI uri = uriBuilder.path("/alterado/{cpf}").buildAndExpand(payLoad.getCpf()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }
}
