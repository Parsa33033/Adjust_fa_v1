package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.ChatMessage;
import com.adjust.api.repository.ChatMessageRepository;
import com.adjust.api.service.ChatMessageService;
import com.adjust.api.service.dto.ChatMessageDTO;
import com.adjust.api.service.mapper.ChatMessageMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ChatMessageResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ChatMessageResourceIT {

    private static final Long DEFAULT_CLIENT_ID = 1L;
    private static final Long UPDATED_CLIENT_ID = 2L;

    private static final String DEFAULT_CLIENT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_USERNAME = "BBBBBBBBBB";

    private static final Long DEFAULT_SPECIALIST_ID = 1L;
    private static final Long UPDATED_SPECIALIST_ID = 2L;

    private static final String DEFAULT_SPECIALIST_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_SPECIALIST_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final byte[] DEFAULT_VOICE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_VOICE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_VOICE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_VOICE_CONTENT_TYPE = "image/png";

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChatMessageMockMvc;

    private ChatMessage chatMessage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChatMessage createEntity(EntityManager em) {
        ChatMessage chatMessage = new ChatMessage()
            .clientId(DEFAULT_CLIENT_ID)
            .clientUsername(DEFAULT_CLIENT_USERNAME)
            .specialistId(DEFAULT_SPECIALIST_ID)
            .specialistUsername(DEFAULT_SPECIALIST_USERNAME)
            .text(DEFAULT_TEXT)
            .voice(DEFAULT_VOICE)
            .voiceContentType(DEFAULT_VOICE_CONTENT_TYPE);
        return chatMessage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChatMessage createUpdatedEntity(EntityManager em) {
        ChatMessage chatMessage = new ChatMessage()
            .clientId(UPDATED_CLIENT_ID)
            .clientUsername(UPDATED_CLIENT_USERNAME)
            .specialistId(UPDATED_SPECIALIST_ID)
            .specialistUsername(UPDATED_SPECIALIST_USERNAME)
            .text(UPDATED_TEXT)
            .voice(UPDATED_VOICE)
            .voiceContentType(UPDATED_VOICE_CONTENT_TYPE);
        return chatMessage;
    }

    @BeforeEach
    public void initTest() {
        chatMessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createChatMessage() throws Exception {
        int databaseSizeBeforeCreate = chatMessageRepository.findAll().size();
        // Create the ChatMessage
        ChatMessageDTO chatMessageDTO = chatMessageMapper.toDto(chatMessage);
        restChatMessageMockMvc.perform(post("/api/chat-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chatMessageDTO)))
            .andExpect(status().isCreated());

        // Validate the ChatMessage in the database
        List<ChatMessage> chatMessageList = chatMessageRepository.findAll();
        assertThat(chatMessageList).hasSize(databaseSizeBeforeCreate + 1);
        ChatMessage testChatMessage = chatMessageList.get(chatMessageList.size() - 1);
        assertThat(testChatMessage.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testChatMessage.getClientUsername()).isEqualTo(DEFAULT_CLIENT_USERNAME);
        assertThat(testChatMessage.getSpecialistId()).isEqualTo(DEFAULT_SPECIALIST_ID);
        assertThat(testChatMessage.getSpecialistUsername()).isEqualTo(DEFAULT_SPECIALIST_USERNAME);
        assertThat(testChatMessage.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testChatMessage.getVoice()).isEqualTo(DEFAULT_VOICE);
        assertThat(testChatMessage.getVoiceContentType()).isEqualTo(DEFAULT_VOICE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createChatMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chatMessageRepository.findAll().size();

        // Create the ChatMessage with an existing ID
        chatMessage.setId(1L);
        ChatMessageDTO chatMessageDTO = chatMessageMapper.toDto(chatMessage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChatMessageMockMvc.perform(post("/api/chat-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chatMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChatMessage in the database
        List<ChatMessage> chatMessageList = chatMessageRepository.findAll();
        assertThat(chatMessageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllChatMessages() throws Exception {
        // Initialize the database
        chatMessageRepository.saveAndFlush(chatMessage);

        // Get all the chatMessageList
        restChatMessageMockMvc.perform(get("/api/chat-messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chatMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].clientUsername").value(hasItem(DEFAULT_CLIENT_USERNAME)))
            .andExpect(jsonPath("$.[*].specialistId").value(hasItem(DEFAULT_SPECIALIST_ID.intValue())))
            .andExpect(jsonPath("$.[*].specialistUsername").value(hasItem(DEFAULT_SPECIALIST_USERNAME)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT)))
            .andExpect(jsonPath("$.[*].voiceContentType").value(hasItem(DEFAULT_VOICE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].voice").value(hasItem(Base64Utils.encodeToString(DEFAULT_VOICE))));
    }
    
    @Test
    @Transactional
    public void getChatMessage() throws Exception {
        // Initialize the database
        chatMessageRepository.saveAndFlush(chatMessage);

        // Get the chatMessage
        restChatMessageMockMvc.perform(get("/api/chat-messages/{id}", chatMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chatMessage.getId().intValue()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID.intValue()))
            .andExpect(jsonPath("$.clientUsername").value(DEFAULT_CLIENT_USERNAME))
            .andExpect(jsonPath("$.specialistId").value(DEFAULT_SPECIALIST_ID.intValue()))
            .andExpect(jsonPath("$.specialistUsername").value(DEFAULT_SPECIALIST_USERNAME))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT))
            .andExpect(jsonPath("$.voiceContentType").value(DEFAULT_VOICE_CONTENT_TYPE))
            .andExpect(jsonPath("$.voice").value(Base64Utils.encodeToString(DEFAULT_VOICE)));
    }
    @Test
    @Transactional
    public void getNonExistingChatMessage() throws Exception {
        // Get the chatMessage
        restChatMessageMockMvc.perform(get("/api/chat-messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChatMessage() throws Exception {
        // Initialize the database
        chatMessageRepository.saveAndFlush(chatMessage);

        int databaseSizeBeforeUpdate = chatMessageRepository.findAll().size();

        // Update the chatMessage
        ChatMessage updatedChatMessage = chatMessageRepository.findById(chatMessage.getId()).get();
        // Disconnect from session so that the updates on updatedChatMessage are not directly saved in db
        em.detach(updatedChatMessage);
        updatedChatMessage
            .clientId(UPDATED_CLIENT_ID)
            .clientUsername(UPDATED_CLIENT_USERNAME)
            .specialistId(UPDATED_SPECIALIST_ID)
            .specialistUsername(UPDATED_SPECIALIST_USERNAME)
            .text(UPDATED_TEXT)
            .voice(UPDATED_VOICE)
            .voiceContentType(UPDATED_VOICE_CONTENT_TYPE);
        ChatMessageDTO chatMessageDTO = chatMessageMapper.toDto(updatedChatMessage);

        restChatMessageMockMvc.perform(put("/api/chat-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chatMessageDTO)))
            .andExpect(status().isOk());

        // Validate the ChatMessage in the database
        List<ChatMessage> chatMessageList = chatMessageRepository.findAll();
        assertThat(chatMessageList).hasSize(databaseSizeBeforeUpdate);
        ChatMessage testChatMessage = chatMessageList.get(chatMessageList.size() - 1);
        assertThat(testChatMessage.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testChatMessage.getClientUsername()).isEqualTo(UPDATED_CLIENT_USERNAME);
        assertThat(testChatMessage.getSpecialistId()).isEqualTo(UPDATED_SPECIALIST_ID);
        assertThat(testChatMessage.getSpecialistUsername()).isEqualTo(UPDATED_SPECIALIST_USERNAME);
        assertThat(testChatMessage.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testChatMessage.getVoice()).isEqualTo(UPDATED_VOICE);
        assertThat(testChatMessage.getVoiceContentType()).isEqualTo(UPDATED_VOICE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingChatMessage() throws Exception {
        int databaseSizeBeforeUpdate = chatMessageRepository.findAll().size();

        // Create the ChatMessage
        ChatMessageDTO chatMessageDTO = chatMessageMapper.toDto(chatMessage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChatMessageMockMvc.perform(put("/api/chat-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chatMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChatMessage in the database
        List<ChatMessage> chatMessageList = chatMessageRepository.findAll();
        assertThat(chatMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChatMessage() throws Exception {
        // Initialize the database
        chatMessageRepository.saveAndFlush(chatMessage);

        int databaseSizeBeforeDelete = chatMessageRepository.findAll().size();

        // Delete the chatMessage
        restChatMessageMockMvc.perform(delete("/api/chat-messages/{id}", chatMessage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChatMessage> chatMessageList = chatMessageRepository.findAll();
        assertThat(chatMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
