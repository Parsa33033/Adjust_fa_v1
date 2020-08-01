package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.AdjustTutorialVideoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjustTutorialVideo} and its DTO {@link AdjustTutorialVideoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdjustTutorialVideoMapper extends EntityMapper<AdjustTutorialVideoDTO, AdjustTutorialVideo> {


    @Mapping(target = "tutorial", ignore = true)
    AdjustTutorialVideo toEntity(AdjustTutorialVideoDTO adjustTutorialVideoDTO);

    default AdjustTutorialVideo fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjustTutorialVideo adjustTutorialVideo = new AdjustTutorialVideo();
        adjustTutorialVideo.setId(id);
        return adjustTutorialVideo;
    }
}
