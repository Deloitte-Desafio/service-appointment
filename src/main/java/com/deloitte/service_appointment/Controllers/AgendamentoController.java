package com.deloitte.service_appointment.Controllers;

import com.deloitte.service_appointment.DTOs.Agendamento.AgendamentoDashboardDTO;
import com.deloitte.service_appointment.DTOs.AgendamentoRequestDTO;
import com.deloitte.service_appointment.DTOs.AgendamentoResponseDTO;
import com.deloitte.service_appointment.Services.AgendamentoService;
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
@RequestMapping("/agendamentos")
@Tag(name = "Agendamentos", description = "Endpoints para gerenciamento de agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Operation(summary = "Busca todos os agendamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de agendamentos retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = AgendamentoResponseDTO.class)))
    })
    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> findAll() {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.findAll();
        return ResponseEntity.ok(agendamentos);
    }

    @Operation(summary = "Busca um agendamento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento retornado com sucesso",
                    content = @Content(schema = @Schema(implementation = AgendamentoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> findById(@PathVariable Long id) {
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.findById(id);
        return ResponseEntity.ok(agendamentoResponseDTO);
    }

    @Operation(summary = "Cria um novo agendamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso",
                    content = @Content(schema = @Schema(implementation = AgendamentoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> create(@RequestBody @Valid AgendamentoRequestDTO agendamentoRequestDTO) {
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.create(agendamentoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoResponseDTO);
    }

    @Operation(summary = "Atualiza um agendamento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = AgendamentoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid AgendamentoRequestDTO agendamentoRequestDTO) {
        AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.update(id, agendamentoRequestDTO);
        return ResponseEntity.ok(agendamentoResponseDTO);
    }

    @Operation(summary = "Deleta um agendamento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        agendamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Cancela um agendamento por cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento cancelado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PreAuthorize("hasRole('CLIENTE')")
    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarAgendamento(@PathVariable Long id) {
        agendamentoService.cancelarAgendamentoPorCliente(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Busca os próximos agendamentos de um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de agendamentos futuros retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = AgendamentoDashboardDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PreAuthorize("hasRole('CLIENTE')")
    @GetMapping("/dashboard/cliente/{id}")
    public ResponseEntity<List<AgendamentoDashboardDTO>> getProximosAgendamentosCliente(@PathVariable Long id) {
        List<AgendamentoDashboardDTO> agendamentos = agendamentoService.buscarAgendamentosFuturosDoCliente(id);
        return ResponseEntity.ok(agendamentos);
    }

    @Operation(summary = "Busca os próximos agendamentos de um profissional na data de hoje")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de agendamentos futuros retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = AgendamentoDashboardDTO.class))),
            @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    @GetMapping("/dashboard/profissional/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<List<AgendamentoDashboardDTO>> getProximosAgendamentosProfissional(@PathVariable Long id) {
        List<AgendamentoDashboardDTO> agendamentos = agendamentoService.buscarAgendamentosFuturosDoProfissional(id);
        return ResponseEntity.ok(agendamentos);
    }

    @Operation(summary = "Busca todos os agendamentos de um profissional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de agendamentos retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = AgendamentoDashboardDTO.class))),
            @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    @GetMapping("/profissional/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<List<AgendamentoDashboardDTO>> getAgendamentosProfissional(@PathVariable Long id) {
        List<AgendamentoDashboardDTO> agendamentos = agendamentoService.buscarAgendamentosProfissional(id);
        return ResponseEntity.ok(agendamentos);
    }

    @Operation(summary = "Cancela um agendamento por profissional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento cancelado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PutMapping("/{id}/cancelar")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<Void> cancelarAgendamentoPorProfissional(@PathVariable Long id) {
        agendamentoService.cancelarAgendamentoPorProfissional(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Conclui um agendamento por profissional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento concluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PutMapping("/{id}/concluir")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<Void> completarAgendamentoProfissional(@PathVariable Long id){
        agendamentoService.completarAgendamentoProfissional(id);
        return ResponseEntity.noContent().build();
    }
}