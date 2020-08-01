package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustProgramTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustProgram.class);
        AdjustProgram adjustProgram1 = new AdjustProgram();
        adjustProgram1.setId(1L);
        AdjustProgram adjustProgram2 = new AdjustProgram();
        adjustProgram2.setId(adjustProgram1.getId());
        assertThat(adjustProgram1).isEqualTo(adjustProgram2);
        adjustProgram2.setId(2L);
        assertThat(adjustProgram1).isNotEqualTo(adjustProgram2);
        adjustProgram1.setId(null);
        assertThat(adjustProgram1).isNotEqualTo(adjustProgram2);
    }
}
