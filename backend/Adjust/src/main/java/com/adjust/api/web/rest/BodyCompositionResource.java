package com.adjust.api.web.rest;

import com.adjust.api.service.BodyCompositionService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.BodyCompositionDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.BodyComposition}.
 */
@RestController
@RequestMapping("/api")
public class BodyCompositionResource {

    private final Logger log = LoggerFactory.getLogger(BodyCompositionResource.class);

    private static final String ENTITY_NAME = "bodyComposition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BodyCompositionService bodyCompositionService;

    public BodyCompositionResource(BodyCompositionService bodyCompositionService) {
        this.bodyCompositionService = bodyCompositionService;
    }

    /**
     * {@code POST  /body-compositions} : Create a new bodyComposition.
     *
     * @param bodyCompositionDTO the bodyCompositionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bodyCompositionDTO, or with status {@code 400 (Bad Request)} if the bodyComposition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/body-compositions")
    public ResponseEntity<BodyCompositionDTO> createBodyComposition(@RequestBody BodyCompositionDTO bodyCompositionDTO) throws URISyntaxException {
        log.debug("REST request to save BodyComposition : {}", bodyCompositionDTO);
        if (bodyCompositionDTO.getId() != null) {
            throw new BadRequestAlertException("A new bodyComposition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BodyCompositionDTO result = bodyCompositionService.save(bodyCompositionDTO);
        return ResponseEntity.created(new URI("/api/body-compositions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /body-compositions} : Updates an existing bodyComposition.
     *
     * @param bodyCompositionDTO the bodyCompositionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bodyCompositionDTO,
     * or with status {@code 400 (Bad Request)} if the bodyCompositionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bodyCompositionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/body-compositions")
    public ResponseEntity<BodyCompositionDTO> updateBodyComposition(@RequestBody BodyCompositionDTO bodyCompositionDTO) throws URISyntaxException {
        log.debug("REST request to update BodyComposition : {}", bodyCompositionDTO);
        if (bodyCompositionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BodyCompositionDTO result = bodyCompositionService.save(bodyCompositionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bodyCompositionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /body-compositions} : get all the bodyCompositions.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bodyCompositions in body.
     */
    @GetMapping("/body-compositions")
    public List<BodyCompositionDTO> getAllBodyCompositions(@RequestParam(required = false) String filter) {
        if ("program-is-null".equals(filter)) {
            log.debug("REST request to get all BodyCompositions where program is null");
            return bodyCompositionService.findAllWhereProgramIsNull();
        }
        log.debug("REST request to get all BodyCompositions");
        return bodyCompositionService.findAll();
    }

    /**
     * {@code GET  /body-compositions/:id} : get the "id" bodyComposition.
     *
     * @param id the id of the bodyCompositionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bodyCompositionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/body-compositions/{id}")
    public ResponseEntity<BodyCompositionDTO> getBodyComposition(@PathVariable Long id) {
        log.debug("REST request to get BodyComposition : {}", id);
        Optional<BodyCompositionDTO> bodyCompositionDTO = bodyCompositionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bodyCompositionDTO);
    }

    /**
     * {@code DELETE  /body-compositions/:id} : delete the "id" bodyComposition.
     *
     * @param id the id of the bodyCompositionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/body-compositions/{id}")
    public ResponseEntity<Void> deleteBodyComposition(@PathVariable Long id) {
        log.debug("REST request to delete BodyComposition : {}", id);
        bodyCompositionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
