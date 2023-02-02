package com.edify.hunterhint.repositories;

import com.edify.hunterhint.models.Booking;
import com.edify.hunterhint.models.HuntingFarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface BookingRep extends JpaRepository<Booking, Long> {
    List<Booking> findByOfferId(int id);
    List<Booking> findByFarmId(int id);
    List<Booking> findByUserId(int id);
}