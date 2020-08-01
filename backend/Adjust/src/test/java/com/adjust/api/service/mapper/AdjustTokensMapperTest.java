package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdjustTokensMapperTest {

    private AdjustTokensMapper adjustTokensMapper;

    @BeforeEach
    public void setUp() {
        adjustTokensMapper = new AdjustTokensMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adjustTokensMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adjustTokensMapper.fromId(null)).isNull();
    }
}
