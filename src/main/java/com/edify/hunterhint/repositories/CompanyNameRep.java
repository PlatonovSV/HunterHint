package com.edify.hunterhint.repositories;

import com.edify.hunterhint.models.Booking;
import com.edify.hunterhint.models.CompanyName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyNameRep extends JpaRepository<CompanyName, Long> {
    List<CompanyName> findByName(String name);
}