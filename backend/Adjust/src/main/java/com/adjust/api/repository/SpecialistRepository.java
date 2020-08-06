package com.adjust.api.repository;

import com.adjust.api.domain.Specialist;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Specialist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    Optional<Specialist> findByUsername(String username);
}
