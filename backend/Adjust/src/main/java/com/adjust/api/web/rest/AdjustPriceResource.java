package com.adjust.api.web.rest;

import com.adjust.api.service.AdjustPriceService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.AdjustPriceDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.AdjustPrice}.
 */
@RestController
@RequestMapping("/api")
public class AdjustPriceResource {

    private final Logger log = LoggerFactory.getLogger(AdjustPriceResource.class);

    private static final String ENTITY_NAME = "adjustPrice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdjustPriceService adjustPriceService;

    public AdjustPriceResource(AdjustPriceService adjustPriceService) {
        this.adjustPriceService = adjustPriceService;
    }

    /**
     * {@code POST  /adjust-prices} : Create a new adjustPrice.
     *
     * @param adjustPriceDTO the adjustPriceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adjustPriceDTO, or with status {@code 400 (Bad Request)} if the adjustPrice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adjust-prices")
    public ResponseEntity<AdjustPriceDTO> createAdjustPrice(@RequestBody AdjustPriceDTO adjustPriceDTO) throws URISyntaxException {
        log.debug("REST request to save AdjustPrice : {}", adjustPriceDTO);
        if (adjustPriceDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjustPrice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjustPriceDTO result = adjustPriceService.save(adjustPriceDTO);
        return ResponseEntity.created(new URI("/api/adjust-prices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adjust-prices} : Updates an existing adjustPrice.
     *
     * @param adjustPriceDTO the adjustPriceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustPriceDTO,
     * or with status {@code 400 (Bad Request)} if the adjustPriceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustPriceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-prices")
    public ResponseEntity<AdjustPriceDTO> updateAdjustPrice(@RequestBody AdjustPriceDTO adjustPriceDTO) throws URISyntaxException {
        log.debug("REST request to update AdjustPrice : {}", adjustPriceDTO);
        if (adjustPriceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustPriceDTO result = adjustPriceService.save(adjustPriceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustPriceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adjust-prices} : get all the adjustPrices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustPrices in body.
     */
    @GetMapping("/adjust-prices")
    public List<AdjustPriceDTO> getAllAdjustPrices() {
        log.debug("REST request to get all AdjustPrices");
        return adjustPriceService.findAll();
    }

    /**
     * {@code GET  /adjust-prices/:id} : get the "id" adjustPrice.
     *
     * @param id the id of the adjustPriceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adjustPriceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adjust-prices/{id}")
    public ResponseEntity<AdjustPriceDTO> getAdjustPrice(@PathVariable Long id) {
        log.debug("REST request to get AdjustPrice : {}", id);
        Optional<AdjustPriceDTO> adjustPriceDTO = adjustPriceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjustPriceDTO);
    }

    /**
     * {@code DELETE  /adjust-prices/:id} : delete the "id" adjustPrice.
     *
     * @param id the id of the adjustPriceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adjust-prices/{id}")
    public ResponseEntity<Void> deleteAdjustPrice(@PathVariable Long id) {
        log.debug("REST request to delete AdjustPrice : {}", id);
        adjustPriceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
