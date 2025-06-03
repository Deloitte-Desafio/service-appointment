package com.deloitte.service_appointment.Controllers;

import com.deloitte.service_appointment.DTOs.ServicoRequestDTO;
import com.deloitte.service_appointment.DTOs.ServicoResponseDTO;
import com.deloitte.service_appointment.Services.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServiceController {

    @Autowired
    private ServicoService servicoService;


    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> findAll() {
        List<ServicoResponseDTO> servicos = servicoService.findAll();
        return ResponseEntity.ok(servicos);
    }

    @PreAuthorize("hasRole('PROFISSIONAL')")
    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> findById(@PathVariable Long id) {
        ServicoResponseDTO servicoResponseDTO = servicoService.findById(id);
        return ResponseEntity.ok(servicoResponseDTO);
    }

    @PreAuthorize("hasRole('PROFISSIONAL')")
    @PostMapping
    public ResponseEntity<ServicoResponseDTO> create(@RequestBody @Valid ServicoRequestDTO servicoRequestDTO) {
        ServicoResponseDTO servicoResponseDTO = servicoService.create(servicoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoResponseDTO);
    }

    @PreAuthorize("hasRole('PROFISSIONAL')")
    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ServicoRequestDTO servicoRequestDTO) {
        ServicoResponseDTO servicoResponseDTO = servicoService.update(id, servicoRequestDTO);
        return ResponseEntity.ok(servicoResponseDTO);
    }

    @PreAuthorize("hasRole('PROFISSIONAL')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
