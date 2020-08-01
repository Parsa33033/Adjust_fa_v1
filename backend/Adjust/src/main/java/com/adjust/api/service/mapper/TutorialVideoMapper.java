package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.TutorialVideoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TutorialVideo} and its DTO {@link TutorialVideoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TutorialVideoMapper extends EntityMapper<TutorialVideoDTO, TutorialVideo> {


    @Mapping(target = "tutorial", ignore = true)
    TutorialVideo toEntity(TutorialVideoDTO tutorialVideoDTO);

    default TutorialVideo fromId(Long id) {
        if (id == null) {
            return null;
        }
        TutorialVideo tutorialVideo = new TutorialVideo();
        tutorialVideo.setId(id);
        return tutorialVideo;
    }
}
