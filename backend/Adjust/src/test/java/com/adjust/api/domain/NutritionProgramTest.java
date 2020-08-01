package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class NutritionProgramTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NutritionProgram.class);
        NutritionProgram nutritionProgram1 = new NutritionProgram();
        nutritionProgram1.setId(1L);
        NutritionProgram nutritionProgram2 = new NutritionProgram();
        nutritionProgram2.setId(nutritionProgram1.getId());
        assertThat(nutritionProgram1).isEqualTo(nutritionProgram2);
        nutritionProgram2.setId(2L);
        assertThat(nutritionProgram1).isNotEqualTo(nutritionProgram2);
        nutritionProgram1.setId(null);
        assertThat(nutritionProgram1).isNotEqualTo(nutritionProgram2);
    }
}
