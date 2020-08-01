package com.adjust.api.repository;

import com.adjust.api.domain.AdjustNutrition;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdjustNutrition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjustNutritionRepository extends JpaRepository<AdjustNutrition, Long> {
}
