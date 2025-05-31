package com.deloitte.service_appointment.DTOs;

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


}
