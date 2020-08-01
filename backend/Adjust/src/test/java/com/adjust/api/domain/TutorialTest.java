package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class TutorialTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tutorial.class);
        Tutorial tutorial1 = new Tutorial();
        tutorial1.setId(1L);
        Tutorial tutorial2 = new Tutorial();
        tutorial2.setId(tutorial1.getId());
        assertThat(tutorial1).isEqualTo(tutorial2);
        tutorial2.setId(2L);
        assertThat(tutorial1).isNotEqualTo(tutorial2);
        tutorial1.setId(null);
        assertThat(tutorial1).isNotEqualTo(tutorial2);
    }
}
