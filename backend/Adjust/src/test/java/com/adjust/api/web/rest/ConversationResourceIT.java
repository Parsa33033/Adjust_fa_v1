package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.Conversation;
import com.adjust.api.repository.ConversationRepository;
import com.adjust.api.service.ConversationService;
import com.adjust.api.service.dto.ConversationDTO;
import com.adjust.api.service.mapper.ConversationMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ConversationResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ConversationResourceIT {

    private static final Long DEFAULT_CLIENT_ID = 1L;
    private static final Long UPDATED_CLIENT_ID = 2L;

    private static final Long DEFAULT_SPECIALIST_ID = 1L;
    private static final Long UPDATED_SPECIALIST_ID = 2L;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ConversationMapper conversationMapper;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restConversationMockMvc;

    private Conversation conversation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Conversation createEntity(EntityManager em) {
        Conversation conversation = new Conversation()
            .clientId(DEFAULT_CLIENT_ID)
            .specialistId(DEFAULT_SPECIALIST_ID);
        return conversation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Conversation createUpdatedEntity(EntityManager em) {
        Conversation conversation = new Conversation()
            .clientId(UPDATED_CLIENT_ID)
            .specialistId(UPDATED_SPECIALIST_ID);
        return conversation;
    }

    @BeforeEach
    public void initTest() {
        conversation = createEntity(em);
    }

    @Test
    @Transactional
    public void createConversation() throws Exception {
        int databaseSizeBeforeCreate = conversationRepository.findAll().size();
        // Create the Conversation
        ConversationDTO conversationDTO = conversationMapper.toDto(conversation);
        restConversationMockMvc.perform(post("/api/conversations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conversationDTO)))
            .andExpect(status().isCreated());

        // Validate the Conversation in the database
        List<Conversation> conversationList = conversationRepository.findAll();
        assertThat(conversationList).hasSize(databaseSizeBeforeCreate + 1);
        Conversation testConversation = conversationList.get(conversationList.size() - 1);
        assertThat(testConversation.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testConversation.getSpecialistId()).isEqualTo(DEFAULT_SPECIALIST_ID);
    }

    @Test
    @Transactional
    public void createConversationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conversationRepository.findAll().size();

        // Create the Conversation with an existing ID
        conversation.setId(1L);
        ConversationDTO conversationDTO = conversationMapper.toDto(conversation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConversationMockMvc.perform(post("/api/conversations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conversationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Conversation in the database
        List<Conversation> conversationList = conversationRepository.findAll();
        assertThat(conversationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllConversations() throws Exception {
        // Initialize the database
        conversationRepository.saveAndFlush(conversation);

        // Get all the conversationList
        restConversationMockMvc.perform(get("/api/conversations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conversation.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].specialistId").value(hasItem(DEFAULT_SPECIALIST_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getConversation() throws Exception {
        // Initialize the database
        conversationRepository.saveAndFlush(conversation);

        // Get the conversation
        restConversationMockMvc.perform(get("/api/conversations/{id}", conversation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(conversation.getId().intValue()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID.intValue()))
            .andExpect(jsonPath("$.specialistId").value(DEFAULT_SPECIALIST_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingConversation() throws Exception {
        // Get the conversation
        restConversationMockMvc.perform(get("/api/conversations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConversation() throws Exception {
        // Initialize the database
        conversationRepository.saveAndFlush(conversation);

        int databaseSizeBeforeUpdate = conversationRepository.findAll().size();

        // Update the conversation
        Conversation updatedConversation = conversationRepository.findById(conversation.getId()).get();
        // Disconnect from session so that the updates on updatedConversation are not directly saved in db
        em.detach(updatedConversation);
        updatedConversation
            .clientId(UPDATED_CLIENT_ID)
            .specialistId(UPDATED_SPECIALIST_ID);
        ConversationDTO conversationDTO = conversationMapper.toDto(updatedConversation);

        restConversationMockMvc.perform(put("/api/conversations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conversationDTO)))
            .andExpect(status().isOk());

        // Validate the Conversation in the database
        List<Conversation> conversationList = conversationRepository.findAll();
        assertThat(conversationList).hasSize(databaseSizeBeforeUpdate);
        Conversation testConversation = conversationList.get(conversationList.size() - 1);
        assertThat(testConversation.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testConversation.getSpecialistId()).isEqualTo(UPDATED_SPECIALIST_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingConversation() throws Exception {
        int databaseSizeBeforeUpdate = conversationRepository.findAll().size();

        // Create the Conversation
        ConversationDTO conversationDTO = conversationMapper.toDto(conversation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConversationMockMvc.perform(put("/api/conversations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conversationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Conversation in the database
        List<Conversation> conversationList = conversationRepository.findAll();
        assertThat(conversationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConversation() throws Exception {
        // Initialize the database
        conversationRepository.saveAndFlush(conversation);

        int databaseSizeBeforeDelete = conversationRepository.findAll().size();

        // Delete the conversation
        restConversationMockMvc.perform(delete("/api/conversations/{id}", conversation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Conversation> conversationList = conversationRepository.findAll();
        assertThat(conversationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
