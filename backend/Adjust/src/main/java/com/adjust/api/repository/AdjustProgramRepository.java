package com.adjust.api.repository;

import com.adjust.api.domain.AdjustClient;
import com.adjust.api.domain.AdjustProgram;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the AdjustProgram entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjustProgramRepository extends JpaRepository<AdjustProgram, Long> {
    List<AdjustProgram> findAllByClient(AdjustClient client);
}
