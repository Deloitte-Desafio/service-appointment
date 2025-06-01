package com.deloitte.service_appointment.Services.Impl;

import com.deloitte.service_appointment.DTOs.DisponibilidadeRequestDTO;
import com.deloitte.service_appointment.DTOs.DisponibilidadeResponseDTO;
import com.deloitte.service_appointment.DTOs.Mappers.DisponibilidadeMapper;
import com.deloitte.service_appointment.Entities.Disponibilidade;
import com.deloitte.service_appointment.Entities.User;
import com.deloitte.service_appointment.Repositories.DisponibilidadeRepository;
import com.deloitte.service_appointment.Repositories.UserRepository;
import com.deloitte.service_appointment.Services.DisponibilidadeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DisponibilidadeServiceImpl implements DisponibilidadeService {

    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<DisponibilidadeResponseDTO> findAll() {
        List<Disponibilidade> disponibilidades = disponibilidadeRepository.findAll();

        return disponibilidades.stream()
                .map(DisponibilidadeMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public DisponibilidadeResponseDTO findById(Long id) {
        Disponibilidade disponibilidade = disponibilidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disponibilidade não encontrada com id " +id));
        return DisponibilidadeMapper.toDTO(disponibilidade);
    }
    @Transactional
    @Override
    public DisponibilidadeResponseDTO create(DisponibilidadeRequestDTO entity) {
        User profissional = userRepository.findById(entity.getProfissionalId())
                .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"));
        Disponibilidade disponibilidade = DisponibilidadeMapper.toEntity(entity);
        disponibilidade.setProfissional(profissional);
        Disponibilidade savedDisponibilidade = disponibilidadeRepository.save(disponibilidade);
        return DisponibilidadeMapper.toDTO(savedDisponibilidade);
    }

    @Override
    public DisponibilidadeResponseDTO update(Long id, DisponibilidadeRequestDTO entity) {
        Disponibilidade disponibilidade = disponibilidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disponibilidade não encontrada com id " + id));

        DisponibilidadeMapper.updateEntity(disponibilidade, entity);
        Disponibilidade updatedDisponibilidade = disponibilidadeRepository.save(disponibilidade);
        return DisponibilidadeMapper.toDTO(updatedDisponibilidade);
    }

    @Override
    public void delete(Long id) {
        if (!disponibilidadeRepository.existsById(id)) {
            throw new EntityNotFoundException("Disponibilidade com id " + id + " não encontrado.");
        }
        disponibilidadeRepository.deleteById(id);

    }
}
