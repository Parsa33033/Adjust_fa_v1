package com.adjust.api.service;

import com.adjust.api.domain.AdjustShopingItem;
import com.adjust.api.repository.AdjustShopingItemRepository;
import com.adjust.api.service.dto.AdjustShopingItemDTO;
import com.adjust.api.service.mapper.AdjustShopingItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AdjustShopingItem}.
 */
@Service
@Transactional
public class AdjustShopingItemService {

    private final Logger log = LoggerFactory.getLogger(AdjustShopingItemService.class);

    private final AdjustShopingItemRepository adjustShopingItemRepository;

    private final AdjustShopingItemMapper adjustShopingItemMapper;

    public AdjustShopingItemService(AdjustShopingItemRepository adjustShopingItemRepository, AdjustShopingItemMapper adjustShopingItemMapper) {
        this.adjustShopingItemRepository = adjustShopingItemRepository;
        this.adjustShopingItemMapper = adjustShopingItemMapper;
    }

    /**
     * Save a adjustShopingItem.
     *
     * @param adjustShopingItemDTO the entity to save.
     * @return the persisted entity.
     */
    public AdjustShopingItemDTO save(AdjustShopingItemDTO adjustShopingItemDTO) {
        log.debug("Request to save AdjustShopingItem : {}", adjustShopingItemDTO);
        AdjustShopingItem adjustShopingItem = adjustShopingItemMapper.toEntity(adjustShopingItemDTO);
        adjustShopingItem = adjustShopingItemRepository.save(adjustShopingItem);
        return adjustShopingItemMapper.toDto(adjustShopingItem);
    }

    /**
     * Get all the adjustShopingItems.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdjustShopingItemDTO> findAll() {
        log.debug("Request to get all AdjustShopingItems");
        return adjustShopingItemRepository.findAll().stream()
            .map(adjustShopingItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one adjustShopingItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdjustShopingItemDTO> findOne(Long id) {
        log.debug("Request to get AdjustShopingItem : {}", id);
        return adjustShopingItemRepository.findById(id)
            .map(adjustShopingItemMapper::toDto);
    }

    /**
     * Delete the adjustShopingItem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdjustShopingItem : {}", id);
        adjustShopingItemRepository.deleteById(id);
    }
}
