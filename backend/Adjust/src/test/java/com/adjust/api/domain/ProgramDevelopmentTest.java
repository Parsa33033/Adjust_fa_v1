package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class ProgramDevelopmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgramDevelopment.class);
        ProgramDevelopment programDevelopment1 = new ProgramDevelopment();
        programDevelopment1.setId(1L);
        ProgramDevelopment programDevelopment2 = new ProgramDevelopment();
        programDevelopment2.setId(programDevelopment1.getId());
        assertThat(programDevelopment1).isEqualTo(programDevelopment2);
        programDevelopment2.setId(2L);
        assertThat(programDevelopment1).isNotEqualTo(programDevelopment2);
        programDevelopment1.setId(null);
        assertThat(programDevelopment1).isNotEqualTo(programDevelopment2);
    }
}
