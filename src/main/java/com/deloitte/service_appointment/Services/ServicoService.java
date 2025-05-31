package com.deloitte.service_appointment.Services;

import com.deloitte.service_appointment.DTOs.ServicoRequestDTO;
import com.deloitte.service_appointment.DTOs.ServicoResponseDTO;
import com.deloitte.service_appointment.Entities.Servico;

import java.lang.reflect.Type;

public interface ServicoService extends CrudService<Long, ServicoResponseDTO, ServicoRequestDTO> {
}
