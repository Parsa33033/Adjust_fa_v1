package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ShopingItemMapperTest {

    private ShopingItemMapper shopingItemMapper;

    @BeforeEach
    public void setUp() {
        shopingItemMapper = new ShopingItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(shopingItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(shopingItemMapper.fromId(null)).isNull();
    }
}
