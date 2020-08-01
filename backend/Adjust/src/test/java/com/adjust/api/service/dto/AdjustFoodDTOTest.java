package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustFoodDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustFoodDTO.class);
        AdjustFoodDTO adjustFoodDTO1 = new AdjustFoodDTO();
        adjustFoodDTO1.setId(1L);
        AdjustFoodDTO adjustFoodDTO2 = new AdjustFoodDTO();
        assertThat(adjustFoodDTO1).isNotEqualTo(adjustFoodDTO2);
        adjustFoodDTO2.setId(adjustFoodDTO1.getId());
        assertThat(adjustFoodDTO1).isEqualTo(adjustFoodDTO2);
        adjustFoodDTO2.setId(2L);
        assertThat(adjustFoodDTO1).isNotEqualTo(adjustFoodDTO2);
        adjustFoodDTO1.setId(null);
        assertThat(adjustFoodDTO1).isNotEqualTo(adjustFoodDTO2);
    }
}
