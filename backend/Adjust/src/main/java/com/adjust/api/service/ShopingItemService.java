package com.adjust.api.service;

import com.adjust.api.domain.ShopingItem;
import com.adjust.api.repository.ShopingItemRepository;
import com.adjust.api.service.dto.ShopingItemDTO;
import com.adjust.api.service.mapper.ShopingItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ShopingItem}.
 */
@Service
@Transactional
public class ShopingItemService {

    private final Logger log = LoggerFactory.getLogger(ShopingItemService.class);

    private final ShopingItemRepository shopingItemRepository;

    private final ShopingItemMapper shopingItemMapper;

    public ShopingItemService(ShopingItemRepository shopingItemRepository, ShopingItemMapper shopingItemMapper) {
        this.shopingItemRepository = shopingItemRepository;
        this.shopingItemMapper = shopingItemMapper;
    }

    /**
     * Save a shopingItem.
     *
     * @param shopingItemDTO the entity to save.
     * @return the persisted entity.
     */
    public ShopingItemDTO save(ShopingItemDTO shopingItemDTO) {
        log.debug("Request to save ShopingItem : {}", shopingItemDTO);
        ShopingItem shopingItem = shopingItemMapper.toEntity(shopingItemDTO);
        shopingItem = shopingItemRepository.save(shopingItem);
        return shopingItemMapper.toDto(shopingItem);
    }

    /**
     * Get all the shopingItems.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ShopingItemDTO> findAll() {
        log.debug("Request to get all ShopingItems");
        return shopingItemRepository.findAll().stream()
            .map(shopingItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one shopingItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ShopingItemDTO> findOne(Long id) {
        log.debug("Request to get ShopingItem : {}", id);
        return shopingItemRepository.findById(id)
            .map(shopingItemMapper::toDto);
    }

    /**
     * Delete the shopingItem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ShopingItem : {}", id);
        shopingItemRepository.deleteById(id);
    }
}
