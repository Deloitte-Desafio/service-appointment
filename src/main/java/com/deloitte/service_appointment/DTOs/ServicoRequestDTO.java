package com.deloitte.service_appointment.DTOs;

import com.deloitte.service_appointment.Entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServicoRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Descricão é obrigatória")
    private String descricao;
    @Min(value = 20)
    private Integer duracaoMinutos;
    @NotNull(message = "O ID do profissional é obrigatório")
    private Long profissionalId;
}
