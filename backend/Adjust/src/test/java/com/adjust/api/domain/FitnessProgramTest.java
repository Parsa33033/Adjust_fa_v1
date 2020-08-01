package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class FitnessProgramTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FitnessProgram.class);
        FitnessProgram fitnessProgram1 = new FitnessProgram();
        fitnessProgram1.setId(1L);
        FitnessProgram fitnessProgram2 = new FitnessProgram();
        fitnessProgram2.setId(fitnessProgram1.getId());
        assertThat(fitnessProgram1).isEqualTo(fitnessProgram2);
        fitnessProgram2.setId(2L);
        assertThat(fitnessProgram1).isNotEqualTo(fitnessProgram2);
        fitnessProgram1.setId(null);
        assertThat(fitnessProgram1).isNotEqualTo(fitnessProgram2);
    }
}
