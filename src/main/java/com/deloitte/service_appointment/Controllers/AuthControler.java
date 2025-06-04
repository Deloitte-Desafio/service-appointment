package com.deloitte.service_appointment.Controllers;

import com.deloitte.service_appointment.DTOs.AuthenticationDTO;
import com.deloitte.service_appointment.DTOs.LoginResponseDTO;
import com.deloitte.service_appointment.DTOs.Mappers.UserMapper;
import com.deloitte.service_appointment.DTOs.UserRequestDTO;
import com.deloitte.service_appointment.DTOs.UserResponseDTO;
import com.deloitte.service_appointment.Entities.User;
import com.deloitte.service_appointment.Repositories.UserRepository;
import com.deloitte.service_appointment.Services.Impl.AuthorizationService;
import com.deloitte.service_appointment.Services.UserService;
import com.deloitte.service_appointment.config.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControler {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserResponseDTO dto = authorizationService.getLoggedUser(userDetails.getUsername());
        return ResponseEntity.ok(dto);
    }



    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO dto)
    {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }



    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO)
    {
        UserResponseDTO userResponseDTO = userService.create(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

}
