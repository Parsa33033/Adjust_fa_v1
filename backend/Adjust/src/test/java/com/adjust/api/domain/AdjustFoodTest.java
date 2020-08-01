package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustFoodTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustFood.class);
        AdjustFood adjustFood1 = new AdjustFood();
        adjustFood1.setId(1L);
        AdjustFood adjustFood2 = new AdjustFood();
        adjustFood2.setId(adjustFood1.getId());
        assertThat(adjustFood1).isEqualTo(adjustFood2);
        adjustFood2.setId(2L);
        assertThat(adjustFood1).isNotEqualTo(adjustFood2);
        adjustFood1.setId(null);
        assertThat(adjustFood1).isNotEqualTo(adjustFood2);
    }
}
