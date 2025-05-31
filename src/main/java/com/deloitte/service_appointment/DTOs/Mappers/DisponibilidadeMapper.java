package com.deloitte.service_appointment.DTOs.Mappers;

import com.deloitte.service_appointment.DTOs.DisponibilidadeRequestDTO;
import com.deloitte.service_appointment.DTOs.DisponibilidadeResponseDTO;
import com.deloitte.service_appointment.DTOs.ServicoRequestDTO;
import com.deloitte.service_appointment.Entities.Disponibilidade;
import com.deloitte.service_appointment.Entities.Servico;

public class DisponibilidadeMapper {
    public static DisponibilidadeResponseDTO toDTO(Disponibilidade disponibilidade) {
        if (disponibilidade == null) {
            return null;
        }
        Long profissionalId = disponibilidade.getProfissional() != null ? disponibilidade.getProfissional().getId() : null;
        return new DisponibilidadeResponseDTO(
                disponibilidade.getId(),
                profissionalId,
                disponibilidade.getDiaDaSemana(),
                disponibilidade.getHoraInicio(),
                disponibilidade.getHoraFim()
        );
    }

    public static Disponibilidade toEntity(DisponibilidadeRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Disponibilidade disponibilidade = new Disponibilidade();
        disponibilidade.setDiaDaSemana(dto.getDiaDaSemana());
        disponibilidade.setHoraInicio(dto.getHoraInicio());
        disponibilidade.setHoraFim(dto.getHoraFim());
        return disponibilidade;
    }

    public static void updateEntity(Disponibilidade disponibilidade, DisponibilidadeRequestDTO disponibilidadeRequestDTO) {
        if (disponibilidadeRequestDTO == null || disponibilidade == null) {
            return;
        }
        disponibilidade.setDiaDaSemana(disponibilidadeRequestDTO.getDiaDaSemana());
        disponibilidade.setHoraFim(disponibilidadeRequestDTO.getHoraFim());
        disponibilidade.setHoraInicio(disponibilidadeRequestDTO.getHoraInicio());
    }
}
