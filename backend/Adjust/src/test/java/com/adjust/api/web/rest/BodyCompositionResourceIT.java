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

import com.adjust.api.domain.enumeration.Gender;
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

    private static final Double DEFAULT_WRIST = 1D;
    private static final Double UPDATED_WRIST = 2D;

    private static final Double DEFAULT_WAIST = 1D;
    private static final Double UPDATED_WAIST = 2D;

    private static final Double DEFAULT_LBM = 1D;
    private static final Double UPDATED_LBM = 2D;

    private static final Double DEFAULT_MUSCLE_MASS = 1D;
    private static final Double UPDATED_MUSCLE_MASS = 2D;

    private static final Integer DEFAULT_MUSCLE_MASS_PERCENTAGE = 1;
    private static final Integer UPDATED_MUSCLE_MASS_PERCENTAGE = 2;

    private static final Double DEFAULT_FAT_MASS = 1D;
    private static final Double UPDATED_FAT_MASS = 2D;

    private static final Integer DEFAULT_FAT_MASS_PERCENTAGE = 1;
    private static final Integer UPDATED_FAT_MASS_PERCENTAGE = 2;

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final byte[] DEFAULT_BODY_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BODY_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BODY_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BODY_IMAGE_CONTENT_TYPE = "image/png";

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
            .wrist(DEFAULT_WRIST)
            .waist(DEFAULT_WAIST)
            .lbm(DEFAULT_LBM)
            .muscleMass(DEFAULT_MUSCLE_MASS)
            .muscleMassPercentage(DEFAULT_MUSCLE_MASS_PERCENTAGE)
            .fatMass(DEFAULT_FAT_MASS)
            .fatMassPercentage(DEFAULT_FAT_MASS_PERCENTAGE)
            .gender(DEFAULT_GENDER)
            .age(DEFAULT_AGE)
            .bodyImage(DEFAULT_BODY_IMAGE)
            .bodyImageContentType(DEFAULT_BODY_IMAGE_CONTENT_TYPE)
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
            .wrist(UPDATED_WRIST)
            .waist(UPDATED_WAIST)
            .lbm(UPDATED_LBM)
            .muscleMass(UPDATED_MUSCLE_MASS)
            .muscleMassPercentage(UPDATED_MUSCLE_MASS_PERCENTAGE)
            .fatMass(UPDATED_FAT_MASS)
            .fatMassPercentage(UPDATED_FAT_MASS_PERCENTAGE)
            .gender(UPDATED_GENDER)
            .age(UPDATED_AGE)
            .bodyImage(UPDATED_BODY_IMAGE)
            .bodyImageContentType(UPDATED_BODY_IMAGE_CONTENT_TYPE)
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
        assertThat(testBodyComposition.getWrist()).isEqualTo(DEFAULT_WRIST);
        assertThat(testBodyComposition.getWaist()).isEqualTo(DEFAULT_WAIST);
        assertThat(testBodyComposition.getLbm()).isEqualTo(DEFAULT_LBM);
        assertThat(testBodyComposition.getMuscleMass()).isEqualTo(DEFAULT_MUSCLE_MASS);
        assertThat(testBodyComposition.getMuscleMassPercentage()).isEqualTo(DEFAULT_MUSCLE_MASS_PERCENTAGE);
        assertThat(testBodyComposition.getFatMass()).isEqualTo(DEFAULT_FAT_MASS);
        assertThat(testBodyComposition.getFatMassPercentage()).isEqualTo(DEFAULT_FAT_MASS_PERCENTAGE);
        assertThat(testBodyComposition.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testBodyComposition.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testBodyComposition.getBodyImage()).isEqualTo(DEFAULT_BODY_IMAGE);
        assertThat(testBodyComposition.getBodyImageContentType()).isEqualTo(DEFAULT_BODY_IMAGE_CONTENT_TYPE);
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
            .andExpect(jsonPath("$.[*].wrist").value(hasItem(DEFAULT_WRIST.doubleValue())))
            .andExpect(jsonPath("$.[*].waist").value(hasItem(DEFAULT_WAIST.doubleValue())))
            .andExpect(jsonPath("$.[*].lbm").value(hasItem(DEFAULT_LBM.doubleValue())))
            .andExpect(jsonPath("$.[*].muscleMass").value(hasItem(DEFAULT_MUSCLE_MASS.doubleValue())))
            .andExpect(jsonPath("$.[*].muscleMassPercentage").value(hasItem(DEFAULT_MUSCLE_MASS_PERCENTAGE)))
            .andExpect(jsonPath("$.[*].fatMass").value(hasItem(DEFAULT_FAT_MASS.doubleValue())))
            .andExpect(jsonPath("$.[*].fatMassPercentage").value(hasItem(DEFAULT_FAT_MASS_PERCENTAGE)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].bodyImageContentType").value(hasItem(DEFAULT_BODY_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].bodyImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_BODY_IMAGE))))
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
            .andExpect(jsonPath("$.wrist").value(DEFAULT_WRIST.doubleValue()))
            .andExpect(jsonPath("$.waist").value(DEFAULT_WAIST.doubleValue()))
            .andExpect(jsonPath("$.lbm").value(DEFAULT_LBM.doubleValue()))
            .andExpect(jsonPath("$.muscleMass").value(DEFAULT_MUSCLE_MASS.doubleValue()))
            .andExpect(jsonPath("$.muscleMassPercentage").value(DEFAULT_MUSCLE_MASS_PERCENTAGE))
            .andExpect(jsonPath("$.fatMass").value(DEFAULT_FAT_MASS.doubleValue()))
            .andExpect(jsonPath("$.fatMassPercentage").value(DEFAULT_FAT_MASS_PERCENTAGE))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.bodyImageContentType").value(DEFAULT_BODY_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.bodyImage").value(Base64Utils.encodeToString(DEFAULT_BODY_IMAGE)))
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
            .wrist(UPDATED_WRIST)
            .waist(UPDATED_WAIST)
            .lbm(UPDATED_LBM)
            .muscleMass(UPDATED_MUSCLE_MASS)
            .muscleMassPercentage(UPDATED_MUSCLE_MASS_PERCENTAGE)
            .fatMass(UPDATED_FAT_MASS)
            .fatMassPercentage(UPDATED_FAT_MASS_PERCENTAGE)
            .gender(UPDATED_GENDER)
            .age(UPDATED_AGE)
            .bodyImage(UPDATED_BODY_IMAGE)
            .bodyImageContentType(UPDATED_BODY_IMAGE_CONTENT_TYPE)
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
        assertThat(testBodyComposition.getWrist()).isEqualTo(UPDATED_WRIST);
        assertThat(testBodyComposition.getWaist()).isEqualTo(UPDATED_WAIST);
        assertThat(testBodyComposition.getLbm()).isEqualTo(UPDATED_LBM);
        assertThat(testBodyComposition.getMuscleMass()).isEqualTo(UPDATED_MUSCLE_MASS);
        assertThat(testBodyComposition.getMuscleMassPercentage()).isEqualTo(UPDATED_MUSCLE_MASS_PERCENTAGE);
        assertThat(testBodyComposition.getFatMass()).isEqualTo(UPDATED_FAT_MASS);
        assertThat(testBodyComposition.getFatMassPercentage()).isEqualTo(UPDATED_FAT_MASS_PERCENTAGE);
        assertThat(testBodyComposition.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testBodyComposition.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testBodyComposition.getBodyImage()).isEqualTo(UPDATED_BODY_IMAGE);
        assertThat(testBodyComposition.getBodyImageContentType()).isEqualTo(UPDATED_BODY_IMAGE_CONTENT_TYPE);
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
