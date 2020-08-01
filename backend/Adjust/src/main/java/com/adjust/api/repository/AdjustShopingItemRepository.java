package com.adjust.api.repository;

import com.adjust.api.domain.AdjustShopingItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdjustShopingItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjustShopingItemRepository extends JpaRepository<AdjustShopingItem, Long> {
}
