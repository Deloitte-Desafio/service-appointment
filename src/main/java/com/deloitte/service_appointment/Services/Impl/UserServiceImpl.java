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
        user = userRepository.save(user);
        return toDTO(user);
    }

    @Transactional
    public UserResponseDTO atualizarPerfil(Long userId, UserUpdateDTO dto) {
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

        if (dto.getNovaSenha() != null && !dto.getNovaSenha().isEmpty()) {
            user.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
        }

        User userAtualizado = userRepository.save(user);

        return new UserResponseDTO(userAtualizado);
    }




    @Transactional
    @Override
    public void deletarUser(Long id) {
        userRepository.deleteById(id);
    }
}
