package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdjustShopingItemMapperTest {

    private AdjustShopingItemMapper adjustShopingItemMapper;

    @BeforeEach
    public void setUp() {
        adjustShopingItemMapper = new AdjustShopingItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adjustShopingItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adjustShopingItemMapper.fromId(null)).isNull();
    }
}
