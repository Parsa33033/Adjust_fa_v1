package com.adjust.api.repository;

import com.adjust.api.domain.AdjustClient;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the AdjustClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjustClientRepository extends JpaRepository<AdjustClient, Long> {
    Optional<AdjustClient> findAdjustClientByUsername(String username);
}
