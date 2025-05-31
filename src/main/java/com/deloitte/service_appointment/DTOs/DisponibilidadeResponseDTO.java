package com.deloitte.service_appointment.DTOs;

import com.deloitte.service_appointment.enums.DiaDaSemana;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisponibilidadeResponseDTO {
    private Long id;
    private Long profissionalId;
    private DiaDaSemana diaDaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
}