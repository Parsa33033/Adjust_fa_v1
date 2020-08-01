package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.Move;
import com.adjust.api.repository.MoveRepository;
import com.adjust.api.service.MoveService;
import com.adjust.api.service.dto.MoveDTO;
import com.adjust.api.service.mapper.MoveMapper;

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
 * Integration tests for the {@link MoveResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MoveResourceIT {

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

    private static final Long DEFAULT_ADJUST_MOVE_ID = 1L;
    private static final Long UPDATED_ADJUST_MOVE_ID = 2L;

    @Autowired
    private MoveRepository moveRepository;

    @Autowired
    private MoveMapper moveMapper;

    @Autowired
    private MoveService moveService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMoveMockMvc;

    private Move move;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Move createEntity(EntityManager em) {
        Move move = new Move()
            .name(DEFAULT_NAME)
            .muscleName(DEFAULT_MUSCLE_NAME)
            .muscleType(DEFAULT_MUSCLE_TYPE)
            .equipment(DEFAULT_EQUIPMENT)
            .picture(DEFAULT_PICTURE)
            .pictureContentType(DEFAULT_PICTURE_CONTENT_TYPE)
            .adjustMoveId(DEFAULT_ADJUST_MOVE_ID);
        return move;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Move createUpdatedEntity(EntityManager em) {
        Move move = new Move()
            .name(UPDATED_NAME)
            .muscleName(UPDATED_MUSCLE_NAME)
            .muscleType(UPDATED_MUSCLE_TYPE)
            .equipment(UPDATED_EQUIPMENT)
            .picture(UPDATED_PICTURE)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE)
            .adjustMoveId(UPDATED_ADJUST_MOVE_ID);
        return move;
    }

    @BeforeEach
    public void initTest() {
        move = createEntity(em);
    }

    @Test
    @Transactional
    public void createMove() throws Exception {
        int databaseSizeBeforeCreate = moveRepository.findAll().size();
        // Create the Move
        MoveDTO moveDTO = moveMapper.toDto(move);
        restMoveMockMvc.perform(post("/api/moves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(moveDTO)))
            .andExpect(status().isCreated());

        // Validate the Move in the database
        List<Move> moveList = moveRepository.findAll();
        assertThat(moveList).hasSize(databaseSizeBeforeCreate + 1);
        Move testMove = moveList.get(moveList.size() - 1);
        assertThat(testMove.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMove.getMuscleName()).isEqualTo(DEFAULT_MUSCLE_NAME);
        assertThat(testMove.getMuscleType()).isEqualTo(DEFAULT_MUSCLE_TYPE);
        assertThat(testMove.getEquipment()).isEqualTo(DEFAULT_EQUIPMENT);
        assertThat(testMove.getPicture()).isEqualTo(DEFAULT_PICTURE);
        assertThat(testMove.getPictureContentType()).isEqualTo(DEFAULT_PICTURE_CONTENT_TYPE);
        assertThat(testMove.getAdjustMoveId()).isEqualTo(DEFAULT_ADJUST_MOVE_ID);
    }

    @Test
    @Transactional
    public void createMoveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = moveRepository.findAll().size();

        // Create the Move with an existing ID
        move.setId(1L);
        MoveDTO moveDTO = moveMapper.toDto(move);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMoveMockMvc.perform(post("/api/moves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(moveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Move in the database
        List<Move> moveList = moveRepository.findAll();
        assertThat(moveList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMoves() throws Exception {
        // Initialize the database
        moveRepository.saveAndFlush(move);

        // Get all the moveList
        restMoveMockMvc.perform(get("/api/moves?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(move.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].muscleName").value(hasItem(DEFAULT_MUSCLE_NAME)))
            .andExpect(jsonPath("$.[*].muscleType").value(hasItem(DEFAULT_MUSCLE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].equipment").value(hasItem(DEFAULT_EQUIPMENT)))
            .andExpect(jsonPath("$.[*].pictureContentType").value(hasItem(DEFAULT_PICTURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(Base64Utils.encodeToString(DEFAULT_PICTURE))))
            .andExpect(jsonPath("$.[*].adjustMoveId").value(hasItem(DEFAULT_ADJUST_MOVE_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getMove() throws Exception {
        // Initialize the database
        moveRepository.saveAndFlush(move);

        // Get the move
        restMoveMockMvc.perform(get("/api/moves/{id}", move.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(move.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.muscleName").value(DEFAULT_MUSCLE_NAME))
            .andExpect(jsonPath("$.muscleType").value(DEFAULT_MUSCLE_TYPE.toString()))
            .andExpect(jsonPath("$.equipment").value(DEFAULT_EQUIPMENT))
            .andExpect(jsonPath("$.pictureContentType").value(DEFAULT_PICTURE_CONTENT_TYPE))
            .andExpect(jsonPath("$.picture").value(Base64Utils.encodeToString(DEFAULT_PICTURE)))
            .andExpect(jsonPath("$.adjustMoveId").value(DEFAULT_ADJUST_MOVE_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMove() throws Exception {
        // Get the move
        restMoveMockMvc.perform(get("/api/moves/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMove() throws Exception {
        // Initialize the database
        moveRepository.saveAndFlush(move);

        int databaseSizeBeforeUpdate = moveRepository.findAll().size();

        // Update the move
        Move updatedMove = moveRepository.findById(move.getId()).get();
        // Disconnect from session so that the updates on updatedMove are not directly saved in db
        em.detach(updatedMove);
        updatedMove
            .name(UPDATED_NAME)
            .muscleName(UPDATED_MUSCLE_NAME)
            .muscleType(UPDATED_MUSCLE_TYPE)
            .equipment(UPDATED_EQUIPMENT)
            .picture(UPDATED_PICTURE)
            .pictureContentType(UPDATED_PICTURE_CONTENT_TYPE)
            .adjustMoveId(UPDATED_ADJUST_MOVE_ID);
        MoveDTO moveDTO = moveMapper.toDto(updatedMove);

        restMoveMockMvc.perform(put("/api/moves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(moveDTO)))
            .andExpect(status().isOk());

        // Validate the Move in the database
        List<Move> moveList = moveRepository.findAll();
        assertThat(moveList).hasSize(databaseSizeBeforeUpdate);
        Move testMove = moveList.get(moveList.size() - 1);
        assertThat(testMove.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMove.getMuscleName()).isEqualTo(UPDATED_MUSCLE_NAME);
        assertThat(testMove.getMuscleType()).isEqualTo(UPDATED_MUSCLE_TYPE);
        assertThat(testMove.getEquipment()).isEqualTo(UPDATED_EQUIPMENT);
        assertThat(testMove.getPicture()).isEqualTo(UPDATED_PICTURE);
        assertThat(testMove.getPictureContentType()).isEqualTo(UPDATED_PICTURE_CONTENT_TYPE);
        assertThat(testMove.getAdjustMoveId()).isEqualTo(UPDATED_ADJUST_MOVE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMove() throws Exception {
        int databaseSizeBeforeUpdate = moveRepository.findAll().size();

        // Create the Move
        MoveDTO moveDTO = moveMapper.toDto(move);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMoveMockMvc.perform(put("/api/moves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(moveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Move in the database
        List<Move> moveList = moveRepository.findAll();
        assertThat(moveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMove() throws Exception {
        // Initialize the database
        moveRepository.saveAndFlush(move);

        int databaseSizeBeforeDelete = moveRepository.findAll().size();

        // Delete the move
        restMoveMockMvc.perform(delete("/api/moves/{id}", move.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Move> moveList = moveRepository.findAll();
        assertThat(moveList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
