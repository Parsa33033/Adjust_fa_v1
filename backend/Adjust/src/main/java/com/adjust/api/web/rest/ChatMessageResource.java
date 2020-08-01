package com.adjust.api.web.rest;

import com.adjust.api.service.ChatMessageService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.ChatMessageDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.adjust.api.domain.ChatMessage}.
 */
@RestController
@RequestMapping("/api")
public class ChatMessageResource {

    private final Logger log = LoggerFactory.getLogger(ChatMessageResource.class);

    private static final String ENTITY_NAME = "chatMessage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChatMessageService chatMessageService;

    public ChatMessageResource(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    /**
     * {@code POST  /chat-messages} : Create a new chatMessage.
     *
     * @param chatMessageDTO the chatMessageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chatMessageDTO, or with status {@code 400 (Bad Request)} if the chatMessage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chat-messages")
    public ResponseEntity<ChatMessageDTO> createChatMessage(@RequestBody ChatMessageDTO chatMessageDTO) throws URISyntaxException {
        log.debug("REST request to save ChatMessage : {}", chatMessageDTO);
        if (chatMessageDTO.getId() != null) {
            throw new BadRequestAlertException("A new chatMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChatMessageDTO result = chatMessageService.save(chatMessageDTO);
        return ResponseEntity.created(new URI("/api/chat-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chat-messages} : Updates an existing chatMessage.
     *
     * @param chatMessageDTO the chatMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chatMessageDTO,
     * or with status {@code 400 (Bad Request)} if the chatMessageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chatMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chat-messages")
    public ResponseEntity<ChatMessageDTO> updateChatMessage(@RequestBody ChatMessageDTO chatMessageDTO) throws URISyntaxException {
        log.debug("REST request to update ChatMessage : {}", chatMessageDTO);
        if (chatMessageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChatMessageDTO result = chatMessageService.save(chatMessageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chatMessageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /chat-messages} : get all the chatMessages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chatMessages in body.
     */
    @GetMapping("/chat-messages")
    public List<ChatMessageDTO> getAllChatMessages() {
        log.debug("REST request to get all ChatMessages");
        return chatMessageService.findAll();
    }

    /**
     * {@code GET  /chat-messages/:id} : get the "id" chatMessage.
     *
     * @param id the id of the chatMessageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chatMessageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chat-messages/{id}")
    public ResponseEntity<ChatMessageDTO> getChatMessage(@PathVariable Long id) {
        log.debug("REST request to get ChatMessage : {}", id);
        Optional<ChatMessageDTO> chatMessageDTO = chatMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chatMessageDTO);
    }

    /**
     * {@code DELETE  /chat-messages/:id} : delete the "id" chatMessage.
     *
     * @param id the id of the chatMessageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chat-messages/{id}")
    public ResponseEntity<Void> deleteChatMessage(@PathVariable Long id) {
        log.debug("REST request to delete ChatMessage : {}", id);
        chatMessageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
