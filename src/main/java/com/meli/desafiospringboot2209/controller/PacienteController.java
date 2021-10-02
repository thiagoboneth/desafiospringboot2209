package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.dto.PacienteDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import com.meli.desafiospringboot2209.entity.Paciente;
import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.persistence.PacientePersistence;
import com.meli.desafiospringboot2209.service.PacienteService;
import com.meli.desafiospringboot2209.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    ProprietarioService proprietarioService;

    @Autowired
    PacienteService pacienteService;


    private PacientePersistence pacientePersistence = new PacientePersistence();

    @PostMapping("/cadastra")
    public ResponseEntity<PacienteDTO> cadastraPaciente(@RequestBody PacienteDTO pacienteDTO, UriComponentsBuilder uriBuilder) {
        Paciente paciente = PacienteDTO.converte(pacienteDTO, proprietarioService);
        pacienteService.cadastrarPaciente(paciente);
        URI uri = uriBuilder.path("/codigo/{codigo}").buildAndExpand(paciente.getNumeroColeira()).toUri();
        return ResponseEntity.created(uri).body(pacienteDTO);
    }

/*    @GetMapping("listar")
    public List<PacienteDTO> mostrarPacientes() {
        return pacientePersistence.buscarPaciente();
    }*/
//
//    @PutMapping("/alterar/{numeroColeira}")
//    public ResponseEntity<PacienteDTO> alterarPaciente(@RequestBody PacienteDTO payLoad, UriComponentsBuilder uriBuilder) {
//        pacientePersistence.alterarPaciente(payLoad);
//        URI uri = uriBuilder.path("/alterado/{numeroColeira}").buildAndExpand(payLoad.getNumeroColeira()).toUri();
//        return ResponseEntity.created(uri).body(payLoad);
//    }

//    @DeleteMapping("/deleta/{numeroColeira}")
//    public void removerPaciente(@PathVariable String numeroColeira){
//        pacientePersistence.removerPacientePorId(numeroColeira);
//    }
}