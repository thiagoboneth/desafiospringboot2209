package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.dto.VeterinarioDTO;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.VeterinarioPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
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

/*    @DeleteMapping("/deleta/{codigo}")
    public void cadastro(@PathVariable String codigo) throws IOException {
        veterinarioService.remover(codigo);
    }*/

}