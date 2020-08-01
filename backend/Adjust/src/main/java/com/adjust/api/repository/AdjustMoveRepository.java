package com.adjust.api.repository;

import com.adjust.api.domain.AdjustMove;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdjustMove entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjustMoveRepository extends JpaRepository<AdjustMove, Long> {
}
