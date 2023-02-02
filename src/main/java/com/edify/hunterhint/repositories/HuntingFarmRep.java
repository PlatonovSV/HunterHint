package com.edify.hunterhint.repositories;

import com.edify.hunterhint.models.HuntingFarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface HuntingFarmRep extends JpaRepository<HuntingFarm, Long> {

    List<HuntingFarm> findByRegionCodeAndMunicipalDistrictId(short code, int id);
    List<HuntingFarm> findByRegionCode(short code);
    List<HuntingFarm> findByUserId(int id);

}