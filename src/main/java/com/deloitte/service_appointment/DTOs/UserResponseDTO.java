package com.deloitte.service_appointment.DTOs;

import com.deloitte.service_appointment.Entities.User;
import com.deloitte.service_appointment.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private UserType tipoUsuario;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.tipoUsuario = user.getTipoUsuario();
    }


    public UserResponseDTO() {

    }
}
