package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class FitnessProgramDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FitnessProgramDTO.class);
        FitnessProgramDTO fitnessProgramDTO1 = new FitnessProgramDTO();
        fitnessProgramDTO1.setId(1L);
        FitnessProgramDTO fitnessProgramDTO2 = new FitnessProgramDTO();
        assertThat(fitnessProgramDTO1).isNotEqualTo(fitnessProgramDTO2);
        fitnessProgramDTO2.setId(fitnessProgramDTO1.getId());
        assertThat(fitnessProgramDTO1).isEqualTo(fitnessProgramDTO2);
        fitnessProgramDTO2.setId(2L);
        assertThat(fitnessProgramDTO1).isNotEqualTo(fitnessProgramDTO2);
        fitnessProgramDTO1.setId(null);
        assertThat(fitnessProgramDTO1).isNotEqualTo(fitnessProgramDTO2);
    }
}
