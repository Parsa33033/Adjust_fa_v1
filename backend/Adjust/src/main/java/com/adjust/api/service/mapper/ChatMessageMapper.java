package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.ChatMessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChatMessage} and its DTO {@link ChatMessageDTO}.
 */
@Mapper(componentModel = "spring", uses = {ConversationMapper.class})
public interface ChatMessageMapper extends EntityMapper<ChatMessageDTO, ChatMessage> {

    @Mapping(source = "conversation.id", target = "conversationId")
    ChatMessageDTO toDto(ChatMessage chatMessage);

    @Mapping(source = "conversationId", target = "conversation")
    ChatMessage toEntity(ChatMessageDTO chatMessageDTO);

    default ChatMessage fromId(Long id) {
        if (id == null) {
            return null;
        }
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(id);
        return chatMessage;
    }
}
