package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustClientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustClient.class);
        AdjustClient adjustClient1 = new AdjustClient();
        adjustClient1.setId(1L);
        AdjustClient adjustClient2 = new AdjustClient();
        adjustClient2.setId(adjustClient1.getId());
        assertThat(adjustClient1).isEqualTo(adjustClient2);
        adjustClient2.setId(2L);
        assertThat(adjustClient1).isNotEqualTo(adjustClient2);
        adjustClient1.setId(null);
        assertThat(adjustClient1).isNotEqualTo(adjustClient2);
    }
}
