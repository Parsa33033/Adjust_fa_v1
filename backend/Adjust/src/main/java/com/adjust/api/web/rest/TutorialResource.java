package com.adjust.api.web.rest;

import com.adjust.api.service.TutorialService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.TutorialDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.Tutorial}.
 */
@RestController
@RequestMapping("/api")
public class TutorialResource {

    private final Logger log = LoggerFactory.getLogger(TutorialResource.class);

    private static final String ENTITY_NAME = "tutorial";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TutorialService tutorialService;

    public TutorialResource(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    /**
     * {@code POST  /tutorials} : Create a new tutorial.
     *
     * @param tutorialDTO the tutorialDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tutorialDTO, or with status {@code 400 (Bad Request)} if the tutorial has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tutorials")
    public ResponseEntity<TutorialDTO> createTutorial(@RequestBody TutorialDTO tutorialDTO) throws URISyntaxException {
        log.debug("REST request to save Tutorial : {}", tutorialDTO);
        if (tutorialDTO.getId() != null) {
            throw new BadRequestAlertException("A new tutorial cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TutorialDTO result = tutorialService.save(tutorialDTO);
        return ResponseEntity.created(new URI("/api/tutorials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tutorials} : Updates an existing tutorial.
     *
     * @param tutorialDTO the tutorialDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tutorialDTO,
     * or with status {@code 400 (Bad Request)} if the tutorialDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tutorialDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tutorials")
    public ResponseEntity<TutorialDTO> updateTutorial(@RequestBody TutorialDTO tutorialDTO) throws URISyntaxException {
        log.debug("REST request to update Tutorial : {}", tutorialDTO);
        if (tutorialDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TutorialDTO result = tutorialService.save(tutorialDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tutorialDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tutorials} : get all the tutorials.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tutorials in body.
     */
    @GetMapping("/tutorials")
    public List<TutorialDTO> getAllTutorials() {
        log.debug("REST request to get all Tutorials");
        return tutorialService.findAll();
    }

    /**
     * {@code GET  /tutorials/:id} : get the "id" tutorial.
     *
     * @param id the id of the tutorialDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tutorialDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<TutorialDTO> getTutorial(@PathVariable Long id) {
        log.debug("REST request to get Tutorial : {}", id);
        Optional<TutorialDTO> tutorialDTO = tutorialService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tutorialDTO);
    }

    /**
     * {@code DELETE  /tutorials/:id} : delete the "id" tutorial.
     *
     * @param id the id of the tutorialDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<Void> deleteTutorial(@PathVariable Long id) {
        log.debug("REST request to delete Tutorial : {}", id);
        tutorialService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
