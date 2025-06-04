package com.deloitte.service_appointment.DTOs.Agendamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDashboardDTO {
    private Long id;
    private String nomeProfissional;
    private String nomeCliente;
    private String nomeServico;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String status;
}
