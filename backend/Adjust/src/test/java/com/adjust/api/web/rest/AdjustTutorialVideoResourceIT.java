package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.AdjustTutorialVideo;
import com.adjust.api.repository.AdjustTutorialVideoRepository;
import com.adjust.api.service.AdjustTutorialVideoService;
import com.adjust.api.service.dto.AdjustTutorialVideoDTO;
import com.adjust.api.service.mapper.AdjustTutorialVideoMapper;

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
 * Integration tests for the {@link AdjustTutorialVideoResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjustTutorialVideoResourceIT {

    private static final byte[] DEFAULT_CONTENT = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CONTENT = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CONTENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CONTENT_CONTENT_TYPE = "image/png";

    @Autowired
    private AdjustTutorialVideoRepository adjustTutorialVideoRepository;

    @Autowired
    private AdjustTutorialVideoMapper adjustTutorialVideoMapper;

    @Autowired
    private AdjustTutorialVideoService adjustTutorialVideoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjustTutorialVideoMockMvc;

    private AdjustTutorialVideo adjustTutorialVideo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustTutorialVideo createEntity(EntityManager em) {
        AdjustTutorialVideo adjustTutorialVideo = new AdjustTutorialVideo()
            .content(DEFAULT_CONTENT)
            .contentContentType(DEFAULT_CONTENT_CONTENT_TYPE);
        return adjustTutorialVideo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustTutorialVideo createUpdatedEntity(EntityManager em) {
        AdjustTutorialVideo adjustTutorialVideo = new AdjustTutorialVideo()
            .content(UPDATED_CONTENT)
            .contentContentType(UPDATED_CONTENT_CONTENT_TYPE);
        return adjustTutorialVideo;
    }

    @BeforeEach
    public void initTest() {
        adjustTutorialVideo = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjustTutorialVideo() throws Exception {
        int databaseSizeBeforeCreate = adjustTutorialVideoRepository.findAll().size();
        // Create the AdjustTutorialVideo
        AdjustTutorialVideoDTO adjustTutorialVideoDTO = adjustTutorialVideoMapper.toDto(adjustTutorialVideo);
        restAdjustTutorialVideoMockMvc.perform(post("/api/adjust-tutorial-videos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTutorialVideoDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjustTutorialVideo in the database
        List<AdjustTutorialVideo> adjustTutorialVideoList = adjustTutorialVideoRepository.findAll();
        assertThat(adjustTutorialVideoList).hasSize(databaseSizeBeforeCreate + 1);
        AdjustTutorialVideo testAdjustTutorialVideo = adjustTutorialVideoList.get(adjustTutorialVideoList.size() - 1);
        assertThat(testAdjustTutorialVideo.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testAdjustTutorialVideo.getContentContentType()).isEqualTo(DEFAULT_CONTENT_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createAdjustTutorialVideoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjustTutorialVideoRepository.findAll().size();

        // Create the AdjustTutorialVideo with an existing ID
        adjustTutorialVideo.setId(1L);
        AdjustTutorialVideoDTO adjustTutorialVideoDTO = adjustTutorialVideoMapper.toDto(adjustTutorialVideo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjustTutorialVideoMockMvc.perform(post("/api/adjust-tutorial-videos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTutorialVideoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustTutorialVideo in the database
        List<AdjustTutorialVideo> adjustTutorialVideoList = adjustTutorialVideoRepository.findAll();
        assertThat(adjustTutorialVideoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjustTutorialVideos() throws Exception {
        // Initialize the database
        adjustTutorialVideoRepository.saveAndFlush(adjustTutorialVideo);

        // Get all the adjustTutorialVideoList
        restAdjustTutorialVideoMockMvc.perform(get("/api/adjust-tutorial-videos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjustTutorialVideo.getId().intValue())))
            .andExpect(jsonPath("$.[*].contentContentType").value(hasItem(DEFAULT_CONTENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(Base64Utils.encodeToString(DEFAULT_CONTENT))));
    }
    
    @Test
    @Transactional
    public void getAdjustTutorialVideo() throws Exception {
        // Initialize the database
        adjustTutorialVideoRepository.saveAndFlush(adjustTutorialVideo);

        // Get the adjustTutorialVideo
        restAdjustTutorialVideoMockMvc.perform(get("/api/adjust-tutorial-videos/{id}", adjustTutorialVideo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjustTutorialVideo.getId().intValue()))
            .andExpect(jsonPath("$.contentContentType").value(DEFAULT_CONTENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.content").value(Base64Utils.encodeToString(DEFAULT_CONTENT)));
    }
    @Test
    @Transactional
    public void getNonExistingAdjustTutorialVideo() throws Exception {
        // Get the adjustTutorialVideo
        restAdjustTutorialVideoMockMvc.perform(get("/api/adjust-tutorial-videos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjustTutorialVideo() throws Exception {
        // Initialize the database
        adjustTutorialVideoRepository.saveAndFlush(adjustTutorialVideo);

        int databaseSizeBeforeUpdate = adjustTutorialVideoRepository.findAll().size();

        // Update the adjustTutorialVideo
        AdjustTutorialVideo updatedAdjustTutorialVideo = adjustTutorialVideoRepository.findById(adjustTutorialVideo.getId()).get();
        // Disconnect from session so that the updates on updatedAdjustTutorialVideo are not directly saved in db
        em.detach(updatedAdjustTutorialVideo);
        updatedAdjustTutorialVideo
            .content(UPDATED_CONTENT)
            .contentContentType(UPDATED_CONTENT_CONTENT_TYPE);
        AdjustTutorialVideoDTO adjustTutorialVideoDTO = adjustTutorialVideoMapper.toDto(updatedAdjustTutorialVideo);

        restAdjustTutorialVideoMockMvc.perform(put("/api/adjust-tutorial-videos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTutorialVideoDTO)))
            .andExpect(status().isOk());

        // Validate the AdjustTutorialVideo in the database
        List<AdjustTutorialVideo> adjustTutorialVideoList = adjustTutorialVideoRepository.findAll();
        assertThat(adjustTutorialVideoList).hasSize(databaseSizeBeforeUpdate);
        AdjustTutorialVideo testAdjustTutorialVideo = adjustTutorialVideoList.get(adjustTutorialVideoList.size() - 1);
        assertThat(testAdjustTutorialVideo.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testAdjustTutorialVideo.getContentContentType()).isEqualTo(UPDATED_CONTENT_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjustTutorialVideo() throws Exception {
        int databaseSizeBeforeUpdate = adjustTutorialVideoRepository.findAll().size();

        // Create the AdjustTutorialVideo
        AdjustTutorialVideoDTO adjustTutorialVideoDTO = adjustTutorialVideoMapper.toDto(adjustTutorialVideo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjustTutorialVideoMockMvc.perform(put("/api/adjust-tutorial-videos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustTutorialVideoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustTutorialVideo in the database
        List<AdjustTutorialVideo> adjustTutorialVideoList = adjustTutorialVideoRepository.findAll();
        assertThat(adjustTutorialVideoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjustTutorialVideo() throws Exception {
        // Initialize the database
        adjustTutorialVideoRepository.saveAndFlush(adjustTutorialVideo);

        int databaseSizeBeforeDelete = adjustTutorialVideoRepository.findAll().size();

        // Delete the adjustTutorialVideo
        restAdjustTutorialVideoMockMvc.perform(delete("/api/adjust-tutorial-videos/{id}", adjustTutorialVideo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjustTutorialVideo> adjustTutorialVideoList = adjustTutorialVideoRepository.findAll();
        assertThat(adjustTutorialVideoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
