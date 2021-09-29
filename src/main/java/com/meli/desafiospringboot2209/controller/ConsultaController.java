package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.Service.ConsultaService;
import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private ConsultaService consultaService = new ConsultaService();

    @PostMapping("/cadastra")
    public ResponseEntity<ConsultaDTO> cadastraConsulta(@RequestBody ConsultaDTO payLoad, UriComponentsBuilder uriBuilder) {
        Consulta consulta = ConsultaDTO.converte(payLoad);
        consultaService.cadastrar(consulta);
        URI uri = uriBuilder.path("/consultas/{codigo}").buildAndExpand(payLoad.getNumeroConsulta()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }

/*    @GetMapping("/listar")
    public List<ConsultaDTO> mostrarConsulta() {
        return consultaService.buscarConsulta();
    }*/

/*    @GetMapping("listarPorDia/{data}")
    public List<ConsultaDTO> mostrarConsultaPorDia(@PathVariable ("data") String data) throws IOException {
        return consultaService.consultasDoDia(data);
    }*/

/*    @GetMapping("/listarPorPaciente")
    public List<ConsultaDTO> mostrarConsultaPaciente() throws IOException {
        return consultaService.consultaPaciente();
    }*/

    @PutMapping("/alterar/{codigo}")
    public ResponseEntity<ConsultaDTO> alterarConsulta(@RequestBody ConsultaDTO payLoad, UriComponentsBuilder uriBuilder) {
        Consulta consulta = ConsultaDTO.converte(payLoad);
        consultaService.alterarConsulta(consulta);
        URI uri = uriBuilder.path("/alterado/{codigo}").buildAndExpand(payLoad.getNumeroConsulta()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }

/*
    @DeleteMapping("/deleta/{codigo}")
    public void removerConsulta(@PathVariable String codigo){
        consultaService.removerConsultaPorId(codigo);
    }
*/

    @GetMapping("/listarTotalCadaVeterinario")
    public List<String> listaTotalCadaVeterinario() throws IOException {
        return consultaService.listarTotalCadaVeterinario();
    }
}
