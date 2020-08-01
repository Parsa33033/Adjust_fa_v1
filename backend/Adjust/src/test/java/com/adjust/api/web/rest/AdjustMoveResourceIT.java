package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.AdjustMove;
import com.adjust.api.repository.AdjustMoveRepository;
import com.adjust.api.service.AdjustMoveService;
import com.adjust.api.service.dto.AdjustMoveDTO;
import com.adjust.api.service.mapper.AdjustMoveMapper;

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

import com.adjust.api.domain.enumeration.MuscleType;
/**
 * Integration tests for the {@link AdjustMoveResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjustMoveResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MUSCLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MUSCLE_NAME = "BBBBBBBBBB";

    private static final MuscleType DEFAULT_MUSCLE_TYPE = MuscleType.CHEST;
    private static final MuscleType UPDATED_MUSCLE_TYPE = MuscleType.SHOULDER;

    private static final String DEFAULT_EQUIPMENT = "AAAAAAAAAA";
    private static final String UPDATED_EQUIPMENT = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PICTURE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PICTURE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PICTURE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PICTURE_CONTENT_TYPE = "image/png";

    @Autowired
    private AdjustMoveRepository adjustMoveRepository;

    @Autowired
    private AdjustMoveMapper adjustMoveMapper;

    @Autowired
    private AdjustMoveService adjustMoveService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjustMoveMockMvc;

    private AdjustMove adjustMove;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustMove createEntity(EntityManager em) {
        AdjustMove adjustMove = new AdjustMove()
            .name(DEFAULT_NAME)
            .muscleName(DEFAULT_MUSCLE_NAME)
            .muscleType(DEFAULT_MUSCLE_TYPE)
            .equipment(DEFAULT_EQUIPMENT)
            .picture(DEFAULT_PICTURE)
            .pictureContentType(DEFAULT_PICTURE_CONTENT_TYPE);
        return adjustMove;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustMove createUpdatedEntity(EntityManager em) {
        AdjustMove adjustMove = new AdjustMove()
            .name(UPDATED_NAME)
            .muscleName(UPDATED_MUSCLE_NAME)
            .muscleType(UPDATED_MUSCLE_TYPE)
            .equipment(UPDATED_EQUIPMENT)
            .picture(UPDATED_PICTURE)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE);
        return adjustMove;
    }

    @BeforeEach
    public void initTest() {
        adjustMove = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjustMove() throws Exception {
        int databaseSizeBeforeCreate = adjustMoveRepository.findAll().size();
        // Create the AdjustMove
        AdjustMoveDTO adjustMoveDTO = adjustMoveMapper.toDto(adjustMove);
        restAdjustMoveMockMvc.perform(post("/api/adjust-moves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustMoveDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjustMove in the database
        List<AdjustMove> adjustMoveList = adjustMoveRepository.findAll();
        assertThat(adjustMoveList).hasSize(databaseSizeBeforeCreate + 1);
        AdjustMove testAdjustMove = adjustMoveList.get(adjustMoveList.size() - 1);
        assertThat(testAdjustMove.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdjustMove.getMuscleName()).isEqualTo(DEFAULT_MUSCLE_NAME);
        assertThat(testAdjustMove.getMuscleType()).isEqualTo(DEFAULT_MUSCLE_TYPE);
        assertThat(testAdjustMove.getEquipment()).isEqualTo(DEFAULT_EQUIPMENT);
        assertThat(testAdjustMove.getPicture()).isEqualTo(DEFAULT_PICTURE);
        assertThat(testAdjustMove.getPictureContentType()).isEqualTo(DEFAULT_PICTURE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createAdjustMoveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjustMoveRepository.findAll().size();

        // Create the AdjustMove with an existing ID
        adjustMove.setId(1L);
        AdjustMoveDTO adjustMoveDTO = adjustMoveMapper.toDto(adjustMove);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjustMoveMockMvc.perform(post("/api/adjust-moves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustMoveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustMove in the database
        List<AdjustMove> adjustMoveList = adjustMoveRepository.findAll();
        assertThat(adjustMoveList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjustMoves() throws Exception {
        // Initialize the database
        adjustMoveRepository.saveAndFlush(adjustMove);

        // Get all the adjustMoveList
        restAdjustMoveMockMvc.perform(get("/api/adjust-moves?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjustMove.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].muscleName").value(hasItem(DEFAULT_MUSCLE_NAME)))
            .andExpect(jsonPath("$.[*].muscleType").value(hasItem(DEFAULT_MUSCLE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].equipment").value(hasItem(DEFAULT_EQUIPMENT)))
            .andExpect(jsonPath("$.[*].pictureContentType").value(hasItem(DEFAULT_PICTURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(Base64Utils.encodeToString(DEFAULT_PICTURE))));
    }
    
    @Test
    @Transactional
    public void getAdjustMove() throws Exception {
        // Initialize the database
        adjustMoveRepository.saveAndFlush(adjustMove);

        // Get the adjustMove
        restAdjustMoveMockMvc.perform(get("/api/adjust-moves/{id}", adjustMove.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjustMove.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.muscleName").value(DEFAULT_MUSCLE_NAME))
            .andExpect(jsonPath("$.muscleType").value(DEFAULT_MUSCLE_TYPE.toString()))
            .andExpect(jsonPath("$.equipment").value(DEFAULT_EQUIPMENT))
            .andExpect(jsonPath("$.pictureContentType").value(DEFAULT_PICTURE_CONTENT_TYPE))
            .andExpect(jsonPath("$.picture").value(Base64Utils.encodeToString(DEFAULT_PICTURE)));
    }
    @Test
    @Transactional
    public void getNonExistingAdjustMove() throws Exception {
        // Get the adjustMove
        restAdjustMoveMockMvc.perform(get("/api/adjust-moves/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjustMove() throws Exception {
        // Initialize the database
        adjustMoveRepository.saveAndFlush(adjustMove);

        int databaseSizeBeforeUpdate = adjustMoveRepository.findAll().size();

        // Update the adjustMove
        AdjustMove updatedAdjustMove = adjustMoveRepository.findById(adjustMove.getId()).get();
        // Disconnect from session so that the updates on updatedAdjustMove are not directly saved in db
        em.detach(updatedAdjustMove);
        updatedAdjustMove
            .name(UPDATED_NAME)
            .muscleName(UPDATED_MUSCLE_NAME)
            .muscleType(UPDATED_MUSCLE_TYPE)
            .equipment(UPDATED_EQUIPMENT)
            .picture(UPDATED_PICTURE)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE);
        AdjustMoveDTO adjustMoveDTO = adjustMoveMapper.toDto(updatedAdjustMove);

        restAdjustMoveMockMvc.perform(put("/api/adjust-moves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustMoveDTO)))
            .andExpect(status().isOk());

        // Validate the AdjustMove in the database
        List<AdjustMove> adjustMoveList = adjustMoveRepository.findAll();
        assertThat(adjustMoveList).hasSize(databaseSizeBeforeUpdate);
        AdjustMove testAdjustMove = adjustMoveList.get(adjustMoveList.size() - 1);
        assertThat(testAdjustMove.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdjustMove.getMuscleName()).isEqualTo(UPDATED_MUSCLE_NAME);
        assertThat(testAdjustMove.getMuscleType()).isEqualTo(UPDATED_MUSCLE_TYPE);
        assertThat(testAdjustMove.getEquipment()).isEqualTo(UPDATED_EQUIPMENT);
        assertThat(testAdjustMove.getPicture()).isEqualTo(UPDATED_PICTURE);
        assertThat(testAdjustMove.getPictureContentType()).isEqualTo(UPDATED_PICTURE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjustMove() throws Exception {
        int databaseSizeBeforeUpdate = adjustMoveRepository.findAll().size();

        // Create the AdjustMove
        AdjustMoveDTO adjustMoveDTO = adjustMoveMapper.toDto(adjustMove);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjustMoveMockMvc.perform(put("/api/adjust-moves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustMoveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustMove in the database
        List<AdjustMove> adjustMoveList = adjustMoveRepository.findAll();
        assertThat(adjustMoveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjustMove() throws Exception {
        // Initialize the database
        adjustMoveRepository.saveAndFlush(adjustMove);

        int databaseSizeBeforeDelete = adjustMoveRepository.findAll().size();

        // Delete the adjustMove
        restAdjustMoveMockMvc.perform(delete("/api/adjust-moves/{id}", adjustMove.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjustMove> adjustMoveList = adjustMoveRepository.findAll();
        assertThat(adjustMoveList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
