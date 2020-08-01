package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdjustTutorialVideoMapperTest {

    private AdjustTutorialVideoMapper adjustTutorialVideoMapper;

    @BeforeEach
    public void setUp() {
        adjustTutorialVideoMapper = new AdjustTutorialVideoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adjustTutorialVideoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adjustTutorialVideoMapper.fromId(null)).isNull();
    }
}
