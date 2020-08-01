package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustProgramDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustProgramDTO.class);
        AdjustProgramDTO adjustProgramDTO1 = new AdjustProgramDTO();
        adjustProgramDTO1.setId(1L);
        AdjustProgramDTO adjustProgramDTO2 = new AdjustProgramDTO();
        assertThat(adjustProgramDTO1).isNotEqualTo(adjustProgramDTO2);
        adjustProgramDTO2.setId(adjustProgramDTO1.getId());
        assertThat(adjustProgramDTO1).isEqualTo(adjustProgramDTO2);
        adjustProgramDTO2.setId(2L);
        assertThat(adjustProgramDTO1).isNotEqualTo(adjustProgramDTO2);
        adjustProgramDTO1.setId(null);
        assertThat(adjustProgramDTO1).isNotEqualTo(adjustProgramDTO2);
    }
}
