package com.adjust.api.repository;

import com.adjust.api.domain.Nutrition;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Nutrition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
}
