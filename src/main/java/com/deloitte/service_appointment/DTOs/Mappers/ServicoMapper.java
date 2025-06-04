package com.deloitte.service_appointment.DTOs.Mappers;

import com.deloitte.service_appointment.DTOs.ServicoRequestDTO;
import com.deloitte.service_appointment.DTOs.ServicoResponseDTO;
import com.deloitte.service_appointment.Entities.Servico;
import com.deloitte.service_appointment.Entities.User;
import org.springframework.stereotype.Component;

@Component
public class ServicoMapper {

    public static ServicoResponseDTO toDTO(Servico servico) {
        if (servico == null) {
            return null;
        }
        Long profissionalId = servico.getProfissional() != null ? servico.getProfissional().getId() : null;
        return new ServicoResponseDTO(
                servico.getId(),
                servico.getNome(),
                servico.getDescricao(),
                servico.getDuracaoMinutos(),
                profissionalId
        );
    }

    public static Servico toEntity(ServicoRequestDTO servicoRequestDTO) {
        if (servicoRequestDTO == null) {
            return null;
        }
        Servico servico = new Servico();
        servico.setNome(servicoRequestDTO.getNome());
        servico.setDescricao(servicoRequestDTO.getDescricao());
        servico.setDuracaoMinutos(servicoRequestDTO.getDuracaoMinutos());
        return servico;
    }

    public static void updateEntity(Servico servico, ServicoRequestDTO servicoRequestDTO) {
        if (servicoRequestDTO == null || servico == null) {
            return;
        }
        servico.setNome(servicoRequestDTO.getNome());
        servico.setDescricao(servicoRequestDTO.getDescricao());
        servico.setDuracaoMinutos(servicoRequestDTO.getDuracaoMinutos());
    }
}
