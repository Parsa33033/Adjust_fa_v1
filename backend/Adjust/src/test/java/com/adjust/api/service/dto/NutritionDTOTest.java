package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class NutritionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NutritionDTO.class);
        NutritionDTO nutritionDTO1 = new NutritionDTO();
        nutritionDTO1.setId(1L);
        NutritionDTO nutritionDTO2 = new NutritionDTO();
        assertThat(nutritionDTO1).isNotEqualTo(nutritionDTO2);
        nutritionDTO2.setId(nutritionDTO1.getId());
        assertThat(nutritionDTO1).isEqualTo(nutritionDTO2);
        nutritionDTO2.setId(2L);
        assertThat(nutritionDTO1).isNotEqualTo(nutritionDTO2);
        nutritionDTO1.setId(null);
        assertThat(nutritionDTO1).isNotEqualTo(nutritionDTO2);
    }
}
