package com.adjust.api.repository;

import com.adjust.api.domain.AdjustProgram;
import com.adjust.api.domain.BodyComposition;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the BodyComposition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BodyCompositionRepository extends JpaRepository<BodyComposition, Long> {
    List<BodyComposition> findAllByProgram(AdjustProgram program);
}
