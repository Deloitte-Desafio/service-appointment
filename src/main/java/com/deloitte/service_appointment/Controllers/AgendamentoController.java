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

    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> findAll() {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.findAll();
        return ResponseEntity.ok(agendamentos);
    }

    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> findById(@PathVariable Long id) {
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.findById(id);
        return ResponseEntity.ok(agendamentoResponseDTO);
    }

    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> create(@RequestBody @Valid AgendamentoRequestDTO agendamentoRequestDTO) {
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.create(agendamentoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoResponseDTO);
    }

    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid AgendamentoRequestDTO agendamentoRequestDTO) {
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.update(id, agendamentoRequestDTO);
        return ResponseEntity.ok(agendamentoResponseDTO);
    }

    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
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

    @GetMapping("/dashboard/profissional/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<List<AgendamentoDashboardDTO>> getProximosAgendamentosProfissional(@PathVariable Long id) {
        List<AgendamentoDashboardDTO> agendamentos = agendamentoService.buscarAgendamentosFuturosDoProfissional(id);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/profissional/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<List<AgendamentoDashboardDTO>> getAgendamentosProfissional(@PathVariable Long id) {
        List<AgendamentoDashboardDTO> agendamentos = agendamentoService.buscarAgendamentosProfissional(id);
        return ResponseEntity.ok(agendamentos);
    }

    @PutMapping("/{id}/cancelar")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<Void> cancelarAgendamentoPorProfissional(@PathVariable Long id) {
        agendamentoService.cancelarAgendamentoPorProfissional(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/concluir")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<Void> completarAgendamentoProfissional(@PathVariable Long id){
        agendamentoService.completarAgendamentoProfissional(id);
        return ResponseEntity.noContent().build();
    }
}