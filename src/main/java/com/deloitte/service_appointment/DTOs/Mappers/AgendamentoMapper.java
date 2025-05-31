package com.deloitte.service_appointment.DTOs.Mappers;

import com.deloitte.service_appointment.DTOs.AgendamentoRequestDTO;
import com.deloitte.service_appointment.DTOs.AgendamentoResponseDTO;
import com.deloitte.service_appointment.Entities.Agendamento;

public class AgendamentoMapper {

    public static AgendamentoResponseDTO toDTO(Agendamento agendamento) {
        if (agendamento == null) {
            return null;
        }
        Long clienteId = agendamento.getCliente() != null ? agendamento.getCliente().getId() : null;
        Long profissionalId = agendamento.getProfissional() != null ? agendamento.getProfissional().getId() : null;
        Long servicoId = agendamento.getServico() != null ? agendamento.getServico().getId() : null;
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                clienteId,
                profissionalId,
                servicoId,
                agendamento.getDataHoraInicio(),
                agendamento.getDataHoraFim(),
                agendamento.getStatus()
        );
    }

    public static Agendamento toEntity(AgendamentoRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        Agendamento agendamento = new Agendamento();
        agendamento.setDataHoraInicio(requestDTO.getDataHoraInicio());
        agendamento.setDataHoraFim(requestDTO.getDataHoraFim());
        agendamento.setStatus(requestDTO.getStatus());
        return agendamento;
    }

    public static void updateEntity(Agendamento agendamento, AgendamentoRequestDTO requestDTO) {
        if (requestDTO == null || agendamento == null) {
            return;
        }
        agendamento.setDataHoraInicio(requestDTO.getDataHoraInicio());
        agendamento.setDataHoraFim(requestDTO.getDataHoraFim());
        agendamento.setStatus(requestDTO.getStatus());
    }
}