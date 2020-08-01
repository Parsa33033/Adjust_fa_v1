package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdjustMoveMapperTest {

    private AdjustMoveMapper adjustMoveMapper;

    @BeforeEach
    public void setUp() {
        adjustMoveMapper = new AdjustMoveMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adjustMoveMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adjustMoveMapper.fromId(null)).isNull();
    }
}
