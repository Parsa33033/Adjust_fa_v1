package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.AdjustClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdjustClient} and its DTO {@link AdjustClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdjustClientMapper extends EntityMapper<AdjustClientDTO, AdjustClient> {


    @Mapping(target = "tutorials", ignore = true)
    @Mapping(target = "removeTutorials", ignore = true)
    @Mapping(target = "programs", ignore = true)
    @Mapping(target = "removePrograms", ignore = true)
    @Mapping(target = "conversation", ignore = true)
    AdjustClient toEntity(AdjustClientDTO adjustClientDTO);

    default AdjustClient fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdjustClient adjustClient = new AdjustClient();
        adjustClient.setId(id);
        return adjustClient;
    }
}
