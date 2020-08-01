package com.adjust.api.web.rest;

import com.adjust.api.service.AdjustTokensService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.AdjustTokensDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.AdjustTokens}.
 */
@RestController
@RequestMapping("/api")
public class AdjustTokensResource {

    private final Logger log = LoggerFactory.getLogger(AdjustTokensResource.class);

    private static final String ENTITY_NAME = "adjustTokens";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdjustTokensService adjustTokensService;

    public AdjustTokensResource(AdjustTokensService adjustTokensService) {
        this.adjustTokensService = adjustTokensService;
    }

    /**
     * {@code POST  /adjust-tokens} : Create a new adjustTokens.
     *
     * @param adjustTokensDTO the adjustTokensDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adjustTokensDTO, or with status {@code 400 (Bad Request)} if the adjustTokens has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adjust-tokens")
    public ResponseEntity<AdjustTokensDTO> createAdjustTokens(@RequestBody AdjustTokensDTO adjustTokensDTO) throws URISyntaxException {
        log.debug("REST request to save AdjustTokens : {}", adjustTokensDTO);
        if (adjustTokensDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjustTokens cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjustTokensDTO result = adjustTokensService.save(adjustTokensDTO);
        return ResponseEntity.created(new URI("/api/adjust-tokens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adjust-tokens} : Updates an existing adjustTokens.
     *
     * @param adjustTokensDTO the adjustTokensDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustTokensDTO,
     * or with status {@code 400 (Bad Request)} if the adjustTokensDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustTokensDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-tokens")
    public ResponseEntity<AdjustTokensDTO> updateAdjustTokens(@RequestBody AdjustTokensDTO adjustTokensDTO) throws URISyntaxException {
        log.debug("REST request to update AdjustTokens : {}", adjustTokensDTO);
        if (adjustTokensDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustTokensDTO result = adjustTokensService.save(adjustTokensDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustTokensDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adjust-tokens} : get all the adjustTokens.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustTokens in body.
     */
    @GetMapping("/adjust-tokens")
    public List<AdjustTokensDTO> getAllAdjustTokens() {
        log.debug("REST request to get all AdjustTokens");
        return adjustTokensService.findAll();
    }

    /**
     * {@code GET  /adjust-tokens/:id} : get the "id" adjustTokens.
     *
     * @param id the id of the adjustTokensDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adjustTokensDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adjust-tokens/{id}")
    public ResponseEntity<AdjustTokensDTO> getAdjustTokens(@PathVariable Long id) {
        log.debug("REST request to get AdjustTokens : {}", id);
        Optional<AdjustTokensDTO> adjustTokensDTO = adjustTokensService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjustTokensDTO);
    }

    /**
     * {@code DELETE  /adjust-tokens/:id} : delete the "id" adjustTokens.
     *
     * @param id the id of the adjustTokensDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adjust-tokens/{id}")
    public ResponseEntity<Void> deleteAdjustTokens(@PathVariable Long id) {
        log.debug("REST request to delete AdjustTokens : {}", id);
        adjustTokensService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
