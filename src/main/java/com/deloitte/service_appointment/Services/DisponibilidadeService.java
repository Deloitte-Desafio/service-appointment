package com.deloitte.service_appointment.Services;

import com.deloitte.service_appointment.DTOs.DisponibilidadeRequestDTO;
import com.deloitte.service_appointment.DTOs.DisponibilidadeResponseDTO;

import java.util.List;

public interface DisponibilidadeService extends CrudService<Long, DisponibilidadeResponseDTO, DisponibilidadeRequestDTO>{
    List<DisponibilidadeResponseDTO> getAvailabilitiesByProfessionalId(Long proId);
}
