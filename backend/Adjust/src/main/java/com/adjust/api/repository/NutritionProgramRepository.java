package com.adjust.api.repository;

import com.adjust.api.domain.NutritionProgram;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NutritionProgram entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NutritionProgramRepository extends JpaRepository<NutritionProgram, Long> {
}
