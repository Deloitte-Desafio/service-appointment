package com.deloitte.service_appointment.Controllers;

import com.deloitte.service_appointment.DTOs.AuthenticationDTO;
import com.deloitte.service_appointment.DTOs.LoginResponseDTO;
import com.deloitte.service_appointment.DTOs.UserRequestDTO;
import com.deloitte.service_appointment.DTOs.UserResponseDTO;
import com.deloitte.service_appointment.Entities.User;
import com.deloitte.service_appointment.Services.UserService;
import com.deloitte.service_appointment.config.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> findAll()
    {
        List<UserResponseDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id)
    {
        UserResponseDTO userResponseDTO = userService.findById(id);
        return ResponseEntity.ok(userResponseDTO);
    }
    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO dto)
    {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO)
    {
        UserResponseDTO userResponseDTO = userService.create(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarPerfil(@PathVariable Long id,@Valid @RequestBody UserRequestDTO userRequestDTO)
    {
        UserResponseDTO atualizado = userService.update(id, userRequestDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <UserResponseDTO> deleteUser(@PathVariable Long id)
    {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
