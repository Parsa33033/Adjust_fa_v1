package com.adjust.api.web.rest;

import com.adjust.api.service.NutritionService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.NutritionDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.Nutrition}.
 */
@RestController
@RequestMapping("/api")
public class NutritionResource {

    private final Logger log = LoggerFactory.getLogger(NutritionResource.class);

    private static final String ENTITY_NAME = "nutrition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NutritionService nutritionService;

    public NutritionResource(NutritionService nutritionService) {
        this.nutritionService = nutritionService;
    }

    /**
     * {@code POST  /nutritions} : Create a new nutrition.
     *
     * @param nutritionDTO the nutritionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nutritionDTO, or with status {@code 400 (Bad Request)} if the nutrition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nutritions")
    public ResponseEntity<NutritionDTO> createNutrition(@RequestBody NutritionDTO nutritionDTO) throws URISyntaxException {
        log.debug("REST request to save Nutrition : {}", nutritionDTO);
        if (nutritionDTO.getId() != null) {
            throw new BadRequestAlertException("A new nutrition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NutritionDTO result = nutritionService.save(nutritionDTO);
        return ResponseEntity.created(new URI("/api/nutritions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nutritions} : Updates an existing nutrition.
     *
     * @param nutritionDTO the nutritionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nutritionDTO,
     * or with status {@code 400 (Bad Request)} if the nutritionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nutritionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nutritions")
    public ResponseEntity<NutritionDTO> updateNutrition(@RequestBody NutritionDTO nutritionDTO) throws URISyntaxException {
        log.debug("REST request to update Nutrition : {}", nutritionDTO);
        if (nutritionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NutritionDTO result = nutritionService.save(nutritionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nutritionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nutritions} : get all the nutritions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nutritions in body.
     */
    @GetMapping("/nutritions")
    public List<NutritionDTO> getAllNutritions() {
        log.debug("REST request to get all Nutritions");
        return nutritionService.findAll();
    }

    /**
     * {@code GET  /nutritions/:id} : get the "id" nutrition.
     *
     * @param id the id of the nutritionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nutritionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nutritions/{id}")
    public ResponseEntity<NutritionDTO> getNutrition(@PathVariable Long id) {
        log.debug("REST request to get Nutrition : {}", id);
        Optional<NutritionDTO> nutritionDTO = nutritionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nutritionDTO);
    }

    /**
     * {@code DELETE  /nutritions/:id} : delete the "id" nutrition.
     *
     * @param id the id of the nutritionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nutritions/{id}")
    public ResponseEntity<Void> deleteNutrition(@PathVariable Long id) {
        log.debug("REST request to delete Nutrition : {}", id);
        nutritionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
