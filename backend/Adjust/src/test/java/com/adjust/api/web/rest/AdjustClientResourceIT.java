package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.AdjustClient;
import com.adjust.api.repository.AdjustClientRepository;
import com.adjust.api.service.AdjustClientService;
import com.adjust.api.service.dto.AdjustClientDTO;
import com.adjust.api.service.mapper.AdjustClientMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.adjust.api.domain.enumeration.Gender;
/**
 * Integration tests for the {@link AdjustClientResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjustClientResourceIT {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final Double DEFAULT_TOKEN = 1D;
    private static final Double UPDATED_TOKEN = 2D;

    private static final Double DEFAULT_SCORE = 1D;
    private static final Double UPDATED_SCORE = 2D;

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private AdjustClientRepository adjustClientRepository;

    @Autowired
    private AdjustClientMapper adjustClientMapper;

    @Autowired
    private AdjustClientService adjustClientService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjustClientMockMvc;

    private AdjustClient adjustClient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustClient createEntity(EntityManager em) {
        AdjustClient adjustClient = new AdjustClient()
            .username(DEFAULT_USERNAME)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .birthDate(DEFAULT_BIRTH_DATE)
            .age(DEFAULT_AGE)
            .gender(DEFAULT_GENDER)
            .token(DEFAULT_TOKEN)
            .score(DEFAULT_SCORE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return adjustClient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustClient createUpdatedEntity(EntityManager em) {
        AdjustClient adjustClient = new AdjustClient()
            .username(UPDATED_USERNAME)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .token(UPDATED_TOKEN)
            .score(UPDATED_SCORE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        return adjustClient;
    }

    @BeforeEach
    public void initTest() {
        adjustClient = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjustClient() throws Exception {
        int databaseSizeBeforeCreate = adjustClientRepository.findAll().size();
        // Create the AdjustClient
        AdjustClientDTO adjustClientDTO = adjustClientMapper.toDto(adjustClient);
        restAdjustClientMockMvc.perform(post("/api/adjust-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustClientDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjustClient in the database
        List<AdjustClient> adjustClientList = adjustClientRepository.findAll();
        assertThat(adjustClientList).hasSize(databaseSizeBeforeCreate + 1);
        AdjustClient testAdjustClient = adjustClientList.get(adjustClientList.size() - 1);
        assertThat(testAdjustClient.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testAdjustClient.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testAdjustClient.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testAdjustClient.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testAdjustClient.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testAdjustClient.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testAdjustClient.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testAdjustClient.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testAdjustClient.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testAdjustClient.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createAdjustClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjustClientRepository.findAll().size();

        // Create the AdjustClient with an existing ID
        adjustClient.setId(1L);
        AdjustClientDTO adjustClientDTO = adjustClientMapper.toDto(adjustClient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjustClientMockMvc.perform(post("/api/adjust-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustClientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustClient in the database
        List<AdjustClient> adjustClientList = adjustClientRepository.findAll();
        assertThat(adjustClientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjustClients() throws Exception {
        // Initialize the database
        adjustClientRepository.saveAndFlush(adjustClient);

        // Get all the adjustClientList
        restAdjustClientMockMvc.perform(get("/api/adjust-clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjustClient.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN.doubleValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @Test
    @Transactional
    public void getAdjustClient() throws Exception {
        // Initialize the database
        adjustClientRepository.saveAndFlush(adjustClient);

        // Get the adjustClient
        restAdjustClientMockMvc.perform(get("/api/adjust-clients/{id}", adjustClient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjustClient.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN.doubleValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.doubleValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }
    @Test
    @Transactional
    public void getNonExistingAdjustClient() throws Exception {
        // Get the adjustClient
        restAdjustClientMockMvc.perform(get("/api/adjust-clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjustClient() throws Exception {
        // Initialize the database
        adjustClientRepository.saveAndFlush(adjustClient);

        int databaseSizeBeforeUpdate = adjustClientRepository.findAll().size();

        // Update the adjustClient
        AdjustClient updatedAdjustClient = adjustClientRepository.findById(adjustClient.getId()).get();
        // Disconnect from session so that the updates on updatedAdjustClient are not directly saved in db
        em.detach(updatedAdjustClient);
        updatedAdjustClient
            .username(UPDATED_USERNAME)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .token(UPDATED_TOKEN)
            .score(UPDATED_SCORE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        AdjustClientDTO adjustClientDTO = adjustClientMapper.toDto(updatedAdjustClient);

        restAdjustClientMockMvc.perform(put("/api/adjust-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustClientDTO)))
            .andExpect(status().isOk());

        // Validate the AdjustClient in the database
        List<AdjustClient> adjustClientList = adjustClientRepository.findAll();
        assertThat(adjustClientList).hasSize(databaseSizeBeforeUpdate);
        AdjustClient testAdjustClient = adjustClientList.get(adjustClientList.size() - 1);
        assertThat(testAdjustClient.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testAdjustClient.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testAdjustClient.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testAdjustClient.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testAdjustClient.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testAdjustClient.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testAdjustClient.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testAdjustClient.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testAdjustClient.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testAdjustClient.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjustClient() throws Exception {
        int databaseSizeBeforeUpdate = adjustClientRepository.findAll().size();

        // Create the AdjustClient
        AdjustClientDTO adjustClientDTO = adjustClientMapper.toDto(adjustClient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjustClientMockMvc.perform(put("/api/adjust-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustClientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustClient in the database
        List<AdjustClient> adjustClientList = adjustClientRepository.findAll();
        assertThat(adjustClientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjustClient() throws Exception {
        // Initialize the database
        adjustClientRepository.saveAndFlush(adjustClient);

        int databaseSizeBeforeDelete = adjustClientRepository.findAll().size();

        // Delete the adjustClient
        restAdjustClientMockMvc.perform(delete("/api/adjust-clients/{id}", adjustClient.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjustClient> adjustClientList = adjustClientRepository.findAll();
        assertThat(adjustClientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
