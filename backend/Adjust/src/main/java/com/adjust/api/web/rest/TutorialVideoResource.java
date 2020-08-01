package com.adjust.api.web.rest;

import com.adjust.api.service.TutorialVideoService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.TutorialVideoDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.TutorialVideo}.
 */
@RestController
@RequestMapping("/api")
public class TutorialVideoResource {

    private final Logger log = LoggerFactory.getLogger(TutorialVideoResource.class);

    private static final String ENTITY_NAME = "tutorialVideo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TutorialVideoService tutorialVideoService;

    public TutorialVideoResource(TutorialVideoService tutorialVideoService) {
        this.tutorialVideoService = tutorialVideoService;
    }

    /**
     * {@code POST  /tutorial-videos} : Create a new tutorialVideo.
     *
     * @param tutorialVideoDTO the tutorialVideoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tutorialVideoDTO, or with status {@code 400 (Bad Request)} if the tutorialVideo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tutorial-videos")
    public ResponseEntity<TutorialVideoDTO> createTutorialVideo(@RequestBody TutorialVideoDTO tutorialVideoDTO) throws URISyntaxException {
        log.debug("REST request to save TutorialVideo : {}", tutorialVideoDTO);
        if (tutorialVideoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tutorialVideo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TutorialVideoDTO result = tutorialVideoService.save(tutorialVideoDTO);
        return ResponseEntity.created(new URI("/api/tutorial-videos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tutorial-videos} : Updates an existing tutorialVideo.
     *
     * @param tutorialVideoDTO the tutorialVideoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tutorialVideoDTO,
     * or with status {@code 400 (Bad Request)} if the tutorialVideoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tutorialVideoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tutorial-videos")
    public ResponseEntity<TutorialVideoDTO> updateTutorialVideo(@RequestBody TutorialVideoDTO tutorialVideoDTO) throws URISyntaxException {
        log.debug("REST request to update TutorialVideo : {}", tutorialVideoDTO);
        if (tutorialVideoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TutorialVideoDTO result = tutorialVideoService.save(tutorialVideoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tutorialVideoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tutorial-videos} : get all the tutorialVideos.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tutorialVideos in body.
     */
    @GetMapping("/tutorial-videos")
    public List<TutorialVideoDTO> getAllTutorialVideos(@RequestParam(required = false) String filter) {
        if ("tutorial-is-null".equals(filter)) {
            log.debug("REST request to get all TutorialVideos where tutorial is null");
            return tutorialVideoService.findAllWhereTutorialIsNull();
        }
        log.debug("REST request to get all TutorialVideos");
        return tutorialVideoService.findAll();
    }

    /**
     * {@code GET  /tutorial-videos/:id} : get the "id" tutorialVideo.
     *
     * @param id the id of the tutorialVideoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tutorialVideoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tutorial-videos/{id}")
    public ResponseEntity<TutorialVideoDTO> getTutorialVideo(@PathVariable Long id) {
        log.debug("REST request to get TutorialVideo : {}", id);
        Optional<TutorialVideoDTO> tutorialVideoDTO = tutorialVideoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tutorialVideoDTO);
    }

    /**
     * {@code DELETE  /tutorial-videos/:id} : delete the "id" tutorialVideo.
     *
     * @param id the id of the tutorialVideoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tutorial-videos/{id}")
    public ResponseEntity<Void> deleteTutorialVideo(@PathVariable Long id) {
        log.debug("REST request to delete TutorialVideo : {}", id);
        tutorialVideoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
