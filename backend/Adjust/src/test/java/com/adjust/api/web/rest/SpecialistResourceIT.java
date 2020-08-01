package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.Specialist;
import com.adjust.api.repository.SpecialistRepository;
import com.adjust.api.service.SpecialistService;
import com.adjust.api.service.dto.SpecialistDTO;
import com.adjust.api.service.mapper.SpecialistMapper;

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
 * Integration tests for the {@link SpecialistResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SpecialistResourceIT {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH = LocalDate.now(ZoneId.systemDefault());

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final String DEFAULT_DEGREE = "AAAAAAAAAA";
    private static final String UPDATED_DEGREE = "BBBBBBBBBB";

    private static final String DEFAULT_FIELD = "AAAAAAAAAA";
    private static final String UPDATED_FIELD = "BBBBBBBBBB";

    private static final String DEFAULT_RESUME = "AAAAAAAAAA";
    private static final String UPDATED_RESUME = "BBBBBBBBBB";

    private static final Double DEFAULT_STARS = 1D;
    private static final Double UPDATED_STARS = 2D;

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_BUSY = false;
    private static final Boolean UPDATED_BUSY = true;

    @Autowired
    private SpecialistRepository specialistRepository;

    @Autowired
    private SpecialistMapper specialistMapper;

    @Autowired
    private SpecialistService specialistService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSpecialistMockMvc;

    private Specialist specialist;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specialist createEntity(EntityManager em) {
        Specialist specialist = new Specialist()
            .username(DEFAULT_USERNAME)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .birth(DEFAULT_BIRTH)
            .gender(DEFAULT_GENDER)
            .degree(DEFAULT_DEGREE)
            .field(DEFAULT_FIELD)
            .resume(DEFAULT_RESUME)
            .stars(DEFAULT_STARS)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .busy(DEFAULT_BUSY);
        return specialist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specialist createUpdatedEntity(EntityManager em) {
        Specialist specialist = new Specialist()
            .username(UPDATED_USERNAME)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .birth(UPDATED_BIRTH)
            .gender(UPDATED_GENDER)
            .degree(UPDATED_DEGREE)
            .field(UPDATED_FIELD)
            .resume(UPDATED_RESUME)
            .stars(UPDATED_STARS)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .busy(UPDATED_BUSY);
        return specialist;
    }

    @BeforeEach
    public void initTest() {
        specialist = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpecialist() throws Exception {
        int databaseSizeBeforeCreate = specialistRepository.findAll().size();
        // Create the Specialist
        SpecialistDTO specialistDTO = specialistMapper.toDto(specialist);
        restSpecialistMockMvc.perform(post("/api/specialists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specialistDTO)))
            .andExpect(status().isCreated());

        // Validate the Specialist in the database
        List<Specialist> specialistList = specialistRepository.findAll();
        assertThat(specialistList).hasSize(databaseSizeBeforeCreate + 1);
        Specialist testSpecialist = specialistList.get(specialistList.size() - 1);
        assertThat(testSpecialist.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testSpecialist.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testSpecialist.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testSpecialist.getBirth()).isEqualTo(DEFAULT_BIRTH);
        assertThat(testSpecialist.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testSpecialist.getDegree()).isEqualTo(DEFAULT_DEGREE);
        assertThat(testSpecialist.getField()).isEqualTo(DEFAULT_FIELD);
        assertThat(testSpecialist.getResume()).isEqualTo(DEFAULT_RESUME);
        assertThat(testSpecialist.getStars()).isEqualTo(DEFAULT_STARS);
        assertThat(testSpecialist.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testSpecialist.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testSpecialist.isBusy()).isEqualTo(DEFAULT_BUSY);
    }

    @Test
    @Transactional
    public void createSpecialistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = specialistRepository.findAll().size();

        // Create the Specialist with an existing ID
        specialist.setId(1L);
        SpecialistDTO specialistDTO = specialistMapper.toDto(specialist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpecialistMockMvc.perform(post("/api/specialists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specialistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Specialist in the database
        List<Specialist> specialistList = specialistRepository.findAll();
        assertThat(specialistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSpecialists() throws Exception {
        // Initialize the database
        specialistRepository.saveAndFlush(specialist);

        // Get all the specialistList
        restSpecialistMockMvc.perform(get("/api/specialists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(specialist.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].birth").value(hasItem(DEFAULT_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].degree").value(hasItem(DEFAULT_DEGREE)))
            .andExpect(jsonPath("$.[*].field").value(hasItem(DEFAULT_FIELD)))
            .andExpect(jsonPath("$.[*].resume").value(hasItem(DEFAULT_RESUME)))
            .andExpect(jsonPath("$.[*].stars").value(hasItem(DEFAULT_STARS.doubleValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].busy").value(hasItem(DEFAULT_BUSY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getSpecialist() throws Exception {
        // Initialize the database
        specialistRepository.saveAndFlush(specialist);

        // Get the specialist
        restSpecialistMockMvc.perform(get("/api/specialists/{id}", specialist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(specialist.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.birth").value(DEFAULT_BIRTH.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.degree").value(DEFAULT_DEGREE))
            .andExpect(jsonPath("$.field").value(DEFAULT_FIELD))
            .andExpect(jsonPath("$.resume").value(DEFAULT_RESUME))
            .andExpect(jsonPath("$.stars").value(DEFAULT_STARS.doubleValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.busy").value(DEFAULT_BUSY.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingSpecialist() throws Exception {
        // Get the specialist
        restSpecialistMockMvc.perform(get("/api/specialists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpecialist() throws Exception {
        // Initialize the database
        specialistRepository.saveAndFlush(specialist);

        int databaseSizeBeforeUpdate = specialistRepository.findAll().size();

        // Update the specialist
        Specialist updatedSpecialist = specialistRepository.findById(specialist.getId()).get();
        // Disconnect from session so that the updates on updatedSpecialist are not directly saved in db
        em.detach(updatedSpecialist);
        updatedSpecialist
            .username(UPDATED_USERNAME)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .birth(UPDATED_BIRTH)
            .gender(UPDATED_GENDER)
            .degree(UPDATED_DEGREE)
            .field(UPDATED_FIELD)
            .resume(UPDATED_RESUME)
            .stars(UPDATED_STARS)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .busy(UPDATED_BUSY);
        SpecialistDTO specialistDTO = specialistMapper.toDto(updatedSpecialist);

        restSpecialistMockMvc.perform(put("/api/specialists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specialistDTO)))
            .andExpect(status().isOk());

        // Validate the Specialist in the database
        List<Specialist> specialistList = specialistRepository.findAll();
        assertThat(specialistList).hasSize(databaseSizeBeforeUpdate);
        Specialist testSpecialist = specialistList.get(specialistList.size() - 1);
        assertThat(testSpecialist.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testSpecialist.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testSpecialist.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testSpecialist.getBirth()).isEqualTo(UPDATED_BIRTH);
        assertThat(testSpecialist.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testSpecialist.getDegree()).isEqualTo(UPDATED_DEGREE);
        assertThat(testSpecialist.getField()).isEqualTo(UPDATED_FIELD);
        assertThat(testSpecialist.getResume()).isEqualTo(UPDATED_RESUME);
        assertThat(testSpecialist.getStars()).isEqualTo(UPDATED_STARS);
        assertThat(testSpecialist.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testSpecialist.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testSpecialist.isBusy()).isEqualTo(UPDATED_BUSY);
    }

    @Test
    @Transactional
    public void updateNonExistingSpecialist() throws Exception {
        int databaseSizeBeforeUpdate = specialistRepository.findAll().size();

        // Create the Specialist
        SpecialistDTO specialistDTO = specialistMapper.toDto(specialist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpecialistMockMvc.perform(put("/api/specialists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specialistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Specialist in the database
        List<Specialist> specialistList = specialistRepository.findAll();
        assertThat(specialistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpecialist() throws Exception {
        // Initialize the database
        specialistRepository.saveAndFlush(specialist);

        int databaseSizeBeforeDelete = specialistRepository.findAll().size();

        // Delete the specialist
        restSpecialistMockMvc.perform(delete("/api/specialists/{id}", specialist.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Specialist> specialistList = specialistRepository.findAll();
        assertThat(specialistList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
