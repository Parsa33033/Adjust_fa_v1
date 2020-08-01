package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MoveMapperTest {

    private MoveMapper moveMapper;

    @BeforeEach
    public void setUp() {
        moveMapper = new MoveMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(moveMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(moveMapper.fromId(null)).isNull();
    }
}
