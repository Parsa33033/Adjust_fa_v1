package com.adjust.api.web.rest;

import com.adjust.api.service.AdjustProgramService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.AdjustProgramDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.AdjustProgram}.
 */
@RestController
@RequestMapping("/api")
public class AdjustProgramResource {

    private final Logger log = LoggerFactory.getLogger(AdjustProgramResource.class);

    private static final String ENTITY_NAME = "adjustProgram";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdjustProgramService adjustProgramService;

    public AdjustProgramResource(AdjustProgramService adjustProgramService) {
        this.adjustProgramService = adjustProgramService;
    }

    /**
     * {@code POST  /adjust-programs} : Create a new adjustProgram.
     *
     * @param adjustProgramDTO the adjustProgramDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adjustProgramDTO, or with status {@code 400 (Bad Request)} if the adjustProgram has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adjust-programs")
    public ResponseEntity<AdjustProgramDTO> createAdjustProgram(@RequestBody AdjustProgramDTO adjustProgramDTO) throws URISyntaxException {
        log.debug("REST request to save AdjustProgram : {}", adjustProgramDTO);
        if (adjustProgramDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjustProgram cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjustProgramDTO result = adjustProgramService.save(adjustProgramDTO);
        return ResponseEntity.created(new URI("/api/adjust-programs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adjust-programs} : Updates an existing adjustProgram.
     *
     * @param adjustProgramDTO the adjustProgramDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustProgramDTO,
     * or with status {@code 400 (Bad Request)} if the adjustProgramDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustProgramDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-programs")
    public ResponseEntity<AdjustProgramDTO> updateAdjustProgram(@RequestBody AdjustProgramDTO adjustProgramDTO) throws URISyntaxException {
        log.debug("REST request to update AdjustProgram : {}", adjustProgramDTO);
        if (adjustProgramDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustProgramDTO result = adjustProgramService.save(adjustProgramDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustProgramDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adjust-programs} : get all the adjustPrograms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustPrograms in body.
     */
    @GetMapping("/adjust-programs")
    public List<AdjustProgramDTO> getAllAdjustPrograms() {
        log.debug("REST request to get all AdjustPrograms");
        return adjustProgramService.findAll();
    }

    /**
     * {@code GET  /adjust-programs/:id} : get the "id" adjustProgram.
     *
     * @param id the id of the adjustProgramDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adjustProgramDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adjust-programs/{id}")
    public ResponseEntity<AdjustProgramDTO> getAdjustProgram(@PathVariable Long id) {
        log.debug("REST request to get AdjustProgram : {}", id);
        Optional<AdjustProgramDTO> adjustProgramDTO = adjustProgramService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjustProgramDTO);
    }

    /**
     * {@code DELETE  /adjust-programs/:id} : delete the "id" adjustProgram.
     *
     * @param id the id of the adjustProgramDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adjust-programs/{id}")
    public ResponseEntity<Void> deleteAdjustProgram(@PathVariable Long id) {
        log.debug("REST request to delete AdjustProgram : {}", id);
        adjustProgramService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
