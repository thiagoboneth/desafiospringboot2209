package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    @Autowired
    private ProprietarioService proprietarioService;

/*    @PostMapping("/cadastrarold")
    public ResponseEntity<ProprietarioDTO> cadastraProprietario(@RequestBody ProprietarioDTO payLoad, UriComponentsBuilder uriBuilder) {
        proprietarioPersistence.salvarProprietarioNoArquivo(payLoad);
        URI uri = uriBuilder.path("/proprietarios/{codigo}").buildAndExpand(payLoad.getCpf()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }*/

    @PostMapping("/cadastrar")
    public ResponseEntity<ProprietarioDTO> cadastraProprietario(@RequestBody ProprietarioDTO proprietarioDTO, UriComponentsBuilder uriBuilder) {
        Proprietario proprietario = ProprietarioDTO.converte(proprietarioDTO);
        proprietarioService.cadastrarProprietario(proprietario);
        URI uri = uriBuilder.path("/proprietarios/{codigo}").buildAndExpand(proprietarioDTO.getCpf()).toUri();
        return ResponseEntity.created(uri).body(proprietarioDTO);
    }


/*    @GetMapping("/listar")
    public List<ProprietarioDTO> mostrarProprietario() {
        return proprietarioPersistence.buscarProprietario();
    }*/

/*    @PutMapping("/alterar/{cpf}")
    public ResponseEntity<ProprietarioDTO> alterarProprietario(@RequestBody ProprietarioDTO payLoad, UriComponentsBuilder uriBuilder) {
        proprietarioPersistence.atualizarProprietario(payLoad);
        URI uri = uriBuilder.path("/alterado/{cpf}").buildAndExpand(payLoad.getCpf()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }*/

    @DeleteMapping("/deletar/{cpf}")
    public void removerProprietario(@PathVariable String cpf) {
        proprietarioService.remover(cpf);
    }
}
