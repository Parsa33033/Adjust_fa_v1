package com.adjust.api.web.rest;

import com.adjust.api.service.AdjustMoveService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.AdjustMoveDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.AdjustMove}.
 */
@RestController
@RequestMapping("/api")
public class AdjustMoveResource {

    private final Logger log = LoggerFactory.getLogger(AdjustMoveResource.class);

    private static final String ENTITY_NAME = "adjustMove";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdjustMoveService adjustMoveService;

    public AdjustMoveResource(AdjustMoveService adjustMoveService) {
        this.adjustMoveService = adjustMoveService;
    }

    /**
     * {@code POST  /adjust-moves} : Create a new adjustMove.
     *
     * @param adjustMoveDTO the adjustMoveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adjustMoveDTO, or with status {@code 400 (Bad Request)} if the adjustMove has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adjust-moves")
    public ResponseEntity<AdjustMoveDTO> createAdjustMove(@RequestBody AdjustMoveDTO adjustMoveDTO) throws URISyntaxException {
        log.debug("REST request to save AdjustMove : {}", adjustMoveDTO);
        if (adjustMoveDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjustMove cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjustMoveDTO result = adjustMoveService.save(adjustMoveDTO);
        return ResponseEntity.created(new URI("/api/adjust-moves/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adjust-moves} : Updates an existing adjustMove.
     *
     * @param adjustMoveDTO the adjustMoveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustMoveDTO,
     * or with status {@code 400 (Bad Request)} if the adjustMoveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustMoveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-moves")
    public ResponseEntity<AdjustMoveDTO> updateAdjustMove(@RequestBody AdjustMoveDTO adjustMoveDTO) throws URISyntaxException {
        log.debug("REST request to update AdjustMove : {}", adjustMoveDTO);
        if (adjustMoveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustMoveDTO result = adjustMoveService.save(adjustMoveDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustMoveDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adjust-moves} : get all the adjustMoves.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustMoves in body.
     */
    @GetMapping("/adjust-moves")
    public List<AdjustMoveDTO> getAllAdjustMoves() {
        log.debug("REST request to get all AdjustMoves");
        return adjustMoveService.findAll();
    }

    /**
     * {@code GET  /adjust-moves/:id} : get the "id" adjustMove.
     *
     * @param id the id of the adjustMoveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adjustMoveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adjust-moves/{id}")
    public ResponseEntity<AdjustMoveDTO> getAdjustMove(@PathVariable Long id) {
        log.debug("REST request to get AdjustMove : {}", id);
        Optional<AdjustMoveDTO> adjustMoveDTO = adjustMoveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjustMoveDTO);
    }

    /**
     * {@code DELETE  /adjust-moves/:id} : delete the "id" adjustMove.
     *
     * @param id the id of the adjustMoveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adjust-moves/{id}")
    public ResponseEntity<Void> deleteAdjustMove(@PathVariable Long id) {
        log.debug("REST request to delete AdjustMove : {}", id);
        adjustMoveService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
