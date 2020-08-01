package com.adjust.api.repository;

import com.adjust.api.domain.AdjustFood;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdjustFood entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjustFoodRepository extends JpaRepository<AdjustFood, Long> {
}
