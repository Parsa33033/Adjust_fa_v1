package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ChatMessageMapperTest {

    private ChatMessageMapper chatMessageMapper;

    @BeforeEach
    public void setUp() {
        chatMessageMapper = new ChatMessageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(chatMessageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(chatMessageMapper.fromId(null)).isNull();
    }
}
