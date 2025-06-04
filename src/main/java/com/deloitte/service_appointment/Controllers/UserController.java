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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;



    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> findAll()
    {
        List<UserResponseDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }


    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id)
    {
        UserResponseDTO userResponseDTO = userService.findById(id);
        return ResponseEntity.ok(userResponseDTO);
    }




    public ResponseEntity<UserResponseDTO> atualizarPerfil(@PathVariable Long id,@Valid @RequestBody UserRequestDTO userRequestDTO)
    {
        UserResponseDTO atualizado = userService.update(id, userRequestDTO);
        return ResponseEntity.ok(atualizado);
    }


    public ResponseEntity <UserResponseDTO> deleteUser(@PathVariable Long id)
    {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
