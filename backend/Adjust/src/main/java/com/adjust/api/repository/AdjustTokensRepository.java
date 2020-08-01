package com.adjust.api.repository;

import com.adjust.api.domain.AdjustTokens;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdjustTokens entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjustTokensRepository extends JpaRepository<AdjustTokens, Long> {
}
