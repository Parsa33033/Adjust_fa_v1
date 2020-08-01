package com.adjust.api.web.rest;

import com.adjust.api.service.AdjustTutorialService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.AdjustTutorialDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.AdjustTutorial}.
 */
@RestController
@RequestMapping("/api")
public class AdjustTutorialResource {

    private final Logger log = LoggerFactory.getLogger(AdjustTutorialResource.class);

    private static final String ENTITY_NAME = "adjustTutorial";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdjustTutorialService adjustTutorialService;

    public AdjustTutorialResource(AdjustTutorialService adjustTutorialService) {
        this.adjustTutorialService = adjustTutorialService;
    }

    /**
     * {@code POST  /adjust-tutorials} : Create a new adjustTutorial.
     *
     * @param adjustTutorialDTO the adjustTutorialDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adjustTutorialDTO, or with status {@code 400 (Bad Request)} if the adjustTutorial has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adjust-tutorials")
    public ResponseEntity<AdjustTutorialDTO> createAdjustTutorial(@RequestBody AdjustTutorialDTO adjustTutorialDTO) throws URISyntaxException {
        log.debug("REST request to save AdjustTutorial : {}", adjustTutorialDTO);
        if (adjustTutorialDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjustTutorial cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjustTutorialDTO result = adjustTutorialService.save(adjustTutorialDTO);
        return ResponseEntity.created(new URI("/api/adjust-tutorials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adjust-tutorials} : Updates an existing adjustTutorial.
     *
     * @param adjustTutorialDTO the adjustTutorialDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustTutorialDTO,
     * or with status {@code 400 (Bad Request)} if the adjustTutorialDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustTutorialDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-tutorials")
    public ResponseEntity<AdjustTutorialDTO> updateAdjustTutorial(@RequestBody AdjustTutorialDTO adjustTutorialDTO) throws URISyntaxException {
        log.debug("REST request to update AdjustTutorial : {}", adjustTutorialDTO);
        if (adjustTutorialDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustTutorialDTO result = adjustTutorialService.save(adjustTutorialDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustTutorialDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adjust-tutorials} : get all the adjustTutorials.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustTutorials in body.
     */
    @GetMapping("/adjust-tutorials")
    public List<AdjustTutorialDTO> getAllAdjustTutorials() {
        log.debug("REST request to get all AdjustTutorials");
        return adjustTutorialService.findAll();
    }

    /**
     * {@code GET  /adjust-tutorials/:id} : get the "id" adjustTutorial.
     *
     * @param id the id of the adjustTutorialDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adjustTutorialDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adjust-tutorials/{id}")
    public ResponseEntity<AdjustTutorialDTO> getAdjustTutorial(@PathVariable Long id) {
        log.debug("REST request to get AdjustTutorial : {}", id);
        Optional<AdjustTutorialDTO> adjustTutorialDTO = adjustTutorialService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjustTutorialDTO);
    }

    /**
     * {@code DELETE  /adjust-tutorials/:id} : delete the "id" adjustTutorial.
     *
     * @param id the id of the adjustTutorialDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adjust-tutorials/{id}")
    public ResponseEntity<Void> deleteAdjustTutorial(@PathVariable Long id) {
        log.debug("REST request to delete AdjustTutorial : {}", id);
        adjustTutorialService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
