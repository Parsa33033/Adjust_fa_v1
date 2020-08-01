package com.adjust.api.web.rest;

import com.adjust.api.service.ShopingItemService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.ShopingItemDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.adjust.api.domain.ShopingItem}.
 */
@RestController
@RequestMapping("/api")
public class ShopingItemResource {

    private final Logger log = LoggerFactory.getLogger(ShopingItemResource.class);

    private static final String ENTITY_NAME = "shopingItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShopingItemService shopingItemService;

    public ShopingItemResource(ShopingItemService shopingItemService) {
        this.shopingItemService = shopingItemService;
    }

    /**
     * {@code POST  /shoping-items} : Create a new shopingItem.
     *
     * @param shopingItemDTO the shopingItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shopingItemDTO, or with status {@code 400 (Bad Request)} if the shopingItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shoping-items")
    public ResponseEntity<ShopingItemDTO> createShopingItem(@RequestBody ShopingItemDTO shopingItemDTO) throws URISyntaxException {
        log.debug("REST request to save ShopingItem : {}", shopingItemDTO);
        if (shopingItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new shopingItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShopingItemDTO result = shopingItemService.save(shopingItemDTO);
        return ResponseEntity.created(new URI("/api/shoping-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shoping-items} : Updates an existing shopingItem.
     *
     * @param shopingItemDTO the shopingItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shopingItemDTO,
     * or with status {@code 400 (Bad Request)} if the shopingItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shopingItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shoping-items")
    public ResponseEntity<ShopingItemDTO> updateShopingItem(@RequestBody ShopingItemDTO shopingItemDTO) throws URISyntaxException {
        log.debug("REST request to update ShopingItem : {}", shopingItemDTO);
        if (shopingItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShopingItemDTO result = shopingItemService.save(shopingItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shopingItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shoping-items} : get all the shopingItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shopingItems in body.
     */
    @GetMapping("/shoping-items")
    public List<ShopingItemDTO> getAllShopingItems() {
        log.debug("REST request to get all ShopingItems");
        return shopingItemService.findAll();
    }

    /**
     * {@code GET  /shoping-items/:id} : get the "id" shopingItem.
     *
     * @param id the id of the shopingItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shopingItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shoping-items/{id}")
    public ResponseEntity<ShopingItemDTO> getShopingItem(@PathVariable Long id) {
        log.debug("REST request to get ShopingItem : {}", id);
        Optional<ShopingItemDTO> shopingItemDTO = shopingItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shopingItemDTO);
    }

    /**
     * {@code DELETE  /shoping-items/:id} : delete the "id" shopingItem.
     *
     * @param id the id of the shopingItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shoping-items/{id}")
    public ResponseEntity<Void> deleteShopingItem(@PathVariable Long id) {
        log.debug("REST request to delete ShopingItem : {}", id);
        shopingItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
