package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NutritionMapperTest {

    private NutritionMapper nutritionMapper;

    @BeforeEach
    public void setUp() {
        nutritionMapper = new NutritionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(nutritionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nutritionMapper.fromId(null)).isNull();
    }
}
