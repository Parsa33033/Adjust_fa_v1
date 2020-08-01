package com.adjust.api.repository;

import com.adjust.api.domain.AdjustClient;
import com.adjust.api.domain.Tutorial;

import com.adjust.api.domain.TutorialVideo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Tutorial entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findTutorialsByClient(AdjustClient client);
    Optional<Tutorial> findTutorialByVideo(TutorialVideo video);
    Optional<Tutorial> findTutorialByClientAndAdjustTutorialId(AdjustClient client, Long adjustTutorialId);
}
