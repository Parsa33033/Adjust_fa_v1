package com.adjust.api.web.rest;

import com.adjust.api.service.MealService;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.service.dto.MealDTO;

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
 * REST controller for managing {@link com.adjust.api.domain.Meal}.
 */
@RestController
@RequestMapping("/api")
public class MealResource {

    private final Logger log = LoggerFactory.getLogger(MealResource.class);

    private static final String ENTITY_NAME = "meal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MealService mealService;

    public MealResource(MealService mealService) {
        this.mealService = mealService;
    }

    /**
     * {@code POST  /meals} : Create a new meal.
     *
     * @param mealDTO the mealDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mealDTO, or with status {@code 400 (Bad Request)} if the meal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meals")
    public ResponseEntity<MealDTO> createMeal(@RequestBody MealDTO mealDTO) throws URISyntaxException {
        log.debug("REST request to save Meal : {}", mealDTO);
        if (mealDTO.getId() != null) {
            throw new BadRequestAlertException("A new meal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MealDTO result = mealService.save(mealDTO);
        return ResponseEntity.created(new URI("/api/meals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meals} : Updates an existing meal.
     *
     * @param mealDTO the mealDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mealDTO,
     * or with status {@code 400 (Bad Request)} if the mealDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mealDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meals")
    public ResponseEntity<MealDTO> updateMeal(@RequestBody MealDTO mealDTO) throws URISyntaxException {
        log.debug("REST request to update Meal : {}", mealDTO);
        if (mealDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MealDTO result = mealService.save(mealDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mealDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /meals} : get all the meals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of meals in body.
     */
    @GetMapping("/meals")
    public List<MealDTO> getAllMeals() {
        log.debug("REST request to get all Meals");
        return mealService.findAll();
    }

    /**
     * {@code GET  /meals/:id} : get the "id" meal.
     *
     * @param id the id of the mealDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mealDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meals/{id}")
    public ResponseEntity<MealDTO> getMeal(@PathVariable Long id) {
        log.debug("REST request to get Meal : {}", id);
        Optional<MealDTO> mealDTO = mealService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mealDTO);
    }

    /**
     * {@code DELETE  /meals/:id} : delete the "id" meal.
     *
     * @param id the id of the mealDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meals/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        log.debug("REST request to delete Meal : {}", id);
        mealService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
