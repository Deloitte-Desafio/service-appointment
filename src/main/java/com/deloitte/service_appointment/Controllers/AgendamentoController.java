package com.deloitte.service_appointment.Controllers;

import com.deloitte.service_appointment.DTOs.Agendamento.AgendamentoDashboardDTO;
import com.deloitte.service_appointment.DTOs.AgendamentoRequestDTO;
import com.deloitte.service_appointment.DTOs.AgendamentoResponseDTO;
import com.deloitte.service_appointment.Services.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PreAuthorize("hasRole('CLIENTE')")
    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> findAll() {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.findAll();
        return ResponseEntity.ok(agendamentos);
    }

    @PreAuthorize("hasRole('CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> findById(@PathVariable Long id) {
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.findById(id);
        return ResponseEntity.ok(agendamentoResponseDTO);
    }

    @PreAuthorize("hasRole('CLIENTE')")
    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> create(@RequestBody @Valid AgendamentoRequestDTO agendamentoRequestDTO) {
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.create(agendamentoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoResponseDTO);
    }

    @PreAuthorize("hasRole('CLIENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid AgendamentoRequestDTO agendamentoRequestDTO) {
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.update(id, agendamentoRequestDTO);
        return ResponseEntity.ok(agendamentoResponseDTO);
    }

    @PreAuthorize("hasRole('CLIENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        agendamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('CLIENTE')")
    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarAgendamento(@PathVariable Long id) {
        agendamentoService.cancelarAgendamentoPorCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('CLIENTE')")
    @GetMapping("/dashboard/cliente/{id}")
    public ResponseEntity<List<AgendamentoDashboardDTO>> getProximosAgendamentosCliente(@PathVariable Long id) {
        List<AgendamentoDashboardDTO> agendamentos = agendamentoService.buscarAgendamentosFuturosDoCliente(id);
        return ResponseEntity.ok(agendamentos);
    }
}