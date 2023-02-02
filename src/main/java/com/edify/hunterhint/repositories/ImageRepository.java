package com.edify.hunterhint.repositories;


import com.edify.hunterhint.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
