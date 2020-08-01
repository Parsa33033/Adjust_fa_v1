package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class BodyCompositionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BodyComposition.class);
        BodyComposition bodyComposition1 = new BodyComposition();
        bodyComposition1.setId(1L);
        BodyComposition bodyComposition2 = new BodyComposition();
        bodyComposition2.setId(bodyComposition1.getId());
        assertThat(bodyComposition1).isEqualTo(bodyComposition2);
        bodyComposition2.setId(2L);
        assertThat(bodyComposition1).isNotEqualTo(bodyComposition2);
        bodyComposition1.setId(null);
        assertThat(bodyComposition1).isNotEqualTo(bodyComposition2);
    }
}
