package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.ShopingItem;
import com.adjust.api.repository.ShopingItemRepository;
import com.adjust.api.service.ShopingItemService;
import com.adjust.api.service.dto.ShopingItemDTO;
import com.adjust.api.service.mapper.ShopingItemMapper;

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
 * Integration tests for the {@link ShopingItemResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ShopingItemResourceIT {

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
    private ShopingItemRepository shopingItemRepository;

    @Autowired
    private ShopingItemMapper shopingItemMapper;

    @Autowired
    private ShopingItemService shopingItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restShopingItemMockMvc;

    private ShopingItem shopingItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShopingItem createEntity(EntityManager em) {
        ShopingItem shopingItem = new ShopingItem()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .token(DEFAULT_TOKEN)
            .price(DEFAULT_PRICE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .orderable(DEFAULT_ORDERABLE);
        return shopingItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShopingItem createUpdatedEntity(EntityManager em) {
        ShopingItem shopingItem = new ShopingItem()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .token(UPDATED_TOKEN)
            .price(UPDATED_PRICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .orderable(UPDATED_ORDERABLE);
        return shopingItem;
    }

    @BeforeEach
    public void initTest() {
        shopingItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createShopingItem() throws Exception {
        int databaseSizeBeforeCreate = shopingItemRepository.findAll().size();
        // Create the ShopingItem
        ShopingItemDTO shopingItemDTO = shopingItemMapper.toDto(shopingItem);
        restShopingItemMockMvc.perform(post("/api/shoping-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopingItemDTO)))
            .andExpect(status().isCreated());

        // Validate the ShopingItem in the database
        List<ShopingItem> shopingItemList = shopingItemRepository.findAll();
        assertThat(shopingItemList).hasSize(databaseSizeBeforeCreate + 1);
        ShopingItem testShopingItem = shopingItemList.get(shopingItemList.size() - 1);
        assertThat(testShopingItem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testShopingItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testShopingItem.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testShopingItem.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testShopingItem.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testShopingItem.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testShopingItem.isOrderable()).isEqualTo(DEFAULT_ORDERABLE);
    }

    @Test
    @Transactional
    public void createShopingItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shopingItemRepository.findAll().size();

        // Create the ShopingItem with an existing ID
        shopingItem.setId(1L);
        ShopingItemDTO shopingItemDTO = shopingItemMapper.toDto(shopingItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShopingItemMockMvc.perform(post("/api/shoping-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopingItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShopingItem in the database
        List<ShopingItem> shopingItemList = shopingItemRepository.findAll();
        assertThat(shopingItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShopingItems() throws Exception {
        // Initialize the database
        shopingItemRepository.saveAndFlush(shopingItem);

        // Get all the shopingItemList
        restShopingItemMockMvc.perform(get("/api/shoping-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shopingItem.getId().intValue())))
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
    public void getShopingItem() throws Exception {
        // Initialize the database
        shopingItemRepository.saveAndFlush(shopingItem);

        // Get the shopingItem
        restShopingItemMockMvc.perform(get("/api/shoping-items/{id}", shopingItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shopingItem.getId().intValue()))
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
    public void getNonExistingShopingItem() throws Exception {
        // Get the shopingItem
        restShopingItemMockMvc.perform(get("/api/shoping-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShopingItem() throws Exception {
        // Initialize the database
        shopingItemRepository.saveAndFlush(shopingItem);

        int databaseSizeBeforeUpdate = shopingItemRepository.findAll().size();

        // Update the shopingItem
        ShopingItem updatedShopingItem = shopingItemRepository.findById(shopingItem.getId()).get();
        // Disconnect from session so that the updates on updatedShopingItem are not directly saved in db
        em.detach(updatedShopingItem);
        updatedShopingItem
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .token(UPDATED_TOKEN)
            .price(UPDATED_PRICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .orderable(UPDATED_ORDERABLE);
        ShopingItemDTO shopingItemDTO = shopingItemMapper.toDto(updatedShopingItem);

        restShopingItemMockMvc.perform(put("/api/shoping-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopingItemDTO)))
            .andExpect(status().isOk());

        // Validate the ShopingItem in the database
        List<ShopingItem> shopingItemList = shopingItemRepository.findAll();
        assertThat(shopingItemList).hasSize(databaseSizeBeforeUpdate);
        ShopingItem testShopingItem = shopingItemList.get(shopingItemList.size() - 1);
        assertThat(testShopingItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testShopingItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testShopingItem.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testShopingItem.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testShopingItem.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testShopingItem.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testShopingItem.isOrderable()).isEqualTo(UPDATED_ORDERABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingShopingItem() throws Exception {
        int databaseSizeBeforeUpdate = shopingItemRepository.findAll().size();

        // Create the ShopingItem
        ShopingItemDTO shopingItemDTO = shopingItemMapper.toDto(shopingItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShopingItemMockMvc.perform(put("/api/shoping-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopingItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShopingItem in the database
        List<ShopingItem> shopingItemList = shopingItemRepository.findAll();
        assertThat(shopingItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShopingItem() throws Exception {
        // Initialize the database
        shopingItemRepository.saveAndFlush(shopingItem);

        int databaseSizeBeforeDelete = shopingItemRepository.findAll().size();

        // Delete the shopingItem
        restShopingItemMockMvc.perform(delete("/api/shoping-items/{id}", shopingItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShopingItem> shopingItemList = shopingItemRepository.findAll();
        assertThat(shopingItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
