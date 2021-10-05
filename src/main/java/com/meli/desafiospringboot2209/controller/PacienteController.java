package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.dto.PacienteDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.persistence.PacientePersistence;
import com.meli.desafiospringboot2209.service.ProprietarioService;
import com.meli.desafiospringboot2209.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private PacientePersistence pacientePersistence = new PacientePersistence();
    @Autowired
    private ProprietarioService proprietarioService;

    @PostMapping("/cadastra")
    public ResponseEntity<PacienteDTO> cadastraPaciente(@RequestBody PacienteDTO pacienteDTO, UriComponentsBuilder uriBuilder) throws IOException {
        Paciente paciente = PacienteDTO.converte(pacienteDTO, proprietarioService);
        pacientePersistence.salvarPacienteNoArquivo(paciente);
        URI uri = uriBuilder.path("/codigo/{codigo}").buildAndExpand(pacienteDTO.getNumeroColeira()).toUri();
        return ResponseEntity.created(uri).body(pacienteDTO);
    }

    @GetMapping("listar")
    public List<Paciente> mostrarPacientes() {
        return pacientePersistence.buscarPaciente();
    }

    @PutMapping("/alterar/{numeroColeira}")
    public ResponseEntity<PacienteDTO> alterarPaciente(@RequestBody PacienteDTO payLoad, UriComponentsBuilder uriBuilder) {
        Paciente paciente = PacienteDTO.converte(payLoad, proprietarioService);
        pacientePersistence.alterarPaciente(paciente);
        URI uri = uriBuilder.path("/alterado/{numeroColeira}").buildAndExpand(payLoad.getNumeroColeira()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }

    @DeleteMapping("/deleta/{numeroColeira}")
    public boolean removerPaciente(@PathVariable String numeroColeira) throws IOException {
        return pacientePersistence.removerPacientePorId(numeroColeira);

    }
}