package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdjustPriceMapperTest {

    private AdjustPriceMapper adjustPriceMapper;

    @BeforeEach
    public void setUp() {
        adjustPriceMapper = new AdjustPriceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adjustPriceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adjustPriceMapper.fromId(null)).isNull();
    }
}
