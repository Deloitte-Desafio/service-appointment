package com.deloitte.service_appointment.Repositories;

import com.deloitte.service_appointment.DTOs.DisponibilidadeResponseDTO;
import com.deloitte.service_appointment.Entities.Disponibilidade;
import com.deloitte.service_appointment.Entities.User;
import com.deloitte.service_appointment.enums.DiaDaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {
    List<Disponibilidade> findByProfissionalId(Long professionalId);
    List<Disponibilidade> findByProfissional(User profissional);
    List<Disponibilidade> findByProfissionalAndDiaDaSemana(User profissional, DiaDaSemana diaDaSemana);
}