package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdjustTutorialMapperTest {

    private AdjustTutorialMapper adjustTutorialMapper;

    @BeforeEach
    public void setUp() {
        adjustTutorialMapper = new AdjustTutorialMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adjustTutorialMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adjustTutorialMapper.fromId(null)).isNull();
    }
}
