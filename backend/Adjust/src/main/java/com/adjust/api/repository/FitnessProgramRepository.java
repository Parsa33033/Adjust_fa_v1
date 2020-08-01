package com.adjust.api.repository;

import com.adjust.api.domain.FitnessProgram;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FitnessProgram entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FitnessProgramRepository extends JpaRepository<FitnessProgram, Long> {
}
