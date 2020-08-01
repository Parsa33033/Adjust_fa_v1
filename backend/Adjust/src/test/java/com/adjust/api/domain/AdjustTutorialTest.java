package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustTutorialTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustTutorial.class);
        AdjustTutorial adjustTutorial1 = new AdjustTutorial();
        adjustTutorial1.setId(1L);
        AdjustTutorial adjustTutorial2 = new AdjustTutorial();
        adjustTutorial2.setId(adjustTutorial1.getId());
        assertThat(adjustTutorial1).isEqualTo(adjustTutorial2);
        adjustTutorial2.setId(2L);
        assertThat(adjustTutorial1).isNotEqualTo(adjustTutorial2);
        adjustTutorial1.setId(null);
        assertThat(adjustTutorial1).isNotEqualTo(adjustTutorial2);
    }
}
