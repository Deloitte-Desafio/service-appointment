package com.deloitte.service_appointment.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationDTO {
    private String email;

    private String senha;
}
