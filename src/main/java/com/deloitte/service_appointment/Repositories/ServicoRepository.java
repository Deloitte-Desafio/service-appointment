package com.deloitte.service_appointment.Repositories;

import com.deloitte.service_appointment.Entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Long, Servico> {
}
