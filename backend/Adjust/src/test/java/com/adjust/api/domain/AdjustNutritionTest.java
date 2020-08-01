package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustNutritionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustNutrition.class);
        AdjustNutrition adjustNutrition1 = new AdjustNutrition();
        adjustNutrition1.setId(1L);
        AdjustNutrition adjustNutrition2 = new AdjustNutrition();
        adjustNutrition2.setId(adjustNutrition1.getId());
        assertThat(adjustNutrition1).isEqualTo(adjustNutrition2);
        adjustNutrition2.setId(2L);
        assertThat(adjustNutrition1).isNotEqualTo(adjustNutrition2);
        adjustNutrition1.setId(null);
        assertThat(adjustNutrition1).isNotEqualTo(adjustNutrition2);
    }
}
