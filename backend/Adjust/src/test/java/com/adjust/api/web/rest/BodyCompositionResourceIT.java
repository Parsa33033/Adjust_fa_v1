package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.BodyComposition;
import com.adjust.api.repository.BodyCompositionRepository;
import com.adjust.api.service.BodyCompositionService;
import com.adjust.api.service.dto.BodyCompositionDTO;
import com.adjust.api.service.mapper.BodyCompositionMapper;

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

/**
 * Integration tests for the {@link BodyCompositionResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BodyCompositionResourceIT {

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_HEIGHT = 1D;
    private static final Double UPDATED_HEIGHT = 2D;

    private static final Double DEFAULT_WEIGHT = 1D;
    private static final Double UPDATED_WEIGHT = 2D;

    private static final Double DEFAULT_BMI = 1D;
    private static final Double UPDATED_BMI = 2D;

    private static final byte[] DEFAULT_BODY_COMPOSITION_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BODY_COMPOSITION_FILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BODY_COMPOSITION_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BODY_COMPOSITION_FILE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BLOOD_TEST_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BLOOD_TEST_FILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BLOOD_TEST_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BLOOD_TEST_FILE_CONTENT_TYPE = "image/png";

    @Autowired
    private BodyCompositionRepository bodyCompositionRepository;

    @Autowired
    private BodyCompositionMapper bodyCompositionMapper;

    @Autowired
    private BodyCompositionService bodyCompositionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBodyCompositionMockMvc;

    private BodyComposition bodyComposition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BodyComposition createEntity(EntityManager em) {
        BodyComposition bodyComposition = new BodyComposition()
            .createdAt(DEFAULT_CREATED_AT)
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .bmi(DEFAULT_BMI)
            .bodyCompositionFile(DEFAULT_BODY_COMPOSITION_FILE)
            .bodyCompositionFileContentType(DEFAULT_BODY_COMPOSITION_FILE_CONTENT_TYPE)
            .bloodTestFile(DEFAULT_BLOOD_TEST_FILE)
            .bloodTestFileContentType(DEFAULT_BLOOD_TEST_FILE_CONTENT_TYPE);
        return bodyComposition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BodyComposition createUpdatedEntity(EntityManager em) {
        BodyComposition bodyComposition = new BodyComposition()
            .createdAt(UPDATED_CREATED_AT)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .bmi(UPDATED_BMI)
            .bodyCompositionFile(UPDATED_BODY_COMPOSITION_FILE)
            .bodyCompositionFileContentType(UPDATED_BODY_COMPOSITION_FILE_CONTENT_TYPE)
            .bloodTestFile(UPDATED_BLOOD_TEST_FILE)
            .bloodTestFileContentType(UPDATED_BLOOD_TEST_FILE_CONTENT_TYPE);
        return bodyComposition;
    }

    @BeforeEach
    public void initTest() {
        bodyComposition = createEntity(em);
    }

    @Test
    @Transactional
    public void createBodyComposition() throws Exception {
        int databaseSizeBeforeCreate = bodyCompositionRepository.findAll().size();
        // Create the BodyComposition
        BodyCompositionDTO bodyCompositionDTO = bodyCompositionMapper.toDto(bodyComposition);
        restBodyCompositionMockMvc.perform(post("/api/body-compositions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bodyCompositionDTO)))
            .andExpect(status().isCreated());

        // Validate the BodyComposition in the database
        List<BodyComposition> bodyCompositionList = bodyCompositionRepository.findAll();
        assertThat(bodyCompositionList).hasSize(databaseSizeBeforeCreate + 1);
        BodyComposition testBodyComposition = bodyCompositionList.get(bodyCompositionList.size() - 1);
        assertThat(testBodyComposition.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBodyComposition.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testBodyComposition.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testBodyComposition.getBmi()).isEqualTo(DEFAULT_BMI);
        assertThat(testBodyComposition.getBodyCompositionFile()).isEqualTo(DEFAULT_BODY_COMPOSITION_FILE);
        assertThat(testBodyComposition.getBodyCompositionFileContentType()).isEqualTo(DEFAULT_BODY_COMPOSITION_FILE_CONTENT_TYPE);
        assertThat(testBodyComposition.getBloodTestFile()).isEqualTo(DEFAULT_BLOOD_TEST_FILE);
        assertThat(testBodyComposition.getBloodTestFileContentType()).isEqualTo(DEFAULT_BLOOD_TEST_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createBodyCompositionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bodyCompositionRepository.findAll().size();

        // Create the BodyComposition with an existing ID
        bodyComposition.setId(1L);
        BodyCompositionDTO bodyCompositionDTO = bodyCompositionMapper.toDto(bodyComposition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBodyCompositionMockMvc.perform(post("/api/body-compositions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bodyCompositionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BodyComposition in the database
        List<BodyComposition> bodyCompositionList = bodyCompositionRepository.findAll();
        assertThat(bodyCompositionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBodyCompositions() throws Exception {
        // Initialize the database
        bodyCompositionRepository.saveAndFlush(bodyComposition);

        // Get all the bodyCompositionList
        restBodyCompositionMockMvc.perform(get("/api/body-compositions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bodyComposition.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].bmi").value(hasItem(DEFAULT_BMI.doubleValue())))
            .andExpect(jsonPath("$.[*].bodyCompositionFileContentType").value(hasItem(DEFAULT_BODY_COMPOSITION_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].bodyCompositionFile").value(hasItem(Base64Utils.encodeToString(DEFAULT_BODY_COMPOSITION_FILE))))
            .andExpect(jsonPath("$.[*].bloodTestFileContentType").value(hasItem(DEFAULT_BLOOD_TEST_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].bloodTestFile").value(hasItem(Base64Utils.encodeToString(DEFAULT_BLOOD_TEST_FILE))));
    }
    
    @Test
    @Transactional
    public void getBodyComposition() throws Exception {
        // Initialize the database
        bodyCompositionRepository.saveAndFlush(bodyComposition);

        // Get the bodyComposition
        restBodyCompositionMockMvc.perform(get("/api/body-compositions/{id}", bodyComposition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bodyComposition.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.bmi").value(DEFAULT_BMI.doubleValue()))
            .andExpect(jsonPath("$.bodyCompositionFileContentType").value(DEFAULT_BODY_COMPOSITION_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.bodyCompositionFile").value(Base64Utils.encodeToString(DEFAULT_BODY_COMPOSITION_FILE)))
            .andExpect(jsonPath("$.bloodTestFileContentType").value(DEFAULT_BLOOD_TEST_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.bloodTestFile").value(Base64Utils.encodeToString(DEFAULT_BLOOD_TEST_FILE)));
    }
    @Test
    @Transactional
    public void getNonExistingBodyComposition() throws Exception {
        // Get the bodyComposition
        restBodyCompositionMockMvc.perform(get("/api/body-compositions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBodyComposition() throws Exception {
        // Initialize the database
        bodyCompositionRepository.saveAndFlush(bodyComposition);

        int databaseSizeBeforeUpdate = bodyCompositionRepository.findAll().size();

        // Update the bodyComposition
        BodyComposition updatedBodyComposition = bodyCompositionRepository.findById(bodyComposition.getId()).get();
        // Disconnect from session so that the updates on updatedBodyComposition are not directly saved in db
        em.detach(updatedBodyComposition);
        updatedBodyComposition
            .createdAt(UPDATED_CREATED_AT)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .bmi(UPDATED_BMI)
            .bodyCompositionFile(UPDATED_BODY_COMPOSITION_FILE)
            .bodyCompositionFileContentType(UPDATED_BODY_COMPOSITION_FILE_CONTENT_TYPE)
            .bloodTestFile(UPDATED_BLOOD_TEST_FILE)
            .bloodTestFileContentType(UPDATED_BLOOD_TEST_FILE_CONTENT_TYPE);
        BodyCompositionDTO bodyCompositionDTO = bodyCompositionMapper.toDto(updatedBodyComposition);

        restBodyCompositionMockMvc.perform(put("/api/body-compositions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bodyCompositionDTO)))
            .andExpect(status().isOk());

        // Validate the BodyComposition in the database
        List<BodyComposition> bodyCompositionList = bodyCompositionRepository.findAll();
        assertThat(bodyCompositionList).hasSize(databaseSizeBeforeUpdate);
        BodyComposition testBodyComposition = bodyCompositionList.get(bodyCompositionList.size() - 1);
        assertThat(testBodyComposition.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBodyComposition.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testBodyComposition.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testBodyComposition.getBmi()).isEqualTo(UPDATED_BMI);
        assertThat(testBodyComposition.getBodyCompositionFile()).isEqualTo(UPDATED_BODY_COMPOSITION_FILE);
        assertThat(testBodyComposition.getBodyCompositionFileContentType()).isEqualTo(UPDATED_BODY_COMPOSITION_FILE_CONTENT_TYPE);
        assertThat(testBodyComposition.getBloodTestFile()).isEqualTo(UPDATED_BLOOD_TEST_FILE);
        assertThat(testBodyComposition.getBloodTestFileContentType()).isEqualTo(UPDATED_BLOOD_TEST_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingBodyComposition() throws Exception {
        int databaseSizeBeforeUpdate = bodyCompositionRepository.findAll().size();

        // Create the BodyComposition
        BodyCompositionDTO bodyCompositionDTO = bodyCompositionMapper.toDto(bodyComposition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBodyCompositionMockMvc.perform(put("/api/body-compositions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bodyCompositionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BodyComposition in the database
        List<BodyComposition> bodyCompositionList = bodyCompositionRepository.findAll();
        assertThat(bodyCompositionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBodyComposition() throws Exception {
        // Initialize the database
        bodyCompositionRepository.saveAndFlush(bodyComposition);

        int databaseSizeBeforeDelete = bodyCompositionRepository.findAll().size();

        // Delete the bodyComposition
        restBodyCompositionMockMvc.perform(delete("/api/body-compositions/{id}", bodyComposition.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BodyComposition> bodyCompositionList = bodyCompositionRepository.findAll();
        assertThat(bodyCompositionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
