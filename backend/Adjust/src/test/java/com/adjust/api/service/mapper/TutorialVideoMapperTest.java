package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TutorialVideoMapperTest {

    private TutorialVideoMapper tutorialVideoMapper;

    @BeforeEach
    public void setUp() {
        tutorialVideoMapper = new TutorialVideoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tutorialVideoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tutorialVideoMapper.fromId(null)).isNull();
    }
}
