package com.deloitte.service_appointment.Services.Impl;

import com.deloitte.service_appointment.DTOs.AgendamentoRequestDTO;
import com.deloitte.service_appointment.DTOs.AgendamentoResponseDTO;
import com.deloitte.service_appointment.DTOs.Mappers.AgendamentoMapper;
import com.deloitte.service_appointment.Entities.Agendamento;
import com.deloitte.service_appointment.Repositories.AgendamentoRepository;
import com.deloitte.service_appointment.Services.AgendamentoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<AgendamentoResponseDTO> findAll() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream()
                .map(AgendamentoMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public AgendamentoResponseDTO findById(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com id " + id));
        return AgendamentoMapper.toDTO(agendamento);
    }

    @Transactional
    @Override
    public AgendamentoResponseDTO create(AgendamentoRequestDTO entity) {
        Agendamento agendamento = AgendamentoMapper.toEntity(entity);
        Agendamento savedAgendamento = agendamentoRepository.save(agendamento);
        return AgendamentoMapper.toDTO(savedAgendamento);
    }
    @Transactional
    @Override
    public AgendamentoResponseDTO update(Long id, AgendamentoRequestDTO entity) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com id " + id));
        AgendamentoMapper.updateEntity(agendamento, entity);
        Agendamento updatedAgendamento = agendamentoRepository.save(agendamento);
        return AgendamentoMapper.toDTO(updatedAgendamento);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com id " + id));
        agendamentoRepository.delete(agendamento);
    }
}
