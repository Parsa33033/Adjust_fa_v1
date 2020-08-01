package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class ChatMessageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChatMessage.class);
        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setId(1L);
        ChatMessage chatMessage2 = new ChatMessage();
        chatMessage2.setId(chatMessage1.getId());
        assertThat(chatMessage1).isEqualTo(chatMessage2);
        chatMessage2.setId(2L);
        assertThat(chatMessage1).isNotEqualTo(chatMessage2);
        chatMessage1.setId(null);
        assertThat(chatMessage1).isNotEqualTo(chatMessage2);
    }
}
