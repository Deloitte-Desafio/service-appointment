package com.deloitte.service_appointment.Services.Impl;

import com.deloitte.service_appointment.DTOs.Agendamento.AgendamentoDashboardDTO;
import com.deloitte.service_appointment.DTOs.AgendamentoRequestDTO;
import com.deloitte.service_appointment.DTOs.AgendamentoResponseDTO;
import com.deloitte.service_appointment.DTOs.Mappers.AgendamentoMapper;
import com.deloitte.service_appointment.Entities.Agendamento;
import com.deloitte.service_appointment.Entities.Servico;
import com.deloitte.service_appointment.Entities.User;
import com.deloitte.service_appointment.Repositories.AgendamentoRepository;
import com.deloitte.service_appointment.Repositories.ServicoRepository;
import com.deloitte.service_appointment.Repositories.UserRepository;
import com.deloitte.service_appointment.Services.AgendamentoService;
import com.deloitte.service_appointment.enums.Status;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    private static final long HORAS_ANTECEDENCIA_CANCELAMENTO = 24;


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

        User profissional = userRepository.findById(entity.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        Servico servico = servicoRepository.findById(entity.getServicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        User cliente = userRepository.findById(entity.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Agendamento agendamento = AgendamentoMapper.toEntity(entity);
        agendamento.setProfissional(profissional);
        agendamento.setServico(servico);
        agendamento.setCliente(cliente);
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



    @Transactional
    public void cancelarAgendamentoPorCliente(Long agendamentoId) {
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));

        if (agendamento.getStatus() == Status.CANCELADO_CLIENTE) {
            throw new IllegalStateException("Agendamento já está cancelado pelo cliente");
        }

        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime horarioLimiteCancelamento = agendamento.getDataHoraInicio().minusHours(HORAS_ANTECEDENCIA_CANCELAMENTO);

        if (agora.isAfter(horarioLimiteCancelamento)) {
            throw new IllegalStateException("Não é possível cancelar o agendamento com menos de "
                    + HORAS_ANTECEDENCIA_CANCELAMENTO + " horas de antecedência");
        }

        agendamento.setStatus(Status.CANCELADO_CLIENTE);
        agendamentoRepository.save(agendamento);
    }

    @Override
    public List<AgendamentoDashboardDTO> buscarAgendamentosFuturosDoCliente(Long clienteId) {
        User cliente = userRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        List<Agendamento> agendamentos = agendamentoRepository
                .findByClienteAndDataHoraInicioAfter(cliente, LocalDateTime.now());

        return agendamentos.stream()
                .map(agendamento -> new AgendamentoDashboardDTO(
                        agendamento.getId(),
                        agendamento.getProfissional().getNome(),
                        agendamento.getCliente().getNome(),
                        agendamento.getServico().getNome(),
                        agendamento.getDataHoraInicio(),
                        agendamento.getDataHoraFim(),
                        agendamento.getStatus().name()
                ))
                .collect(Collectors.toList());
    }

    public List<AgendamentoDashboardDTO> buscarAgendamentosFuturosDoProfissional(Long profissionalId) {
        LocalDateTime now = LocalDateTime.now();
        List<Agendamento> agendamentos = agendamentoRepository.findByProfissionalIdAndDataHoraInicioAfter(profissionalId, now);
        return agendamentos.stream()
                .map(this::convertToDashboardDTO)
                .collect(Collectors.toList());
    }

    private AgendamentoDashboardDTO convertToDashboardDTO(Agendamento agendamento) {
        AgendamentoDashboardDTO dto = new AgendamentoDashboardDTO();
        dto.setId(agendamento.getId());
        dto.setCliente(agendamento.getCliente().getNome());
        dto.setServico(agendamento.getServico().getNome());
        dto.setProfissional(agendamento.getProfissional().getNome());
        dto.setDataHoraInicio(agendamento.getDataHoraInicio());
        dto.setDataHoraFim(agendamento.getDataHoraFim());
        dto.setStatus(agendamento.getStatus().name());
        return dto;
    }


}
