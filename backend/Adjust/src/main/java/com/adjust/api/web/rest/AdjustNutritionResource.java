package com.adjust.api.web.rest;

import com.adjust.api.service.AdjustNutritionService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.AdjustNutritionDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.AdjustNutrition}.
 */
@RestController
@RequestMapping("/api")
public class AdjustNutritionResource {

    private final Logger log = LoggerFactory.getLogger(AdjustNutritionResource.class);

    private static final String ENTITY_NAME = "adjustNutrition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdjustNutritionService adjustNutritionService;

    public AdjustNutritionResource(AdjustNutritionService adjustNutritionService) {
        this.adjustNutritionService = adjustNutritionService;
    }

    /**
     * {@code POST  /adjust-nutritions} : Create a new adjustNutrition.
     *
     * @param adjustNutritionDTO the adjustNutritionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adjustNutritionDTO, or with status {@code 400 (Bad Request)} if the adjustNutrition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adjust-nutritions")
    public ResponseEntity<AdjustNutritionDTO> createAdjustNutrition(@RequestBody AdjustNutritionDTO adjustNutritionDTO) throws URISyntaxException {
        log.debug("REST request to save AdjustNutrition : {}", adjustNutritionDTO);
        if (adjustNutritionDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjustNutrition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjustNutritionDTO result = adjustNutritionService.save(adjustNutritionDTO);
        return ResponseEntity.created(new URI("/api/adjust-nutritions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adjust-nutritions} : Updates an existing adjustNutrition.
     *
     * @param adjustNutritionDTO the adjustNutritionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustNutritionDTO,
     * or with status {@code 400 (Bad Request)} if the adjustNutritionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustNutritionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-nutritions")
    public ResponseEntity<AdjustNutritionDTO> updateAdjustNutrition(@RequestBody AdjustNutritionDTO adjustNutritionDTO) throws URISyntaxException {
        log.debug("REST request to update AdjustNutrition : {}", adjustNutritionDTO);
        if (adjustNutritionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustNutritionDTO result = adjustNutritionService.save(adjustNutritionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustNutritionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adjust-nutritions} : get all the adjustNutritions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustNutritions in body.
     */
    @GetMapping("/adjust-nutritions")
    public List<AdjustNutritionDTO> getAllAdjustNutritions() {
        log.debug("REST request to get all AdjustNutritions");
        return adjustNutritionService.findAll();
    }

    /**
     * {@code GET  /adjust-nutritions/:id} : get the "id" adjustNutrition.
     *
     * @param id the id of the adjustNutritionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adjustNutritionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adjust-nutritions/{id}")
    public ResponseEntity<AdjustNutritionDTO> getAdjustNutrition(@PathVariable Long id) {
        log.debug("REST request to get AdjustNutrition : {}", id);
        Optional<AdjustNutritionDTO> adjustNutritionDTO = adjustNutritionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjustNutritionDTO);
    }

    /**
     * {@code DELETE  /adjust-nutritions/:id} : delete the "id" adjustNutrition.
     *
     * @param id the id of the adjustNutritionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adjust-nutritions/{id}")
    public ResponseEntity<Void> deleteAdjustNutrition(@PathVariable Long id) {
        log.debug("REST request to delete AdjustNutrition : {}", id);
        adjustNutritionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
