package com.deloitte.service_appointment.Services.Impl;

import com.deloitte.service_appointment.DTOs.Mappers.ServicoMapper;
import com.deloitte.service_appointment.DTOs.ServicoRequestDTO;
import com.deloitte.service_appointment.DTOs.ServicoResponseDTO;
import com.deloitte.service_appointment.Entities.Servico;
import com.deloitte.service_appointment.Repositories.ServicoRepository;
import com.deloitte.service_appointment.Services.ServicoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.deloitte.service_appointment.DTOs.Mappers.ServicoMapper.toDTO;

@Service
public class ServicoServiceImp implements ServicoService {

    @Autowired
    ServicoRepository servicoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ServicoResponseDTO> findAll() {
        List<Servico> servicos = servicoRepository.findAll();
        return servicos.stream().map(ServicoMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ServicoResponseDTO findById(Long id) {
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Serviço com id" + id + " não encontrado."));
        return toDTO(servico);
    }

    @Transactional
    @Override
    public ServicoResponseDTO create(ServicoRequestDTO servicoRequestDTO) {
        Servico servico = ServicoMapper.toEntity(servicoRequestDTO);
        servico = servicoRepository.save(servico);
        return toDTO(servico);
    }

    @Transactional
    @Override
    public ServicoResponseDTO update(Long id, ServicoRequestDTO servicoRequestDTO) {
        Servico existingServico = servicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Serviço com id " + id + " não encontrado."));
        Servico updatedServico = ServicoMapper.toEntity(servicoRequestDTO);
        updatedServico.setId(id);
        updatedServico = servicoRepository.save(updatedServico);
        return ServicoMapper.toDTO(updatedServico);
    }



    @Transactional
    @Override
    public void delete(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Serviço com id " + id + " não encontrado.");
        }
        servicoRepository.deleteById(id);
    }
}
