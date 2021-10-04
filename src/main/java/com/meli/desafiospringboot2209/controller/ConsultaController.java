package com.meli.desafiospringboot2209.controller;

import com.meli.desafiospringboot2209.entity.Proprietario;
import com.meli.desafiospringboot2209.service.PacienteService;
import com.meli.desafiospringboot2209.service.ProprietarioService;
import com.meli.desafiospringboot2209.service.VeterinarioService;
import com.meli.desafiospringboot2209.dto.ConsultaDTO;
import com.meli.desafiospringboot2209.entity.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.meli.desafiospringboot2209.service.ConsultaService;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/cadastra")
    public ResponseEntity<ConsultaDTO> cadastraConsulta(@RequestBody ConsultaDTO consultaDTO, UriComponentsBuilder uriBuilder){
        Consulta consulta = ConsultaDTO.converte(consultaDTO, veterinarioService, pacienteService);
        consultaService.marcaConsulta(consulta);
        URI uri = uriBuilder.path("/consultas/{codigo}").buildAndExpand(consulta.getNumeroConsulta()).toUri();
        return ResponseEntity.created(uri).body(consultaDTO);
    }


    @GetMapping("/listar")
    public List<Consulta> mostrarConsulta() {
        return consultaService.buscarConsulta("C");
    }

   @GetMapping("listarPorDia/{data}")
    public List<Consulta> mostrarConsultaPorDia(@PathVariable ("data") String data) throws IOException {
        return consultaService.consultasDoDia(data);
    }

/*    @GetMapping("/listarPorPaciente")
    public List<ConsultaDTO> mostrarConsultaPaciente() throws IOException {
        return consultaService.consultaPaciente();
    }*/

    @PutMapping("/alterar/{codigo}")
    public ResponseEntity<Consulta> alterarConsulta(@RequestBody Consulta payLoad, UriComponentsBuilder uriBuilder) {
        consultaService.alterarConsulta(payLoad);
        URI uri = uriBuilder.path("/alterado/{codigo}").buildAndExpand(payLoad.getNumeroConsulta()).toUri();
        return ResponseEntity.created(uri).body(payLoad);
    }

    @DeleteMapping("/deleta/{codigo}")
    public void removerConsultaPorId(@PathVariable String codigo){
        consultaService.removerConsultaPorId(codigo);
    }

/*    @GetMapping("/listarTotalCadaVeterinario")
    public List<String> listaTotalCadaVeterinario() throws IOException {
        return consultaService.listarTotalCadaVeterinario();
    }*/
}

