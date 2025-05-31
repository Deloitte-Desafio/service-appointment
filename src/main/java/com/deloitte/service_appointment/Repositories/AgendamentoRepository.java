package com.deloitte.service_appointment.Repositories;

import com.deloitte.service_appointment.Entities.Agendamento;
import com.deloitte.service_appointment.Entities.User;
import com.deloitte.service_appointment.enums.Status;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByCliente(User cliente);

    List<Agendamento> findByProfissional(User profissional);

    List<Agendamento> findByProfissionalAndStatus(User profissional, Status status);

    List<Agendamento> findByProfissionalAndDataHoraInicioBetween(
            User profissional, LocalDateTime inicio, LocalDateTime fim);

    List<Agendamento> findByClienteAndDataHoraInicioBetween(
            User cliente, LocalDateTime inicio, LocalDateTime fim);

    List<Agendamento> findByProfissionalAndStatusAndDataHoraInicioBetween(
            User profissional, Status status, LocalDateTime inicio, LocalDateTime fim);
}
