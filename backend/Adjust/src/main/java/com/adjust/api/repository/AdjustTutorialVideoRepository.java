package com.adjust.api.repository;

import com.adjust.api.domain.AdjustTutorialVideo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdjustTutorialVideo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjustTutorialVideoRepository extends JpaRepository<AdjustTutorialVideo, Long> {
}
