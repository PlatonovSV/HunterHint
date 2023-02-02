package com.edify.hunterhint.repositories;

import com.edify.hunterhint.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRep extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}