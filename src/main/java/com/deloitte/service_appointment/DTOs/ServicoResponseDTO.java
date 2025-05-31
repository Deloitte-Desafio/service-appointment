package com.deloitte.service_appointment.DTOs;

import com.deloitte.service_appointment.Entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Setter
public class ServicoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Integer duracaoMinutos;
    private User profissional_id;
}
