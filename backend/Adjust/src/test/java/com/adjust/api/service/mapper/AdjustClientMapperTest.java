package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdjustClientMapperTest {

    private AdjustClientMapper adjustClientMapper;

    @BeforeEach
    public void setUp() {
        adjustClientMapper = new AdjustClientMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adjustClientMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adjustClientMapper.fromId(null)).isNull();
    }
}
