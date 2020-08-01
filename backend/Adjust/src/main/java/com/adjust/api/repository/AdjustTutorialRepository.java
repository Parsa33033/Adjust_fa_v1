package com.adjust.api.repository;

import com.adjust.api.domain.AdjustTutorial;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdjustTutorial entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjustTutorialRepository extends JpaRepository<AdjustTutorial, Long> {
}
