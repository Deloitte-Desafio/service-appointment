package com.deloitte.service_appointment.Repositories;

import com.deloitte.service_appointment.Entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByProfissionalId(Long profissionalId);
}
