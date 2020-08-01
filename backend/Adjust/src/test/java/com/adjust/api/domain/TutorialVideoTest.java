package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class TutorialVideoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TutorialVideo.class);
        TutorialVideo tutorialVideo1 = new TutorialVideo();
        tutorialVideo1.setId(1L);
        TutorialVideo tutorialVideo2 = new TutorialVideo();
        tutorialVideo2.setId(tutorialVideo1.getId());
        assertThat(tutorialVideo1).isEqualTo(tutorialVideo2);
        tutorialVideo2.setId(2L);
        assertThat(tutorialVideo1).isNotEqualTo(tutorialVideo2);
        tutorialVideo1.setId(null);
        assertThat(tutorialVideo1).isNotEqualTo(tutorialVideo2);
    }
}
