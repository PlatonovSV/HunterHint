package com.edify.hunterhint.repositories;

import com.edify.hunterhint.models.HuntingFarm;
import com.edify.hunterhint.models.HuntingOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface HuntingOfferRep extends JpaRepository<HuntingOffer, Long> {

    List<HuntingOffer> findByFarmId(int id);
}