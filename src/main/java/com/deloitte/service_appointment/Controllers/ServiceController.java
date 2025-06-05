package com.deloitte.service_appointment.Controllers;

import com.deloitte.service_appointment.DTOs.ServicoRequestDTO;
import com.deloitte.service_appointment.DTOs.ServicoResponseDTO;
import com.deloitte.service_appointment.Services.ServicoService;
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
@RequestMapping("/servicos")
@Tag(name = "Serviços", description = "Endpoints para gerenciamento de serviços")
public class ServiceController {

    @Autowired
    private ServicoService servicoService;

    @Operation(summary = "Busca todos os serviços")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de serviços retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = ServicoResponseDTO.class)))
    })
    @GetMapping
    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    public ResponseEntity<List<ServicoResponseDTO>> findAll() {
        List<ServicoResponseDTO> servicos = servicoService.findAll();
        return ResponseEntity.ok(servicos);
    }

    @Operation(summary = "Busca um serviço por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço retornado com sucesso",
                    content = @Content(schema = @Schema(implementation = ServicoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('PROFISSIONAL')")
    public ResponseEntity<ServicoResponseDTO> findById(@PathVariable Long id) {
        ServicoResponseDTO servicoResponseDTO = servicoService.findById(id);
        return ResponseEntity.ok(servicoResponseDTO);
    }


    @Operation(summary = "Cria um novo serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso",
                    content = @Content(schema = @Schema(implementation = ServicoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<ServicoResponseDTO> create(@RequestBody @Valid ServicoRequestDTO servicoRequestDTO) {
        ServicoResponseDTO servicoResponseDTO = servicoService.create(servicoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoResponseDTO);
    }

    @Operation(summary = "Atualiza um serviço por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = ServicoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<ServicoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ServicoRequestDTO servicoRequestDTO) {
        ServicoResponseDTO servicoResponseDTO = servicoService.update(id, servicoRequestDTO);
        return ResponseEntity.ok(servicoResponseDTO);
    }

    @Operation(summary = "Deleta um serviço por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serviço deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Busca serviços de um profissional por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de serviços retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = ServicoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    @GetMapping("/profissional/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public ResponseEntity<List<ServicoResponseDTO>> getServicesByProfessional(@PathVariable Long id) {
        List<ServicoResponseDTO> services = servicoService.findByProfessionalId(id);
        return ResponseEntity.ok(services);
    }
}
