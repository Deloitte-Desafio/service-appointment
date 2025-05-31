package com.deloitte.service_appointment.Services.ServiceImpl;

import com.deloitte.service_appointment.DTOs.Mappers.UserMapper;
import com.deloitte.service_appointment.DTOs.UserRequestDTO;
import com.deloitte.service_appointment.DTOs.UserResponseDTO;
import com.deloitte.service_appointment.Entities.User;
import com.deloitte.service_appointment.Repositories.UserRepository;
import com.deloitte.service_appointment.Services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.deloitte.service_appointment.DTOs.Mappers.UserMapper.toDTO;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<UserResponseDTO> listarUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDTO encontrarUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente com id " + id + " não encontrado."));
        return toDTO(user);
    }

    @Transactional
    @Override
    public UserResponseDTO adicionarUser(UserRequestDTO userRequestDTO) {
        User user = UserMapper.toEntity(userRequestDTO);
        return toDTO(user);
    }



    @Transactional
    @Override
    public void deletarUser(Long id) {
        userRepository.deleteById(id);
    }
}
