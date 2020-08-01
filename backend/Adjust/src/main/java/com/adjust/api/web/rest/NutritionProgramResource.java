package com.adjust.api.web.rest;

import com.adjust.api.service.NutritionProgramService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.NutritionProgramDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.adjust.api.domain.NutritionProgram}.
 */
@RestController
@RequestMapping("/api")
public class NutritionProgramResource {

    private final Logger log = LoggerFactory.getLogger(NutritionProgramResource.class);

    private static final String ENTITY_NAME = "nutritionProgram";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NutritionProgramService nutritionProgramService;

    public NutritionProgramResource(NutritionProgramService nutritionProgramService) {
        this.nutritionProgramService = nutritionProgramService;
    }

    /**
     * {@code POST  /nutrition-programs} : Create a new nutritionProgram.
     *
     * @param nutritionProgramDTO the nutritionProgramDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nutritionProgramDTO, or with status {@code 400 (Bad Request)} if the nutritionProgram has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nutrition-programs")
    public ResponseEntity<NutritionProgramDTO> createNutritionProgram(@RequestBody NutritionProgramDTO nutritionProgramDTO) throws URISyntaxException {
        log.debug("REST request to save NutritionProgram : {}", nutritionProgramDTO);
        if (nutritionProgramDTO.getId() != null) {
            throw new BadRequestAlertException("A new nutritionProgram cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NutritionProgramDTO result = nutritionProgramService.save(nutritionProgramDTO);
        return ResponseEntity.created(new URI("/api/nutrition-programs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nutrition-programs} : Updates an existing nutritionProgram.
     *
     * @param nutritionProgramDTO the nutritionProgramDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nutritionProgramDTO,
     * or with status {@code 400 (Bad Request)} if the nutritionProgramDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nutritionProgramDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nutrition-programs")
    public ResponseEntity<NutritionProgramDTO> updateNutritionProgram(@RequestBody NutritionProgramDTO nutritionProgramDTO) throws URISyntaxException {
        log.debug("REST request to update NutritionProgram : {}", nutritionProgramDTO);
        if (nutritionProgramDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NutritionProgramDTO result = nutritionProgramService.save(nutritionProgramDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nutritionProgramDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nutrition-programs} : get all the nutritionPrograms.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nutritionPrograms in body.
     */
    @GetMapping("/nutrition-programs")
    public List<NutritionProgramDTO> getAllNutritionPrograms(@RequestParam(required = false) String filter) {
        if ("program-is-null".equals(filter)) {
            log.debug("REST request to get all NutritionPrograms where program is null");
            return nutritionProgramService.findAllWhereProgramIsNull();
        }
        log.debug("REST request to get all NutritionPrograms");
        return nutritionProgramService.findAll();
    }

    /**
     * {@code GET  /nutrition-programs/:id} : get the "id" nutritionProgram.
     *
     * @param id the id of the nutritionProgramDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nutritionProgramDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nutrition-programs/{id}")
    public ResponseEntity<NutritionProgramDTO> getNutritionProgram(@PathVariable Long id) {
        log.debug("REST request to get NutritionProgram : {}", id);
        Optional<NutritionProgramDTO> nutritionProgramDTO = nutritionProgramService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nutritionProgramDTO);
    }

    /**
     * {@code DELETE  /nutrition-programs/:id} : delete the "id" nutritionProgram.
     *
     * @param id the id of the nutritionProgramDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nutrition-programs/{id}")
    public ResponseEntity<Void> deleteNutritionProgram(@PathVariable Long id) {
        log.debug("REST request to delete NutritionProgram : {}", id);
        nutritionProgramService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
