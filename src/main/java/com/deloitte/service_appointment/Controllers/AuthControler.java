package com.deloitte.service_appointment.Controllers;

import com.deloitte.service_appointment.DTOs.AuthenticationDTO;
import com.deloitte.service_appointment.DTOs.LoginResponseDTO;
import com.deloitte.service_appointment.Entities.User;
import com.deloitte.service_appointment.config.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControler {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO dto)
    {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
