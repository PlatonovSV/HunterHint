package com.edify.hunterhint.repositories;

import com.edify.hunterhint.models.ResourcesCost;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourcesCostRep extends JpaRepository<ResourcesCost, Long> {
    List<ResourcesCost> findByOfferId(int id);
}