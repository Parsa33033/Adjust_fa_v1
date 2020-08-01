package com.adjust.api.service;

import com.adjust.api.domain.AdjustPrice;
import com.adjust.api.repository.AdjustPriceRepository;
import com.adjust.api.service.dto.AdjustPriceDTO;
import com.adjust.api.service.mapper.AdjustPriceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AdjustPrice}.
 */
@Service
@Transactional
public class AdjustPriceService {

    private final Logger log = LoggerFactory.getLogger(AdjustPriceService.class);

    private final AdjustPriceRepository adjustPriceRepository;

    private final AdjustPriceMapper adjustPriceMapper;

    public AdjustPriceService(AdjustPriceRepository adjustPriceRepository, AdjustPriceMapper adjustPriceMapper) {
        this.adjustPriceRepository = adjustPriceRepository;
        this.adjustPriceMapper = adjustPriceMapper;
    }

    /**
     * Save a adjustPrice.
     *
     * @param adjustPriceDTO the entity to save.
     * @return the persisted entity.
     */
    public AdjustPriceDTO save(AdjustPriceDTO adjustPriceDTO) {
        log.debug("Request to save AdjustPrice : {}", adjustPriceDTO);
        AdjustPrice adjustPrice = adjustPriceMapper.toEntity(adjustPriceDTO);
        adjustPrice = adjustPriceRepository.save(adjustPrice);
        return adjustPriceMapper.toDto(adjustPrice);
    }

    /**
     * Get all the adjustPrices.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdjustPriceDTO> findAll() {
        log.debug("Request to get all AdjustPrices");
        return adjustPriceRepository.findAll().stream()
            .map(adjustPriceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one adjustPrice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdjustPriceDTO> findOne(Long id) {
        log.debug("Request to get AdjustPrice : {}", id);
        return adjustPriceRepository.findById(id)
            .map(adjustPriceMapper::toDto);
    }

    /**
     * Delete the adjustPrice by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdjustPrice : {}", id);
        adjustPriceRepository.deleteById(id);
    }
}
