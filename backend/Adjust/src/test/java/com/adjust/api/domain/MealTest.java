package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class MealTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Meal.class);
        Meal meal1 = new Meal();
        meal1.setId(1L);
        Meal meal2 = new Meal();
        meal2.setId(meal1.getId());
        assertThat(meal1).isEqualTo(meal2);
        meal2.setId(2L);
        assertThat(meal1).isNotEqualTo(meal2);
        meal1.setId(null);
        assertThat(meal1).isNotEqualTo(meal2);
    }
}
