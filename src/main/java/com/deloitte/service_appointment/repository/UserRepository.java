package com.deloitte.service_appointment.repository;

import com.deloitte.service_appointment.Entities.User;
import com.deloitte.service_appointment.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    List<User> findByUserType(UserType userType);
}
