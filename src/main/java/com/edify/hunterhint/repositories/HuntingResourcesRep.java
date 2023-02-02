package com.edify.hunterhint.repositories;

import com.edify.hunterhint.models.HuntingResources;
import com.edify.hunterhint.models.MunicipalDistrict;
import com.edify.hunterhint.models.Region;
import com.edify.hunterhint.models.ResourcesCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HuntingResourcesRep extends JpaRepository<HuntingResources, Long> {
    List<HuntingResources> findByName(String name);

    default int getOneByName(String name) {
        if (name == null || name.isEmpty()) {
            return -1;
        } else {
            List<HuntingResources> huntingResources = findByName(name);
            if (!huntingResources.isEmpty()) {
                return huntingResources.get(0).getId();
            } else {
                return -1;
            }
        }
    }
}