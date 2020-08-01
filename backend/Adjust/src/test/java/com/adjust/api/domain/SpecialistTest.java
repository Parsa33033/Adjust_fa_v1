package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class SpecialistTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Specialist.class);
        Specialist specialist1 = new Specialist();
        specialist1.setId(1L);
        Specialist specialist2 = new Specialist();
        specialist2.setId(specialist1.getId());
        assertThat(specialist1).isEqualTo(specialist2);
        specialist2.setId(2L);
        assertThat(specialist1).isNotEqualTo(specialist2);
        specialist1.setId(null);
        assertThat(specialist1).isNotEqualTo(specialist2);
    }
}
