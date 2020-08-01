package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdjustFoodMapperTest {

    private AdjustFoodMapper adjustFoodMapper;

    @BeforeEach
    public void setUp() {
        adjustFoodMapper = new AdjustFoodMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adjustFoodMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adjustFoodMapper.fromId(null)).isNull();
    }
}
