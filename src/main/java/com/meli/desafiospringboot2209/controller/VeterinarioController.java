package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.dto.VeterinarioDTO;
import com.meli.desafiospringboot2209.persistence.VeterinarioPersistence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/veterinario")
public class VeterinarioController {

    private VeterinarioPersistence veterinarioPersistence = new VeterinarioPersistence();

    @PostMapping("/cadastra")
    public ResponseEntity<VeterinarioDTO> cadastraVeterinario(@RequestBody VeterinarioDTO payLoad, UriComponentsBuilder uriBuilder) {
        veterinarioPersistence.salvarVeterinarioNoArquivo(payLoad);
        URI uri = uriBuilder.path("/veterinarios/{codigo}").buildAndExpand(payLoad.getNumeroRegistro()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }

    @GetMapping("listar")
    public List<VeterinarioDTO> mostrarVeterinario() {
        return veterinarioPersistence.buscarVeterinario();
    }

    @PutMapping("/alterar/{codigo}")
    public ResponseEntity<VeterinarioDTO> alterarVeterinario(@RequestBody VeterinarioDTO payLoad, UriComponentsBuilder uriBuilder) {
        veterinarioPersistence.alterarVeterinario(payLoad);
        URI uri = uriBuilder.path("/alterado/{codigo}").buildAndExpand(payLoad.getNumeroRegistro()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }

    @DeleteMapping("/deleta/{codigo}")
    public void removerVeterinario(@PathVariable String codigo){
        veterinarioPersistence.removerMedicoPorId(codigo);
    }
}