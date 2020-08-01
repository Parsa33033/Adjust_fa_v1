package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class NutritionProgramDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NutritionProgramDTO.class);
        NutritionProgramDTO nutritionProgramDTO1 = new NutritionProgramDTO();
        nutritionProgramDTO1.setId(1L);
        NutritionProgramDTO nutritionProgramDTO2 = new NutritionProgramDTO();
        assertThat(nutritionProgramDTO1).isNotEqualTo(nutritionProgramDTO2);
        nutritionProgramDTO2.setId(nutritionProgramDTO1.getId());
        assertThat(nutritionProgramDTO1).isEqualTo(nutritionProgramDTO2);
        nutritionProgramDTO2.setId(2L);
        assertThat(nutritionProgramDTO1).isNotEqualTo(nutritionProgramDTO2);
        nutritionProgramDTO1.setId(null);
        assertThat(nutritionProgramDTO1).isNotEqualTo(nutritionProgramDTO2);
    }
}
