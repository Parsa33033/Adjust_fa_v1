package com.adjust.api.web.rest;

import com.adjust.api.service.SpecialistService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.SpecialistDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.Specialist}.
 */
@RestController
@RequestMapping("/api")
public class SpecialistResource {

    private final Logger log = LoggerFactory.getLogger(SpecialistResource.class);

    private static final String ENTITY_NAME = "specialist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpecialistService specialistService;

    public SpecialistResource(SpecialistService specialistService) {
        this.specialistService = specialistService;
    }

    /**
     * {@code POST  /specialists} : Create a new specialist.
     *
     * @param specialistDTO the specialistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specialistDTO, or with status {@code 400 (Bad Request)} if the specialist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/specialists")
    public ResponseEntity<SpecialistDTO> createSpecialist(@RequestBody SpecialistDTO specialistDTO) throws URISyntaxException {
        log.debug("REST request to save Specialist : {}", specialistDTO);
        if (specialistDTO.getId() != null) {
            throw new BadRequestAlertException("A new specialist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpecialistDTO result = specialistService.save(specialistDTO);
        return ResponseEntity.created(new URI("/api/specialists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /specialists} : Updates an existing specialist.
     *
     * @param specialistDTO the specialistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specialistDTO,
     * or with status {@code 400 (Bad Request)} if the specialistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specialistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specialists")
    public ResponseEntity<SpecialistDTO> updateSpecialist(@RequestBody SpecialistDTO specialistDTO) throws URISyntaxException {
        log.debug("REST request to update Specialist : {}", specialistDTO);
        if (specialistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpecialistDTO result = specialistService.save(specialistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specialistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /specialists} : get all the specialists.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specialists in body.
     */
    @GetMapping("/specialists")
    public List<SpecialistDTO> getAllSpecialists(@RequestParam(required = false) String filter) {
        if ("conversation-is-null".equals(filter)) {
            log.debug("REST request to get all Specialists where conversation is null");
            return specialistService.findAllWhereConversationIsNull();
        }
        log.debug("REST request to get all Specialists");
        return specialistService.findAll();
    }

    /**
     * {@code GET  /specialists/:id} : get the "id" specialist.
     *
     * @param id the id of the specialistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specialistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/specialists/{id}")
    public ResponseEntity<SpecialistDTO> getSpecialist(@PathVariable Long id) {
        log.debug("REST request to get Specialist : {}", id);
        Optional<SpecialistDTO> specialistDTO = specialistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(specialistDTO);
    }

    /**
     * {@code DELETE  /specialists/:id} : delete the "id" specialist.
     *
     * @param id the id of the specialistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/specialists/{id}")
    public ResponseEntity<Void> deleteSpecialist(@PathVariable Long id) {
        log.debug("REST request to delete Specialist : {}", id);
        specialistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
