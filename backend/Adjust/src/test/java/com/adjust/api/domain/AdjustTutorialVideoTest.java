package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustTutorialVideoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustTutorialVideo.class);
        AdjustTutorialVideo adjustTutorialVideo1 = new AdjustTutorialVideo();
        adjustTutorialVideo1.setId(1L);
        AdjustTutorialVideo adjustTutorialVideo2 = new AdjustTutorialVideo();
        adjustTutorialVideo2.setId(adjustTutorialVideo1.getId());
        assertThat(adjustTutorialVideo1).isEqualTo(adjustTutorialVideo2);
        adjustTutorialVideo2.setId(2L);
        assertThat(adjustTutorialVideo1).isNotEqualTo(adjustTutorialVideo2);
        adjustTutorialVideo1.setId(null);
        assertThat(adjustTutorialVideo1).isNotEqualTo(adjustTutorialVideo2);
    }
}
