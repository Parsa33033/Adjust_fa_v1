package com.adjust.api.repository;

import com.adjust.api.domain.AdjustPrice;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdjustPrice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjustPriceRepository extends JpaRepository<AdjustPrice, Long> {
}
