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


    @GetMapping
    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    public ResponseEntity<List<ServicoResponseDTO>> findAll() {
        List<ServicoResponseDTO> servicos = servicoService.findAll();
        return ResponseEntity.ok(servicos);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    public ResponseEntity<ServicoResponseDTO> findById(@PathVariable Long id) {
        ServicoResponseDTO servicoResponseDTO = servicoService.findById(id);
        return ResponseEntity.ok(servicoResponseDTO);
    }


    @PostMapping
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<ServicoResponseDTO> create(@RequestBody @Valid ServicoRequestDTO servicoRequestDTO) {
        ServicoResponseDTO servicoResponseDTO = servicoService.create(servicoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoResponseDTO);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<ServicoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ServicoRequestDTO servicoRequestDTO) {
        ServicoResponseDTO servicoResponseDTO = servicoService.update(id, servicoRequestDTO);
        return ResponseEntity.ok(servicoResponseDTO);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profissional/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<List<ServicoResponseDTO>> getServicesByProfessional(@PathVariable Long id) {
        List<ServicoResponseDTO> services = servicoService.findByProfessionalId(id);
        return ResponseEntity.ok(services);
    }
}
