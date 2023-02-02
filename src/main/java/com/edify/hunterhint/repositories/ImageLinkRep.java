package com.edify.hunterhint.repositories;

import com.edify.hunterhint.models.HuntingResources;
import com.edify.hunterhint.models.ImageLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageLinkRep extends JpaRepository<ImageLink, Long> {
    List<ImageLink> findAllByOwnerId(int id);

}