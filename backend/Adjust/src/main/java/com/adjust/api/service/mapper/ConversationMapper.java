package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.ConversationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Conversation} and its DTO {@link ConversationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConversationMapper extends EntityMapper<ConversationDTO, Conversation> {


    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "removeMessages", ignore = true)
    Conversation toEntity(ConversationDTO conversationDTO);

    default Conversation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Conversation conversation = new Conversation();
        conversation.setId(id);
        return conversation;
    }
}
