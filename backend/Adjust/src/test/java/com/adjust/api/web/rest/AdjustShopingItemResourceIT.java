package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.AdjustShopingItem;
import com.adjust.api.repository.AdjustShopingItemRepository;
import com.adjust.api.service.AdjustShopingItemService;
import com.adjust.api.service.dto.AdjustShopingItemDTO;
import com.adjust.api.service.mapper.AdjustShopingItemMapper;

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
 * Integration tests for the {@link AdjustShopingItemResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjustShopingItemResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_TOKEN = 1D;
    private static final Double UPDATED_TOKEN = 2D;

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_ORDERABLE = false;
    private static final Boolean UPDATED_ORDERABLE = true;

    @Autowired
    private AdjustShopingItemRepository adjustShopingItemRepository;

    @Autowired
    private AdjustShopingItemMapper adjustShopingItemMapper;

    @Autowired
    private AdjustShopingItemService adjustShopingItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjustShopingItemMockMvc;

    private AdjustShopingItem adjustShopingItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustShopingItem createEntity(EntityManager em) {
        AdjustShopingItem adjustShopingItem = new AdjustShopingItem()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .token(DEFAULT_TOKEN)
            .price(DEFAULT_PRICE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .orderable(DEFAULT_ORDERABLE);
        return adjustShopingItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustShopingItem createUpdatedEntity(EntityManager em) {
        AdjustShopingItem adjustShopingItem = new AdjustShopingItem()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .token(UPDATED_TOKEN)
            .price(UPDATED_PRICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .orderable(UPDATED_ORDERABLE);
        return adjustShopingItem;
    }

    @BeforeEach
    public void initTest() {
        adjustShopingItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjustShopingItem() throws Exception {
        int databaseSizeBeforeCreate = adjustShopingItemRepository.findAll().size();
        // Create the AdjustShopingItem
        AdjustShopingItemDTO adjustShopingItemDTO = adjustShopingItemMapper.toDto(adjustShopingItem);
        restAdjustShopingItemMockMvc.perform(post("/api/adjust-shoping-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustShopingItemDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjustShopingItem in the database
        List<AdjustShopingItem> adjustShopingItemList = adjustShopingItemRepository.findAll();
        assertThat(adjustShopingItemList).hasSize(databaseSizeBeforeCreate + 1);
        AdjustShopingItem testAdjustShopingItem = adjustShopingItemList.get(adjustShopingItemList.size() - 1);
        assertThat(testAdjustShopingItem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdjustShopingItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAdjustShopingItem.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testAdjustShopingItem.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testAdjustShopingItem.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testAdjustShopingItem.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testAdjustShopingItem.isOrderable()).isEqualTo(DEFAULT_ORDERABLE);
    }

    @Test
    @Transactional
    public void createAdjustShopingItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjustShopingItemRepository.findAll().size();

        // Create the AdjustShopingItem with an existing ID
        adjustShopingItem.setId(1L);
        AdjustShopingItemDTO adjustShopingItemDTO = adjustShopingItemMapper.toDto(adjustShopingItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjustShopingItemMockMvc.perform(post("/api/adjust-shoping-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustShopingItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustShopingItem in the database
        List<AdjustShopingItem> adjustShopingItemList = adjustShopingItemRepository.findAll();
        assertThat(adjustShopingItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjustShopingItems() throws Exception {
        // Initialize the database
        adjustShopingItemRepository.saveAndFlush(adjustShopingItem);

        // Get all the adjustShopingItemList
        restAdjustShopingItemMockMvc.perform(get("/api/adjust-shoping-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjustShopingItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN.doubleValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].orderable").value(hasItem(DEFAULT_ORDERABLE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAdjustShopingItem() throws Exception {
        // Initialize the database
        adjustShopingItemRepository.saveAndFlush(adjustShopingItem);

        // Get the adjustShopingItem
        restAdjustShopingItemMockMvc.perform(get("/api/adjust-shoping-items/{id}", adjustShopingItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjustShopingItem.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN.doubleValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.orderable").value(DEFAULT_ORDERABLE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAdjustShopingItem() throws Exception {
        // Get the adjustShopingItem
        restAdjustShopingItemMockMvc.perform(get("/api/adjust-shoping-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjustShopingItem() throws Exception {
        // Initialize the database
        adjustShopingItemRepository.saveAndFlush(adjustShopingItem);

        int databaseSizeBeforeUpdate = adjustShopingItemRepository.findAll().size();

        // Update the adjustShopingItem
        AdjustShopingItem updatedAdjustShopingItem = adjustShopingItemRepository.findById(adjustShopingItem.getId()).get();
        // Disconnect from session so that the updates on updatedAdjustShopingItem are not directly saved in db
        em.detach(updatedAdjustShopingItem);
        updatedAdjustShopingItem
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .token(UPDATED_TOKEN)
            .price(UPDATED_PRICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .orderable(UPDATED_ORDERABLE);
        AdjustShopingItemDTO adjustShopingItemDTO = adjustShopingItemMapper.toDto(updatedAdjustShopingItem);

        restAdjustShopingItemMockMvc.perform(put("/api/adjust-shoping-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustShopingItemDTO)))
            .andExpect(status().isOk());

        // Validate the AdjustShopingItem in the database
        List<AdjustShopingItem> adjustShopingItemList = adjustShopingItemRepository.findAll();
        assertThat(adjustShopingItemList).hasSize(databaseSizeBeforeUpdate);
        AdjustShopingItem testAdjustShopingItem = adjustShopingItemList.get(adjustShopingItemList.size() - 1);
        assertThat(testAdjustShopingItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdjustShopingItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAdjustShopingItem.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testAdjustShopingItem.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testAdjustShopingItem.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testAdjustShopingItem.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testAdjustShopingItem.isOrderable()).isEqualTo(UPDATED_ORDERABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjustShopingItem() throws Exception {
        int databaseSizeBeforeUpdate = adjustShopingItemRepository.findAll().size();

        // Create the AdjustShopingItem
        AdjustShopingItemDTO adjustShopingItemDTO = adjustShopingItemMapper.toDto(adjustShopingItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjustShopingItemMockMvc.perform(put("/api/adjust-shoping-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustShopingItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustShopingItem in the database
        List<AdjustShopingItem> adjustShopingItemList = adjustShopingItemRepository.findAll();
        assertThat(adjustShopingItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjustShopingItem() throws Exception {
        // Initialize the database
        adjustShopingItemRepository.saveAndFlush(adjustShopingItem);

        int databaseSizeBeforeDelete = adjustShopingItemRepository.findAll().size();

        // Delete the adjustShopingItem
        restAdjustShopingItemMockMvc.perform(delete("/api/adjust-shoping-items/{id}", adjustShopingItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjustShopingItem> adjustShopingItemList = adjustShopingItemRepository.findAll();
        assertThat(adjustShopingItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
