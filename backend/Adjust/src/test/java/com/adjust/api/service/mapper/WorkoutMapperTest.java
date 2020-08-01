package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WorkoutMapperTest {

    private WorkoutMapper workoutMapper;

    @BeforeEach
    public void setUp() {
        workoutMapper = new WorkoutMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(workoutMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(workoutMapper.fromId(null)).isNull();
    }
}
