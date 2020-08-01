package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.TutorialDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tutorial} and its DTO {@link TutorialDTO}.
 */
@Mapper(componentModel = "spring", uses = {TutorialVideoMapper.class, AdjustClientMapper.class})
public interface TutorialMapper extends EntityMapper<TutorialDTO, Tutorial> {

    @Mapping(source = "video.id", target = "videoId")
    @Mapping(source = "client.id", target = "clientId")
    TutorialDTO toDto(Tutorial tutorial);

    @Mapping(source = "videoId", target = "video")
    @Mapping(source = "clientId", target = "client")
    Tutorial toEntity(TutorialDTO tutorialDTO);

    default Tutorial fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tutorial tutorial = new Tutorial();
        tutorial.setId(id);
        return tutorial;
    }
}
