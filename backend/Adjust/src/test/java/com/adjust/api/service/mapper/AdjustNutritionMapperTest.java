package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdjustNutritionMapperTest {

    private AdjustNutritionMapper adjustNutritionMapper;

    @BeforeEach
    public void setUp() {
        adjustNutritionMapper = new AdjustNutritionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adjustNutritionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adjustNutritionMapper.fromId(null)).isNull();
    }
}
