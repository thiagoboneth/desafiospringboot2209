package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.dto.PacienteDTO;
import com.meli.desafiospringboot2209.dto.VeterinarioDTO;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.persistence.PacientePersistence;
import com.meli.desafiospringboot2209.persistence.VeterinarioPersistence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private PacientePersistence pacientePersistence = new PacientePersistence();

    @GetMapping("listar")
    public List<PacienteDTO> mostrarPacientes() {
        return pacientePersistence.buscarPaciente();
    }

    @PostMapping("/cadastra") //---->ok<------
    public ResponseEntity<PacienteDTO> cadastraPaciente(@RequestBody PacienteDTO payLoad, UriComponentsBuilder uriBuilder) {
        pacientePersistence.salvarPacienteNoArquivo(payLoad);
        URI uri = uriBuilder.path("/codigo/{codigo}").buildAndExpand(payLoad.getNumeroColeira()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }

    @DeleteMapping("/deleta/{codigo}")//---->ok<------
    public void removerPaciente(@PathVariable String codigo){
        pacientePersistence.removerPacientePorId(codigo);
    }

    @PutMapping("/alterar/{codigo}")//---->ok<------
    public ResponseEntity<PacienteDTO> alterarPaciente(@RequestBody PacienteDTO payLoad, UriComponentsBuilder uriBuilder) {
        pacientePersistence.alterarPaciente(payLoad);
        URI uri = uriBuilder.path("/alterado/{codigo}").buildAndExpand(payLoad.getNumeroColeira()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }


}