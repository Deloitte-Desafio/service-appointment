package com.deloitte.service_appointment.Controllers;

import com.deloitte.service_appointment.DTOs.DisponibilidadeRequestDTO;
import com.deloitte.service_appointment.DTOs.DisponibilidadeResponseDTO;
import com.deloitte.service_appointment.Services.DisponibilidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disponibilidades")
public class DisponibilidadeController {

    @Autowired
    private DisponibilidadeService disponibilidadeService;


    @GetMapping
    public ResponseEntity<List<DisponibilidadeResponseDTO>> findAll() {
        List<DisponibilidadeResponseDTO> disponibilidades = disponibilidadeService.findAll();
        return ResponseEntity.ok(disponibilidades);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadeResponseDTO> findById(@PathVariable Long id) {
        DisponibilidadeResponseDTO disponibilidadeResponseDTO = disponibilidadeService.findById(id);
        return ResponseEntity.ok(disponibilidadeResponseDTO);
    }


    @PostMapping
    public ResponseEntity<DisponibilidadeResponseDTO> create(@RequestBody @Valid DisponibilidadeRequestDTO disponibilidadeRequestDTO) {
        DisponibilidadeResponseDTO disponibilidadeResponseDTO = disponibilidadeService.create(disponibilidadeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(disponibilidadeResponseDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadeResponseDTO> update(@PathVariable Long id, @RequestBody @Valid DisponibilidadeRequestDTO disponibilidadeRequestDTO) {
        DisponibilidadeResponseDTO disponibilidadeResponseDTO = disponibilidadeService.update(id, disponibilidadeRequestDTO);
        return ResponseEntity.ok(disponibilidadeResponseDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        disponibilidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profissional/{proId}")
    public ResponseEntity<List<DisponibilidadeResponseDTO>> getProfessionalAvailabilities(@PathVariable Long proId) {
        List<DisponibilidadeResponseDTO> availabilities = disponibilidadeService.getAvailabilitiesByProfessionalId(proId);
        return ResponseEntity.ok(availabilities);
    }
}