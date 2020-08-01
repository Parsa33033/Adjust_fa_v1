package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NutritionProgramMapperTest {

    private NutritionProgramMapper nutritionProgramMapper;

    @BeforeEach
    public void setUp() {
        nutritionProgramMapper = new NutritionProgramMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(nutritionProgramMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nutritionProgramMapper.fromId(null)).isNull();
    }
}
