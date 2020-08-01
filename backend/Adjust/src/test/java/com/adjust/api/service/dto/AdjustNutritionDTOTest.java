package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustNutritionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustNutritionDTO.class);
        AdjustNutritionDTO adjustNutritionDTO1 = new AdjustNutritionDTO();
        adjustNutritionDTO1.setId(1L);
        AdjustNutritionDTO adjustNutritionDTO2 = new AdjustNutritionDTO();
        assertThat(adjustNutritionDTO1).isNotEqualTo(adjustNutritionDTO2);
        adjustNutritionDTO2.setId(adjustNutritionDTO1.getId());
        assertThat(adjustNutritionDTO1).isEqualTo(adjustNutritionDTO2);
        adjustNutritionDTO2.setId(2L);
        assertThat(adjustNutritionDTO1).isNotEqualTo(adjustNutritionDTO2);
        adjustNutritionDTO1.setId(null);
        assertThat(adjustNutritionDTO1).isNotEqualTo(adjustNutritionDTO2);
    }
}
