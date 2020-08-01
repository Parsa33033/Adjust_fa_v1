package com.adjust.api.web.rest;

import com.adjust.api.service.AdjustFoodService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.AdjustFoodDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.AdjustFood}.
 */
@RestController
@RequestMapping("/api")
public class AdjustFoodResource {

    private final Logger log = LoggerFactory.getLogger(AdjustFoodResource.class);

    private static final String ENTITY_NAME = "adjustFood";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdjustFoodService adjustFoodService;

    public AdjustFoodResource(AdjustFoodService adjustFoodService) {
        this.adjustFoodService = adjustFoodService;
    }

    /**
     * {@code POST  /adjust-foods} : Create a new adjustFood.
     *
     * @param adjustFoodDTO the adjustFoodDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adjustFoodDTO, or with status {@code 400 (Bad Request)} if the adjustFood has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adjust-foods")
    public ResponseEntity<AdjustFoodDTO> createAdjustFood(@RequestBody AdjustFoodDTO adjustFoodDTO) throws URISyntaxException {
        log.debug("REST request to save AdjustFood : {}", adjustFoodDTO);
        if (adjustFoodDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjustFood cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjustFoodDTO result = adjustFoodService.save(adjustFoodDTO);
        return ResponseEntity.created(new URI("/api/adjust-foods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adjust-foods} : Updates an existing adjustFood.
     *
     * @param adjustFoodDTO the adjustFoodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustFoodDTO,
     * or with status {@code 400 (Bad Request)} if the adjustFoodDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustFoodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-foods")
    public ResponseEntity<AdjustFoodDTO> updateAdjustFood(@RequestBody AdjustFoodDTO adjustFoodDTO) throws URISyntaxException {
        log.debug("REST request to update AdjustFood : {}", adjustFoodDTO);
        if (adjustFoodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustFoodDTO result = adjustFoodService.save(adjustFoodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustFoodDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adjust-foods} : get all the adjustFoods.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustFoods in body.
     */
    @GetMapping("/adjust-foods")
    public List<AdjustFoodDTO> getAllAdjustFoods() {
        log.debug("REST request to get all AdjustFoods");
        return adjustFoodService.findAll();
    }

    /**
     * {@code GET  /adjust-foods/:id} : get the "id" adjustFood.
     *
     * @param id the id of the adjustFoodDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adjustFoodDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adjust-foods/{id}")
    public ResponseEntity<AdjustFoodDTO> getAdjustFood(@PathVariable Long id) {
        log.debug("REST request to get AdjustFood : {}", id);
        Optional<AdjustFoodDTO> adjustFoodDTO = adjustFoodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjustFoodDTO);
    }

    /**
     * {@code DELETE  /adjust-foods/:id} : delete the "id" adjustFood.
     *
     * @param id the id of the adjustFoodDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adjust-foods/{id}")
    public ResponseEntity<Void> deleteAdjustFood(@PathVariable Long id) {
        log.debug("REST request to delete AdjustFood : {}", id);
        adjustFoodService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
