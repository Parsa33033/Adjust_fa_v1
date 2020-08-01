package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MealMapperTest {

    private MealMapper mealMapper;

    @BeforeEach
    public void setUp() {
        mealMapper = new MealMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mealMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mealMapper.fromId(null)).isNull();
    }
}
