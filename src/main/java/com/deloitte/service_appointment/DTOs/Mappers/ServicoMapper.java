package com.deloitte.service_appointment.DTOs.Mappers;

import com.deloitte.service_appointment.DTOs.ServicoRequestDTO;
import com.deloitte.service_appointment.DTOs.ServicoResponseDTO;
import com.deloitte.service_appointment.Entities.Servico;
import com.deloitte.service_appointment.Entities.User;

public class ServicoMapper {

    public static Servico toEntity(ServicoRequestDTO dto){
        Servico servico = new Servico();
        servico.setNome(dto.getNome());
        servico.setDescricao(dto.getDescricao());
        servico.setDuracaoMinutos(dto.getDuracaoMinutos());
        servico.setProfissional_id(dto.getProfissional_id());

        return servico;
    }

    public static ServicoResponseDTO toDTO(Servico servico){
        ServicoResponseDTO dto = new ServicoResponseDTO();
        dto.setId(servico.getId());
        dto.setNome(servico.getNome());
        dto.setDescricao(servico.getDescricao());
        dto.setDuracaoMinutos(servico.getDuracaoMinutos());
        dto.setProfissional_id(servico.getProfissional_id());
        return dto;
    }
}
