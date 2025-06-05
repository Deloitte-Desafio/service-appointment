package com.deloitte.service_appointment.Controllers;

import com.deloitte.service_appointment.DTOs.DisponibilidadeRequestDTO;
import com.deloitte.service_appointment.DTOs.DisponibilidadeResponseDTO;
import com.deloitte.service_appointment.Services.DisponibilidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disponibilidades")
@Tag(name = "Disponibilidades", description = "Endpoints para gerenciamento de disponibilidades")
public class DisponibilidadeController {

    @Autowired
    private DisponibilidadeService disponibilidadeService;


    @Operation(summary = "Busca todas as disponibilidades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de disponibilidades retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = DisponibilidadeResponseDTO.class)))
    })
    @GetMapping
    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    public ResponseEntity<List<DisponibilidadeResponseDTO>> findAll() {
        List<DisponibilidadeResponseDTO> disponibilidades = disponibilidadeService.findAll();
        return ResponseEntity.ok(disponibilidades);
    }


    @Operation(summary = "Busca uma disponibilidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidade retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = DisponibilidadeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Disponibilidade não encontrada")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    public ResponseEntity<DisponibilidadeResponseDTO> findById(@PathVariable Long id) {
        DisponibilidadeResponseDTO disponibilidadeResponseDTO = disponibilidadeService.findById(id);
        return ResponseEntity.ok(disponibilidadeResponseDTO);
    }


    @Operation(summary = "Cria uma nova disponibilidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Disponibilidade criada com sucesso",
                    content = @Content(schema = @Schema(implementation = DisponibilidadeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<DisponibilidadeResponseDTO> create(@RequestBody @Valid DisponibilidadeRequestDTO disponibilidadeRequestDTO) {
        DisponibilidadeResponseDTO disponibilidadeResponseDTO = disponibilidadeService.create(disponibilidadeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(disponibilidadeResponseDTO);
    }

    @Operation(summary = "Atualiza uma disponibilidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidade atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = DisponibilidadeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Disponibilidade não encontrada")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<DisponibilidadeResponseDTO> update(@PathVariable Long id, @RequestBody @Valid DisponibilidadeRequestDTO disponibilidadeRequestDTO) {
        DisponibilidadeResponseDTO disponibilidadeResponseDTO = disponibilidadeService.update(id, disponibilidadeRequestDTO);
        return ResponseEntity.ok(disponibilidadeResponseDTO);
    }

    @Operation(summary = "Deleta uma disponibilidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Disponibilidade deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disponibilidade não encontrada")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        disponibilidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Busca disponibilidades de um profissional por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de disponibilidades retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = DisponibilidadeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    @GetMapping("/profissional/{proId}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<List<DisponibilidadeResponseDTO>> getProfessionalAvailabilities(@PathVariable Long proId) {
        List<DisponibilidadeResponseDTO> availabilities = disponibilidadeService.getAvailabilitiesByProfessionalId(proId);
        return ResponseEntity.ok(availabilities);
    }
}