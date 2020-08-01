package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ExerciseMapperTest {

    private ExerciseMapper exerciseMapper;

    @BeforeEach
    public void setUp() {
        exerciseMapper = new ExerciseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(exerciseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(exerciseMapper.fromId(null)).isNull();
    }
}
