package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProgramDevelopmentMapperTest {

    private ProgramDevelopmentMapper programDevelopmentMapper;

    @BeforeEach
    public void setUp() {
        programDevelopmentMapper = new ProgramDevelopmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(programDevelopmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(programDevelopmentMapper.fromId(null)).isNull();
    }
}
