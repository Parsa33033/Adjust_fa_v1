package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BodyCompositionMapperTest {

    private BodyCompositionMapper bodyCompositionMapper;

    @BeforeEach
    public void setUp() {
        bodyCompositionMapper = new BodyCompositionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(bodyCompositionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(bodyCompositionMapper.fromId(null)).isNull();
    }
}
