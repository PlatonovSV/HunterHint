package com.edify.hunterhint.repositories;

import com.edify.hunterhint.models.MunicipalDistrict;
import com.edify.hunterhint.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRep extends JpaRepository<Region, Long> {

    List<Region> findByName(String string);
}
