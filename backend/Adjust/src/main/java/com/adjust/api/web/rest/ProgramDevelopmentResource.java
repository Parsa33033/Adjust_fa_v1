package com.adjust.api.web.rest;

import com.adjust.api.service.ProgramDevelopmentService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.ProgramDevelopmentDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.ProgramDevelopment}.
 */
@RestController
@RequestMapping("/api")
public class ProgramDevelopmentResource {

    private final Logger log = LoggerFactory.getLogger(ProgramDevelopmentResource.class);

    private static final String ENTITY_NAME = "programDevelopment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProgramDevelopmentService programDevelopmentService;

    public ProgramDevelopmentResource(ProgramDevelopmentService programDevelopmentService) {
        this.programDevelopmentService = programDevelopmentService;
    }

    /**
     * {@code POST  /program-developments} : Create a new programDevelopment.
     *
     * @param programDevelopmentDTO the programDevelopmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new programDevelopmentDTO, or with status {@code 400 (Bad Request)} if the programDevelopment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/program-developments")
    public ResponseEntity<ProgramDevelopmentDTO> createProgramDevelopment(@RequestBody ProgramDevelopmentDTO programDevelopmentDTO) throws URISyntaxException {
        log.debug("REST request to save ProgramDevelopment : {}", programDevelopmentDTO);
        if (programDevelopmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new programDevelopment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProgramDevelopmentDTO result = programDevelopmentService.save(programDevelopmentDTO);
        return ResponseEntity.created(new URI("/api/program-developments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /program-developments} : Updates an existing programDevelopment.
     *
     * @param programDevelopmentDTO the programDevelopmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated programDevelopmentDTO,
     * or with status {@code 400 (Bad Request)} if the programDevelopmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the programDevelopmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/program-developments")
    public ResponseEntity<ProgramDevelopmentDTO> updateProgramDevelopment(@RequestBody ProgramDevelopmentDTO programDevelopmentDTO) throws URISyntaxException {
        log.debug("REST request to update ProgramDevelopment : {}", programDevelopmentDTO);
        if (programDevelopmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProgramDevelopmentDTO result = programDevelopmentService.save(programDevelopmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, programDevelopmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /program-developments} : get all the programDevelopments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of programDevelopments in body.
     */
    @GetMapping("/program-developments")
    public List<ProgramDevelopmentDTO> getAllProgramDevelopments() {
        log.debug("REST request to get all ProgramDevelopments");
        return programDevelopmentService.findAll();
    }

    /**
     * {@code GET  /program-developments/:id} : get the "id" programDevelopment.
     *
     * @param id the id of the programDevelopmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the programDevelopmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/program-developments/{id}")
    public ResponseEntity<ProgramDevelopmentDTO> getProgramDevelopment(@PathVariable Long id) {
        log.debug("REST request to get ProgramDevelopment : {}", id);
        Optional<ProgramDevelopmentDTO> programDevelopmentDTO = programDevelopmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(programDevelopmentDTO);
    }

    /**
     * {@code DELETE  /program-developments/:id} : delete the "id" programDevelopment.
     *
     * @param id the id of the programDevelopmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/program-developments/{id}")
    public ResponseEntity<Void> deleteProgramDevelopment(@PathVariable Long id) {
        log.debug("REST request to delete ProgramDevelopment : {}", id);
        programDevelopmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
