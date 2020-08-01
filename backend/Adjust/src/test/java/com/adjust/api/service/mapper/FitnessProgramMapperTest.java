package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FitnessProgramMapperTest {

    private FitnessProgramMapper fitnessProgramMapper;

    @BeforeEach
    public void setUp() {
        fitnessProgramMapper = new FitnessProgramMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fitnessProgramMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fitnessProgramMapper.fromId(null)).isNull();
    }
}
