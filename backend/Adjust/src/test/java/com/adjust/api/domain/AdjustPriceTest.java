package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustPriceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustPrice.class);
        AdjustPrice adjustPrice1 = new AdjustPrice();
        adjustPrice1.setId(1L);
        AdjustPrice adjustPrice2 = new AdjustPrice();
        adjustPrice2.setId(adjustPrice1.getId());
        assertThat(adjustPrice1).isEqualTo(adjustPrice2);
        adjustPrice2.setId(2L);
        assertThat(adjustPrice1).isNotEqualTo(adjustPrice2);
        adjustPrice1.setId(null);
        assertThat(adjustPrice1).isNotEqualTo(adjustPrice2);
    }
}
