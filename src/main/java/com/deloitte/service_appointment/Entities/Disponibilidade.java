package com.deloitte.service_appointment.Entities;

import com.deloitte.service_appointment.enums.DiaDaSemana;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "tb_disponibilidade")
@Getter
@Setter
@NoArgsConstructor
public class Disponibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id", nullable = false)
    private User profissional;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiaDaSemana diaDaSemana;

    @NotNull
    @Column(nullable = false)
    private LocalTime horaInicio;

    @NotNull
    @Column(nullable = false)
    private LocalTime horaFim;

    public Disponibilidade(User profissional, DiaDaSemana diaDaSemana, LocalTime horaInicio, LocalTime horaFim) {
        this.profissional = profissional;
        this.diaDaSemana = diaDaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }
}
