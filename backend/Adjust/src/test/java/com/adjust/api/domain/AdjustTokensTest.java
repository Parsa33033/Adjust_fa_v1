package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustTokensTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustTokens.class);
        AdjustTokens adjustTokens1 = new AdjustTokens();
        adjustTokens1.setId(1L);
        AdjustTokens adjustTokens2 = new AdjustTokens();
        adjustTokens2.setId(adjustTokens1.getId());
        assertThat(adjustTokens1).isEqualTo(adjustTokens2);
        adjustTokens2.setId(2L);
        assertThat(adjustTokens1).isNotEqualTo(adjustTokens2);
        adjustTokens1.setId(null);
        assertThat(adjustTokens1).isNotEqualTo(adjustTokens2);
    }
}
