package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class BodyCompositionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BodyCompositionDTO.class);
        BodyCompositionDTO bodyCompositionDTO1 = new BodyCompositionDTO();
        bodyCompositionDTO1.setId(1L);
        BodyCompositionDTO bodyCompositionDTO2 = new BodyCompositionDTO();
        assertThat(bodyCompositionDTO1).isNotEqualTo(bodyCompositionDTO2);
        bodyCompositionDTO2.setId(bodyCompositionDTO1.getId());
        assertThat(bodyCompositionDTO1).isEqualTo(bodyCompositionDTO2);
        bodyCompositionDTO2.setId(2L);
        assertThat(bodyCompositionDTO1).isNotEqualTo(bodyCompositionDTO2);
        bodyCompositionDTO1.setId(null);
        assertThat(bodyCompositionDTO1).isNotEqualTo(bodyCompositionDTO2);
    }
}
