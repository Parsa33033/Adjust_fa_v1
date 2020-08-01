package com.adjust.api.web.rest;

import com.adjust.api.service.FitnessProgramService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.FitnessProgramDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.FitnessProgram}.
 */
@RestController
@RequestMapping("/api")
public class FitnessProgramResource {

    private final Logger log = LoggerFactory.getLogger(FitnessProgramResource.class);

    private static final String ENTITY_NAME = "fitnessProgram";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FitnessProgramService fitnessProgramService;

    public FitnessProgramResource(FitnessProgramService fitnessProgramService) {
        this.fitnessProgramService = fitnessProgramService;
    }

    /**
     * {@code POST  /fitness-programs} : Create a new fitnessProgram.
     *
     * @param fitnessProgramDTO the fitnessProgramDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fitnessProgramDTO, or with status {@code 400 (Bad Request)} if the fitnessProgram has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fitness-programs")
    public ResponseEntity<FitnessProgramDTO> createFitnessProgram(@RequestBody FitnessProgramDTO fitnessProgramDTO) throws URISyntaxException {
        log.debug("REST request to save FitnessProgram : {}", fitnessProgramDTO);
        if (fitnessProgramDTO.getId() != null) {
            throw new BadRequestAlertException("A new fitnessProgram cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FitnessProgramDTO result = fitnessProgramService.save(fitnessProgramDTO);
        return ResponseEntity.created(new URI("/api/fitness-programs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fitness-programs} : Updates an existing fitnessProgram.
     *
     * @param fitnessProgramDTO the fitnessProgramDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fitnessProgramDTO,
     * or with status {@code 400 (Bad Request)} if the fitnessProgramDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fitnessProgramDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fitness-programs")
    public ResponseEntity<FitnessProgramDTO> updateFitnessProgram(@RequestBody FitnessProgramDTO fitnessProgramDTO) throws URISyntaxException {
        log.debug("REST request to update FitnessProgram : {}", fitnessProgramDTO);
        if (fitnessProgramDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FitnessProgramDTO result = fitnessProgramService.save(fitnessProgramDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fitnessProgramDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fitness-programs} : get all the fitnessPrograms.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fitnessPrograms in body.
     */
    @GetMapping("/fitness-programs")
    public List<FitnessProgramDTO> getAllFitnessPrograms(@RequestParam(required = false) String filter) {
        if ("program-is-null".equals(filter)) {
            log.debug("REST request to get all FitnessPrograms where program is null");
            return fitnessProgramService.findAllWhereProgramIsNull();
        }
        log.debug("REST request to get all FitnessPrograms");
        return fitnessProgramService.findAll();
    }

    /**
     * {@code GET  /fitness-programs/:id} : get the "id" fitnessProgram.
     *
     * @param id the id of the fitnessProgramDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fitnessProgramDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fitness-programs/{id}")
    public ResponseEntity<FitnessProgramDTO> getFitnessProgram(@PathVariable Long id) {
        log.debug("REST request to get FitnessProgram : {}", id);
        Optional<FitnessProgramDTO> fitnessProgramDTO = fitnessProgramService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fitnessProgramDTO);
    }

    /**
     * {@code DELETE  /fitness-programs/:id} : delete the "id" fitnessProgram.
     *
     * @param id the id of the fitnessProgramDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fitness-programs/{id}")
    public ResponseEntity<Void> deleteFitnessProgram(@PathVariable Long id) {
        log.debug("REST request to delete FitnessProgram : {}", id);
        fitnessProgramService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
