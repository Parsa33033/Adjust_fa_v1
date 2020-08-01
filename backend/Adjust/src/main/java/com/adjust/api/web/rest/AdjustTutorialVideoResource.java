package com.adjust.api.web.rest;

import com.adjust.api.service.AdjustTutorialVideoService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.AdjustTutorialVideoDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.AdjustTutorialVideo}.
 */
@RestController
@RequestMapping("/api")
public class AdjustTutorialVideoResource {

    private final Logger log = LoggerFactory.getLogger(AdjustTutorialVideoResource.class);

    private static final String ENTITY_NAME = "adjustTutorialVideo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdjustTutorialVideoService adjustTutorialVideoService;

    public AdjustTutorialVideoResource(AdjustTutorialVideoService adjustTutorialVideoService) {
        this.adjustTutorialVideoService = adjustTutorialVideoService;
    }

    /**
     * {@code POST  /adjust-tutorial-videos} : Create a new adjustTutorialVideo.
     *
     * @param adjustTutorialVideoDTO the adjustTutorialVideoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adjustTutorialVideoDTO, or with status {@code 400 (Bad Request)} if the adjustTutorialVideo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adjust-tutorial-videos")
    public ResponseEntity<AdjustTutorialVideoDTO> createAdjustTutorialVideo(@RequestBody AdjustTutorialVideoDTO adjustTutorialVideoDTO) throws URISyntaxException {
        log.debug("REST request to save AdjustTutorialVideo : {}", adjustTutorialVideoDTO);
        if (adjustTutorialVideoDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjustTutorialVideo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjustTutorialVideoDTO result = adjustTutorialVideoService.save(adjustTutorialVideoDTO);
        return ResponseEntity.created(new URI("/api/adjust-tutorial-videos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adjust-tutorial-videos} : Updates an existing adjustTutorialVideo.
     *
     * @param adjustTutorialVideoDTO the adjustTutorialVideoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustTutorialVideoDTO,
     * or with status {@code 400 (Bad Request)} if the adjustTutorialVideoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustTutorialVideoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-tutorial-videos")
    public ResponseEntity<AdjustTutorialVideoDTO> updateAdjustTutorialVideo(@RequestBody AdjustTutorialVideoDTO adjustTutorialVideoDTO) throws URISyntaxException {
        log.debug("REST request to update AdjustTutorialVideo : {}", adjustTutorialVideoDTO);
        if (adjustTutorialVideoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustTutorialVideoDTO result = adjustTutorialVideoService.save(adjustTutorialVideoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustTutorialVideoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adjust-tutorial-videos} : get all the adjustTutorialVideos.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustTutorialVideos in body.
     */
    @GetMapping("/adjust-tutorial-videos")
    public List<AdjustTutorialVideoDTO> getAllAdjustTutorialVideos(@RequestParam(required = false) String filter) {
        if ("tutorial-is-null".equals(filter)) {
            log.debug("REST request to get all AdjustTutorialVideos where tutorial is null");
            return adjustTutorialVideoService.findAllWhereTutorialIsNull();
        }
        log.debug("REST request to get all AdjustTutorialVideos");
        return adjustTutorialVideoService.findAll();
    }

    /**
     * {@code GET  /adjust-tutorial-videos/:id} : get the "id" adjustTutorialVideo.
     *
     * @param id the id of the adjustTutorialVideoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adjustTutorialVideoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adjust-tutorial-videos/{id}")
    public ResponseEntity<AdjustTutorialVideoDTO> getAdjustTutorialVideo(@PathVariable Long id) {
        log.debug("REST request to get AdjustTutorialVideo : {}", id);
        Optional<AdjustTutorialVideoDTO> adjustTutorialVideoDTO = adjustTutorialVideoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjustTutorialVideoDTO);
    }

    /**
     * {@code DELETE  /adjust-tutorial-videos/:id} : delete the "id" adjustTutorialVideo.
     *
     * @param id the id of the adjustTutorialVideoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adjust-tutorial-videos/{id}")
    public ResponseEntity<Void> deleteAdjustTutorialVideo(@PathVariable Long id) {
        log.debug("REST request to delete AdjustTutorialVideo : {}", id);
        adjustTutorialVideoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
