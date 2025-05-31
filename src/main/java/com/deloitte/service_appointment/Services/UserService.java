package com.deloitte.service_appointment.Services;

import com.deloitte.service_appointment.DTOs.UserRequestDTO;
import com.deloitte.service_appointment.DTOs.UserResponseDTO;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> listarUser();

    UserResponseDTO encontrarUser(Long id);

    UserResponseDTO adicionarUser(UserRequestDTO userRequestDTO);

    //UserResponseDTO editarUser(UserRequestDTO userRequestDTO,Long id);

    void deletarUser(Long id);
}
