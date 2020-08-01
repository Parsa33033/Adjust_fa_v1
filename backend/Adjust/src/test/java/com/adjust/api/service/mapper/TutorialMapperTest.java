package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TutorialMapperTest {

    private TutorialMapper tutorialMapper;

    @BeforeEach
    public void setUp() {
        tutorialMapper = new TutorialMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tutorialMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tutorialMapper.fromId(null)).isNull();
    }
}
