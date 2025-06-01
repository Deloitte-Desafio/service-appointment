package com.deloitte.service_appointment.DTOs;

import com.deloitte.service_appointment.enums.DiaDaSemana;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisponibilidadeRequestDTO {
    @NotNull(message = "O ID do profissional é obrigatório")
    private Long profissionalId;

    @NotNull(message = "O dia da semana é obrigatório")
    private DiaDaSemana diaDaSemana;

    @NotNull(message = "A hora de início é obrigatória")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaInicio;

    @NotNull(message = "A hora de fim é obrigatória")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaFim;
}