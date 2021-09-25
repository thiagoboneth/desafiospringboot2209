package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.persistence.ConsultaPersistence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private ConsultaPersistence consultaPersistence = new ConsultaPersistence();

    @PostMapping("/cadastra") //---->ok<------
    public ResponseEntity<ConsultaDTO> cadastraConsulta(@RequestBody ConsultaDTO payLoad, UriComponentsBuilder uriBuilder) {
        consultaPersistence.salvarConsultaNoArquivo(payLoad);
        URI uri = uriBuilder.path("/consultas/{codigo}").buildAndExpand(payLoad.getNumeroConsulta()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }

    @GetMapping("listar") //---->ok<------
    public List<ConsultaDTO> mostrarConsulta() {
        return consultaPersistence.buscarConsulta();
    }

    @PutMapping("/alterar/{codigo}")  //---->ok<------
    public ResponseEntity<ConsultaDTO> alterarConsulta(@RequestBody ConsultaDTO payLoad, UriComponentsBuilder uriBuilder) {
        consultaPersistence.alterarConsulta(payLoad);
        URI uri = uriBuilder.path("/alterado/{codigo}").buildAndExpand(payLoad.getNumeroConsulta()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }

    @DeleteMapping("/deleta/{codigo}") //---->ok<------
    public void removerConsulta(@PathVariable String codigo){
        consultaPersistence.removerConsultaPorId(codigo);
    }
}
