package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecialistMapperTest {

    private SpecialistMapper specialistMapper;

    @BeforeEach
    public void setUp() {
        specialistMapper = new SpecialistMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(specialistMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(specialistMapper.fromId(null)).isNull();
    }
}
