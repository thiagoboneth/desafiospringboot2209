package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.dto.VeterinarioDTO;
import com.meli.desafiospringboot2209.persistence.VeterinarioPersistence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping("/veterinario")
public class VeterinarioController {

    private VeterinarioPersistence veterinarioPersistence = new VeterinarioPersistence();

    @PostMapping("/cadastra") //---->ok<------
    public ResponseEntity<VeterinarioDTO> cadastraVeterinario(@RequestBody VeterinarioDTO payLoad, UriComponentsBuilder uriBuilder) {
        veterinarioPersistence.salvarVeterinarioNoArquivo(payLoad);
        URI uri = uriBuilder.path("/veterinarios/{codigo}").buildAndExpand(payLoad.getNumeroRegistro()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }


    @DeleteMapping("/deleta/{codigo}")//---->ok<------
    public void cadastro(@PathVariable String codigo){
        veterinarioPersistence.removerMedicoPorId(codigo);
    }

    @PutMapping("/alterar/{codigo}")//---->ok<------
    public ResponseEntity<VeterinarioDTO> alterarVeterinario(@RequestBody VeterinarioDTO payLoad, UriComponentsBuilder uriBuilder) {
        veterinarioPersistence.alterarVeterinario(payLoad);
        URI uri = uriBuilder.path("/alterado/{codigo}").buildAndExpand(payLoad.getNumeroRegistro()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }
}