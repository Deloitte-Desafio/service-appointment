package com.deloitte.service_appointment.Controllers;

import com.deloitte.service_appointment.DTOs.AgendamentoRequestDTO;
import com.deloitte.service_appointment.DTOs.AgendamentoResponseDTO;
import com.deloitte.service_appointment.Services.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> findAll() {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.findAll();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> findById(@PathVariable Long id) {
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.findById(id);
        return ResponseEntity.ok(agendamentoResponseDTO);
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> create(@RequestBody @Valid AgendamentoRequestDTO agendamentoRequestDTO) {
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.create(agendamentoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid AgendamentoRequestDTO agendamentoRequestDTO) {
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.update(id, agendamentoRequestDTO);
        return ResponseEntity.ok(agendamentoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        agendamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}