package com.adjust.api.web.rest;

import com.adjust.api.service.AdjustShopingItemService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.AdjustShopingItemDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.AdjustShopingItem}.
 */
@RestController
@RequestMapping("/api")
public class AdjustShopingItemResource {

    private final Logger log = LoggerFactory.getLogger(AdjustShopingItemResource.class);

    private static final String ENTITY_NAME = "adjustShopingItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdjustShopingItemService adjustShopingItemService;

    public AdjustShopingItemResource(AdjustShopingItemService adjustShopingItemService) {
        this.adjustShopingItemService = adjustShopingItemService;
    }

    /**
     * {@code POST  /adjust-shoping-items} : Create a new adjustShopingItem.
     *
     * @param adjustShopingItemDTO the adjustShopingItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adjustShopingItemDTO, or with status {@code 400 (Bad Request)} if the adjustShopingItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adjust-shoping-items")
    public ResponseEntity<AdjustShopingItemDTO> createAdjustShopingItem(@RequestBody AdjustShopingItemDTO adjustShopingItemDTO) throws URISyntaxException {
        log.debug("REST request to save AdjustShopingItem : {}", adjustShopingItemDTO);
        if (adjustShopingItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjustShopingItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjustShopingItemDTO result = adjustShopingItemService.save(adjustShopingItemDTO);
        return ResponseEntity.created(new URI("/api/adjust-shoping-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adjust-shoping-items} : Updates an existing adjustShopingItem.
     *
     * @param adjustShopingItemDTO the adjustShopingItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustShopingItemDTO,
     * or with status {@code 400 (Bad Request)} if the adjustShopingItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustShopingItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-shoping-items")
    public ResponseEntity<AdjustShopingItemDTO> updateAdjustShopingItem(@RequestBody AdjustShopingItemDTO adjustShopingItemDTO) throws URISyntaxException {
        log.debug("REST request to update AdjustShopingItem : {}", adjustShopingItemDTO);
        if (adjustShopingItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustShopingItemDTO result = adjustShopingItemService.save(adjustShopingItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustShopingItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adjust-shoping-items} : get all the adjustShopingItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustShopingItems in body.
     */
    @GetMapping("/adjust-shoping-items")
    public List<AdjustShopingItemDTO> getAllAdjustShopingItems() {
        log.debug("REST request to get all AdjustShopingItems");
        return adjustShopingItemService.findAll();
    }

    /**
     * {@code GET  /adjust-shoping-items/:id} : get the "id" adjustShopingItem.
     *
     * @param id the id of the adjustShopingItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adjustShopingItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adjust-shoping-items/{id}")
    public ResponseEntity<AdjustShopingItemDTO> getAdjustShopingItem(@PathVariable Long id) {
        log.debug("REST request to get AdjustShopingItem : {}", id);
        Optional<AdjustShopingItemDTO> adjustShopingItemDTO = adjustShopingItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjustShopingItemDTO);
    }

    /**
     * {@code DELETE  /adjust-shoping-items/:id} : delete the "id" adjustShopingItem.
     *
     * @param id the id of the adjustShopingItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adjust-shoping-items/{id}")
    public ResponseEntity<Void> deleteAdjustShopingItem(@PathVariable Long id) {
        log.debug("REST request to delete AdjustShopingItem : {}", id);
        adjustShopingItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
