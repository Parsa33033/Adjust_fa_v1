package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class ProgramDevelopmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgramDevelopmentDTO.class);
        ProgramDevelopmentDTO programDevelopmentDTO1 = new ProgramDevelopmentDTO();
        programDevelopmentDTO1.setId(1L);
        ProgramDevelopmentDTO programDevelopmentDTO2 = new ProgramDevelopmentDTO();
        assertThat(programDevelopmentDTO1).isNotEqualTo(programDevelopmentDTO2);
        programDevelopmentDTO2.setId(programDevelopmentDTO1.getId());
        assertThat(programDevelopmentDTO1).isEqualTo(programDevelopmentDTO2);
        programDevelopmentDTO2.setId(2L);
        assertThat(programDevelopmentDTO1).isNotEqualTo(programDevelopmentDTO2);
        programDevelopmentDTO1.setId(null);
        assertThat(programDevelopmentDTO1).isNotEqualTo(programDevelopmentDTO2);
    }
}
