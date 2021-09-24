package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.dto.ProprietarioDTO;
import com.meli.desafiospringboot2209.persistence.ProprietarioPersistence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    private ProprietarioPersistence proprietarioPersistence = new ProprietarioPersistence();

    @PostMapping("/cadastra") //---->ok<------
    public ResponseEntity<ProprietarioDTO> cadastraproprietario(@RequestBody ProprietarioDTO payLoad, UriComponentsBuilder uriBuilder) {
        proprietarioPersistence.salvarProprietarioNoArquivo(payLoad);
        URI uri = uriBuilder.path("/proprietarios/{codigo}").buildAndExpand(payLoad.getCpf()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }

}
