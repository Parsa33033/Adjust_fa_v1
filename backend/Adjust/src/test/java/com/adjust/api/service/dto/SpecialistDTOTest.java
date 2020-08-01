package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class SpecialistDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpecialistDTO.class);
        SpecialistDTO specialistDTO1 = new SpecialistDTO();
        specialistDTO1.setId(1L);
        SpecialistDTO specialistDTO2 = new SpecialistDTO();
        assertThat(specialistDTO1).isNotEqualTo(specialistDTO2);
        specialistDTO2.setId(specialistDTO1.getId());
        assertThat(specialistDTO1).isEqualTo(specialistDTO2);
        specialistDTO2.setId(2L);
        assertThat(specialistDTO1).isNotEqualTo(specialistDTO2);
        specialistDTO1.setId(null);
        assertThat(specialistDTO1).isNotEqualTo(specialistDTO2);
    }
}
