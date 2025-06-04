package com.deloitte.service_appointment.Services;

import com.deloitte.service_appointment.DTOs.ServicoRequestDTO;
import com.deloitte.service_appointment.DTOs.ServicoResponseDTO;

import java.util.List;

public interface ServicoService extends CrudService<Long, ServicoResponseDTO, ServicoRequestDTO> {
    List<ServicoResponseDTO> findByProfessionalId(Long profissionalId);
}
