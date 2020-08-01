package com.adjust.api.web.rest;

import com.adjust.api.service.AdjustClientService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.AdjustClientDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.AdjustClient}.
 */
@RestController
@RequestMapping("/api")
public class AdjustClientResource {

    private final Logger log = LoggerFactory.getLogger(AdjustClientResource.class);

    private static final String ENTITY_NAME = "adjustClient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdjustClientService adjustClientService;

    public AdjustClientResource(AdjustClientService adjustClientService) {
        this.adjustClientService = adjustClientService;
    }

    /**
     * {@code POST  /adjust-clients} : Create a new adjustClient.
     *
     * @param adjustClientDTO the adjustClientDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adjustClientDTO, or with status {@code 400 (Bad Request)} if the adjustClient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adjust-clients")
    public ResponseEntity<AdjustClientDTO> createAdjustClient(@RequestBody AdjustClientDTO adjustClientDTO) throws URISyntaxException {
        log.debug("REST request to save AdjustClient : {}", adjustClientDTO);
        if (adjustClientDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjustClient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjustClientDTO result = adjustClientService.save(adjustClientDTO);
        return ResponseEntity.created(new URI("/api/adjust-clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adjust-clients} : Updates an existing adjustClient.
     *
     * @param adjustClientDTO the adjustClientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustClientDTO,
     * or with status {@code 400 (Bad Request)} if the adjustClientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustClientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-clients")
    public ResponseEntity<AdjustClientDTO> updateAdjustClient(@RequestBody AdjustClientDTO adjustClientDTO) throws URISyntaxException {
        log.debug("REST request to update AdjustClient : {}", adjustClientDTO);
        if (adjustClientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustClientDTO result = adjustClientService.save(adjustClientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustClientDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adjust-clients} : get all the adjustClients.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustClients in body.
     */
    @GetMapping("/adjust-clients")
    public List<AdjustClientDTO> getAllAdjustClients(@RequestParam(required = false) String filter) {
        if ("conversation-is-null".equals(filter)) {
            log.debug("REST request to get all AdjustClients where conversation is null");
            return adjustClientService.findAllWhereConversationIsNull();
        }
        log.debug("REST request to get all AdjustClients");
        return adjustClientService.findAll();
    }

    /**
     * {@code GET  /adjust-clients/:id} : get the "id" adjustClient.
     *
     * @param id the id of the adjustClientDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adjustClientDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adjust-clients/{id}")
    public ResponseEntity<AdjustClientDTO> getAdjustClient(@PathVariable Long id) {
        log.debug("REST request to get AdjustClient : {}", id);
        Optional<AdjustClientDTO> adjustClientDTO = adjustClientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjustClientDTO);
    }

    /**
     * {@code DELETE  /adjust-clients/:id} : delete the "id" adjustClient.
     *
     * @param id the id of the adjustClientDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adjust-clients/{id}")
    public ResponseEntity<Void> deleteAdjustClient(@PathVariable Long id) {
        log.debug("REST request to delete AdjustClient : {}", id);
        adjustClientService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
