package com.adjust.api.web.websocket;

import com.adjust.api.domain.AdjustClient;
import com.adjust.api.domain.ChatMessage;
import com.adjust.api.domain.Conversation;
import com.adjust.api.domain.Specialist;
import com.adjust.api.repository.AdjustClientRepository;
import com.adjust.api.repository.ChatMessageRepository;
import com.adjust.api.repository.ConversationRepository;
import com.adjust.api.repository.SpecialistRepository;
import com.adjust.api.security.SecurityUtils;
import com.adjust.api.service.ChatMessageService;
import com.adjust.api.service.dto.AdjustClientDTO;
import com.adjust.api.service.dto.ChatMessageDTO;
import com.adjust.api.service.mapper.AdjustClientMapper;
import com.adjust.api.web.rest.ClientAppController;
import com.adjust.api.web.websocket.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageService {
    Logger logger = LoggerFactory.getLogger(MessageService.class);

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    ConversationRepository conversationRepository;

    @Autowired
    ChatMessageService chatMessageService;

    @Autowired
    AdjustClientRepository adjustClientRepository;

    @Autowired
    SpecialistRepository specialistRepository;


    @MessageMapping("/client/chat.message")
    @SendTo("/topic/reply")
    public void sendMessageByClient(@Payload MessageDTO msg) {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        chatMessageDTO.setClientId(msg.getClientId());
        chatMessageDTO.setSpecialistId(msg.getSpecialistId());
        chatMessageDTO.setSender(userLogin);
        chatMessageDTO.setClientUsername(userLogin);
        chatMessageDTO.setSpecialistUsername(msg.getReceiver());
        AdjustClient adjustClient = adjustClientRepository.findAdjustClientByUsername(userLogin).get();
        Specialist specialist = specialistRepository.findByUsername(msg.getReceiver()).get();
        chatMessageDTO.setReceiver(specialist.getUsername());
        Conversation conversation = conversationRepository.findByClientIdAndSpecialistId(adjustClient.getId(), specialist.getId()).get();
        chatMessageDTO.setConversationId(conversation.getId());
        chatMessageDTO.setText(msg.getMessage());
        chatMessageService.save(chatMessageDTO);
        simpMessagingTemplate.convertAndSend("/topic/"+ msg.getReceiver() +"/reply", msg.getMessage());
    }

    @MessageMapping("/specialist/chat.message")
    @SendTo("/topic/reply")
    public void sendMessageBySpecialist(@Payload MessageDTO msg) {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        chatMessageDTO.setClientId(msg.getClientId());
        chatMessageDTO.setSpecialistId(msg.getSpecialistId());
        chatMessageDTO.setSender(userLogin);
        chatMessageDTO.setClientUsername(msg.getReceiver());
        chatMessageDTO.setSpecialistUsername(userLogin);
        AdjustClient adjustClient = adjustClientRepository.findAdjustClientByUsername(msg.getReceiver()).get();
        Specialist specialist = specialistRepository.findByUsername(userLogin).get();
        chatMessageDTO.setReceiver(specialist.getUsername());
        Conversation conversation = conversationRepository.findByClientIdAndSpecialistId(adjustClient.getId(), specialist.getId()).get();
        chatMessageDTO.setConversationId(conversation.getId());
        chatMessageDTO.setText(msg.getMessage());
        chatMessageService.save(chatMessageDTO);
        simpMessagingTemplate.convertAndSend("/topic/"+ msg.getReceiver() +"/reply", msg.getMessage() );
    }
}
