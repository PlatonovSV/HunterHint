package com.edify.hunterhint.repositories;

import com.edify.hunterhint.models.CompanyName;
import com.edify.hunterhint.models.UserName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserNameRep extends JpaRepository<UserName, Long> {
    List<UserName> findAllByName(String name);
}
