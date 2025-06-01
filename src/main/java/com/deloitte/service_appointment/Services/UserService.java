package com.deloitte.service_appointment.Services;

import com.deloitte.service_appointment.DTOs.User.UserUpdateDTO;
import com.deloitte.service_appointment.DTOs.UserRequestDTO;
import com.deloitte.service_appointment.DTOs.UserResponseDTO;
import com.deloitte.service_appointment.Entities.User;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> listarUser();

    UserResponseDTO encontrarUser(Long id);

    UserResponseDTO adicionarUser(UserRequestDTO userRequestDTO);

    UserResponseDTO atualizarPerfil(Long id, UserUpdateDTO dto);

    void deletarUser(Long id);
}
