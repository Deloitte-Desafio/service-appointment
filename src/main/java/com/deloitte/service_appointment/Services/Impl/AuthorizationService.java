package com.deloitte.service_appointment.Services.Impl;

import com.deloitte.service_appointment.DTOs.Mappers.UserMapper;
import com.deloitte.service_appointment.DTOs.UserResponseDTO;
import com.deloitte.service_appointment.Entities.User;
import com.deloitte.service_appointment.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.deloitte.service_appointment.DTOs.Mappers.UserMapper.toDTO;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

    public UserResponseDTO getLoggedUser(String email) {
        var user = repository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com email: " + email);
        }

        return UserMapper.toDTO(user);
    }

}
