package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ConversationMapperTest {

    private ConversationMapper conversationMapper;

    @BeforeEach
    public void setUp() {
        conversationMapper = new ConversationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(conversationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(conversationMapper.fromId(null)).isNull();
    }
}
