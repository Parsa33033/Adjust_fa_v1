package com.adjust.api.repository;

import com.adjust.api.domain.Move;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Move entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MoveRepository extends JpaRepository<Move, Long> {
}
