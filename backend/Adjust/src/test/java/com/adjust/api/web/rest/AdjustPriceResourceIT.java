package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.AdjustPrice;
import com.adjust.api.repository.AdjustPriceRepository;
import com.adjust.api.service.AdjustPriceService;
import com.adjust.api.service.dto.AdjustPriceDTO;
import com.adjust.api.service.mapper.AdjustPriceMapper;

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
 * Integration tests for the {@link AdjustPriceResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjustPriceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_TOKEN = 1D;
    private static final Double UPDATED_TOKEN = 2D;

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    @Autowired
    private AdjustPriceRepository adjustPriceRepository;

    @Autowired
    private AdjustPriceMapper adjustPriceMapper;

    @Autowired
    private AdjustPriceService adjustPriceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjustPriceMockMvc;

    private AdjustPrice adjustPrice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustPrice createEntity(EntityManager em) {
        AdjustPrice adjustPrice = new AdjustPrice()
            .name(DEFAULT_NAME)
            .token(DEFAULT_TOKEN)
            .price(DEFAULT_PRICE);
        return adjustPrice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdjustPrice createUpdatedEntity(EntityManager em) {
        AdjustPrice adjustPrice = new AdjustPrice()
            .name(UPDATED_NAME)
            .token(UPDATED_TOKEN)
            .price(UPDATED_PRICE);
        return adjustPrice;
    }

    @BeforeEach
    public void initTest() {
        adjustPrice = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjustPrice() throws Exception {
        int databaseSizeBeforeCreate = adjustPriceRepository.findAll().size();
        // Create the AdjustPrice
        AdjustPriceDTO adjustPriceDTO = adjustPriceMapper.toDto(adjustPrice);
        restAdjustPriceMockMvc.perform(post("/api/adjust-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustPriceDTO)))
            .andExpect(status().isCreated());

        // Validate the AdjustPrice in the database
        List<AdjustPrice> adjustPriceList = adjustPriceRepository.findAll();
        assertThat(adjustPriceList).hasSize(databaseSizeBeforeCreate + 1);
        AdjustPrice testAdjustPrice = adjustPriceList.get(adjustPriceList.size() - 1);
        assertThat(testAdjustPrice.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdjustPrice.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testAdjustPrice.getPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    @Transactional
    public void createAdjustPriceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjustPriceRepository.findAll().size();

        // Create the AdjustPrice with an existing ID
        adjustPrice.setId(1L);
        AdjustPriceDTO adjustPriceDTO = adjustPriceMapper.toDto(adjustPrice);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjustPriceMockMvc.perform(post("/api/adjust-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustPriceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustPrice in the database
        List<AdjustPrice> adjustPriceList = adjustPriceRepository.findAll();
        assertThat(adjustPriceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdjustPrices() throws Exception {
        // Initialize the database
        adjustPriceRepository.saveAndFlush(adjustPrice);

        // Get all the adjustPriceList
        restAdjustPriceMockMvc.perform(get("/api/adjust-prices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjustPrice.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN.doubleValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getAdjustPrice() throws Exception {
        // Initialize the database
        adjustPriceRepository.saveAndFlush(adjustPrice);

        // Get the adjustPrice
        restAdjustPriceMockMvc.perform(get("/api/adjust-prices/{id}", adjustPrice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjustPrice.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN.doubleValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAdjustPrice() throws Exception {
        // Get the adjustPrice
        restAdjustPriceMockMvc.perform(get("/api/adjust-prices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjustPrice() throws Exception {
        // Initialize the database
        adjustPriceRepository.saveAndFlush(adjustPrice);

        int databaseSizeBeforeUpdate = adjustPriceRepository.findAll().size();

        // Update the adjustPrice
        AdjustPrice updatedAdjustPrice = adjustPriceRepository.findById(adjustPrice.getId()).get();
        // Disconnect from session so that the updates on updatedAdjustPrice are not directly saved in db
        em.detach(updatedAdjustPrice);
        updatedAdjustPrice
            .name(UPDATED_NAME)
            .token(UPDATED_TOKEN)
            .price(UPDATED_PRICE);
        AdjustPriceDTO adjustPriceDTO = adjustPriceMapper.toDto(updatedAdjustPrice);

        restAdjustPriceMockMvc.perform(put("/api/adjust-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustPriceDTO)))
            .andExpect(status().isOk());

        // Validate the AdjustPrice in the database
        List<AdjustPrice> adjustPriceList = adjustPriceRepository.findAll();
        assertThat(adjustPriceList).hasSize(databaseSizeBeforeUpdate);
        AdjustPrice testAdjustPrice = adjustPriceList.get(adjustPriceList.size() - 1);
        assertThat(testAdjustPrice.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdjustPrice.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testAdjustPrice.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjustPrice() throws Exception {
        int databaseSizeBeforeUpdate = adjustPriceRepository.findAll().size();

        // Create the AdjustPrice
        AdjustPriceDTO adjustPriceDTO = adjustPriceMapper.toDto(adjustPrice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjustPriceMockMvc.perform(put("/api/adjust-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjustPriceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdjustPrice in the database
        List<AdjustPrice> adjustPriceList = adjustPriceRepository.findAll();
        assertThat(adjustPriceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjustPrice() throws Exception {
        // Initialize the database
        adjustPriceRepository.saveAndFlush(adjustPrice);

        int databaseSizeBeforeDelete = adjustPriceRepository.findAll().size();

        // Delete the adjustPrice
        restAdjustPriceMockMvc.perform(delete("/api/adjust-prices/{id}", adjustPrice.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdjustPrice> adjustPriceList = adjustPriceRepository.findAll();
        assertThat(adjustPriceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
