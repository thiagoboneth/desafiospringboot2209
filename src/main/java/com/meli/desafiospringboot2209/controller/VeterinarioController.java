package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.crudteste.Crud;
import com.meli.desafiospringboot2209.entity.Veterinario;
import com.meli.desafiospringboot2209.persistence.VeterinarioPersistence;
import com.meli.desafiospringboot2209.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/Veterinario")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @PostMapping("cadastra")
    public ResponseEntity<Veterinario> cadastro (@RequestBody Veterinario payLoad, UriComponentsBuilder uriBuilder){
        //Veterinario veterinario = Veterinario.convert(payload);
        Veterinario veterinario = new Veterinario();
        veterinarioService.cadastrar(veterinario);
        URI uri = uriBuilder.path("/veterinarios/{codigo}").buildAndExpand(veterinario.getNumeroRegistro()).toUri();
        return ResponseEntity.created(uri).body(veterinario);
    }
    @DeleteMapping("/deleta/{codigo}")
    public void cadastro(@PathVariable("cdg")String codigo){
        veterinarioService.remover(codigo);
    }
    @GetMapping("/handler")
    public void handler(){
        throw new ArithmeticException("Deu ruim ...");
    }

    @PostMapping("/cadastraa")
    public Veterinario cadastraVeterinario(@RequestBody Veterinario veterinario) throws IOException {
        veterinarioService.cadastraMedico(veterinario);
        return veterinario;
    }
}
