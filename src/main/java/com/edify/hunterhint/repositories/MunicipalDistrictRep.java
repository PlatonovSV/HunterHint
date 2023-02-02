package com.edify.hunterhint.repositories;

import com.edify.hunterhint.models.HuntingFarm;
import com.edify.hunterhint.models.MunicipalDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipalDistrictRep extends JpaRepository<MunicipalDistrict, Long> {
    List<MunicipalDistrict> findByNameAndRegion(String name, Short code);
}