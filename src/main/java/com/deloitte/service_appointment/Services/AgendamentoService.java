package com.deloitte.service_appointment.Services;

import com.deloitte.service_appointment.DTOs.AgendamentoRequestDTO;
import com.deloitte.service_appointment.DTOs.AgendamentoResponseDTO;

public interface AgendamentoService extends CrudService<Long, AgendamentoResponseDTO, AgendamentoRequestDTO>{

    void cancelarAgendamentoPorCliente(Long agendamentoId);
}
