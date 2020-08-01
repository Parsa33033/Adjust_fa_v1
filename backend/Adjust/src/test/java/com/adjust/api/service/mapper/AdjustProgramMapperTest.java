package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdjustProgramMapperTest {

    private AdjustProgramMapper adjustProgramMapper;

    @BeforeEach
    public void setUp() {
        adjustProgramMapper = new AdjustProgramMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adjustProgramMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adjustProgramMapper.fromId(null)).isNull();
    }
}
