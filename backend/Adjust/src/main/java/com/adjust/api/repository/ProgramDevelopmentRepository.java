package com.adjust.api.repository;

import com.adjust.api.domain.ProgramDevelopment;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProgramDevelopment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgramDevelopmentRepository extends JpaRepository<ProgramDevelopment, Long> {
}
