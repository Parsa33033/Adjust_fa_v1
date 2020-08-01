package com.adjust.api.web.rest;

import com.adjust.api.service.MoveService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.MoveDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.Move}.
 */
@RestController
@RequestMapping("/api")
public class MoveResource {

    private final Logger log = LoggerFactory.getLogger(MoveResource.class);

    private static final String ENTITY_NAME = "move";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MoveService moveService;

    public MoveResource(MoveService moveService) {
        this.moveService = moveService;
    }

    /**
     * {@code POST  /moves} : Create a new move.
     *
     * @param moveDTO the moveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new moveDTO, or with status {@code 400 (Bad Request)} if the move has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/moves")
    public ResponseEntity<MoveDTO> createMove(@RequestBody MoveDTO moveDTO) throws URISyntaxException {
        log.debug("REST request to save Move : {}", moveDTO);
        if (moveDTO.getId() != null) {
            throw new BadRequestAlertException("A new move cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MoveDTO result = moveService.save(moveDTO);
        return ResponseEntity.created(new URI("/api/moves/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /moves} : Updates an existing move.
     *
     * @param moveDTO the moveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated moveDTO,
     * or with status {@code 400 (Bad Request)} if the moveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the moveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/moves")
    public ResponseEntity<MoveDTO> updateMove(@RequestBody MoveDTO moveDTO) throws URISyntaxException {
        log.debug("REST request to update Move : {}", moveDTO);
        if (moveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MoveDTO result = moveService.save(moveDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, moveDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /moves} : get all the moves.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of moves in body.
     */
    @GetMapping("/moves")
    public List<MoveDTO> getAllMoves(@RequestParam(required = false) String filter) {
        if ("exercise-is-null".equals(filter)) {
            log.debug("REST request to get all Moves where exercise is null");
            return moveService.findAllWhereExerciseIsNull();
        }
        log.debug("REST request to get all Moves");
        return moveService.findAll();
    }

    /**
     * {@code GET  /moves/:id} : get the "id" move.
     *
     * @param id the id of the moveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the moveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/moves/{id}")
    public ResponseEntity<MoveDTO> getMove(@PathVariable Long id) {
        log.debug("REST request to get Move : {}", id);
        Optional<MoveDTO> moveDTO = moveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(moveDTO);
    }

    /**
     * {@code DELETE  /moves/:id} : delete the "id" move.
     *
     * @param id the id of the moveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/moves/{id}")
    public ResponseEntity<Void> deleteMove(@PathVariable Long id) {
        log.debug("REST request to delete Move : {}", id);
        moveService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
