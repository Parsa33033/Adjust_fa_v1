package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustTutorialVideoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustTutorialVideoDTO.class);
        AdjustTutorialVideoDTO adjustTutorialVideoDTO1 = new AdjustTutorialVideoDTO();
        adjustTutorialVideoDTO1.setId(1L);
        AdjustTutorialVideoDTO adjustTutorialVideoDTO2 = new AdjustTutorialVideoDTO();
        assertThat(adjustTutorialVideoDTO1).isNotEqualTo(adjustTutorialVideoDTO2);
        adjustTutorialVideoDTO2.setId(adjustTutorialVideoDTO1.getId());
        assertThat(adjustTutorialVideoDTO1).isEqualTo(adjustTutorialVideoDTO2);
        adjustTutorialVideoDTO2.setId(2L);
        assertThat(adjustTutorialVideoDTO1).isNotEqualTo(adjustTutorialVideoDTO2);
        adjustTutorialVideoDTO1.setId(null);
        assertThat(adjustTutorialVideoDTO1).isNotEqualTo(adjustTutorialVideoDTO2);
    }
}
