package com.deloitte.service_appointment.DTOs;

import com.deloitte.service_appointment.Entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Integer duracaoMinutos;
    private Long profissionalId;


}
