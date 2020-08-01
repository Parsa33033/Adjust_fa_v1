package com.adjust.api.repository;

import com.adjust.api.domain.ShopingItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ShopingItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShopingItemRepository extends JpaRepository<ShopingItem, Long> {
}
