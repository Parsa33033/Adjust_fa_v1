package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustTutorialDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustTutorialDTO.class);
        AdjustTutorialDTO adjustTutorialDTO1 = new AdjustTutorialDTO();
        adjustTutorialDTO1.setId(1L);
        AdjustTutorialDTO adjustTutorialDTO2 = new AdjustTutorialDTO();
        assertThat(adjustTutorialDTO1).isNotEqualTo(adjustTutorialDTO2);
        adjustTutorialDTO2.setId(adjustTutorialDTO1.getId());
        assertThat(adjustTutorialDTO1).isEqualTo(adjustTutorialDTO2);
        adjustTutorialDTO2.setId(2L);
        assertThat(adjustTutorialDTO1).isNotEqualTo(adjustTutorialDTO2);
        adjustTutorialDTO1.setId(null);
        assertThat(adjustTutorialDTO1).isNotEqualTo(adjustTutorialDTO2);
    }
}
