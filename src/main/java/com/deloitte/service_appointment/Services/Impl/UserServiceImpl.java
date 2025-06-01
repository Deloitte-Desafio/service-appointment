package com.deloitte.service_appointment.Services.Impl;

import com.deloitte.service_appointment.DTOs.Mappers.UserMapper;
import com.deloitte.service_appointment.DTOs.User.UserUpdateDTO;
import com.deloitte.service_appointment.DTOs.UserRequestDTO;
import com.deloitte.service_appointment.DTOs.UserResponseDTO;
import com.deloitte.service_appointment.Entities.User;
import com.deloitte.service_appointment.Repositories.UserRepository;
import com.deloitte.service_appointment.Services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.deloitte.service_appointment.DTOs.Mappers.UserMapper.toDTO;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<UserResponseDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente com id " + id + " não encontrado."));
        return toDTO(user);
    }

    @Transactional
    @Override
    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
        User user = UserMapper.toEntity(userRequestDTO);
        if (user.getSenha() != null && !user.getSenha().isEmpty()) {
            user.setSenha(passwordEncoder.encode(user.getSenha()));
        }
        user = userRepository.save(user);
        return toDTO(user);
    }


    @Transactional
    public UserResponseDTO update(Long userId, UserRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(dto.getEmail())) {
                throw new RuntimeException("E-mail já está em uso");
            }
            user.setEmail(dto.getEmail());
        }

        if (dto.getNome() != null) {
            user.setNome(dto.getNome());
        }

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            user.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        User userAtualizado = userRepository.save(user);

        return new UserResponseDTO(userAtualizado);
    }




    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
