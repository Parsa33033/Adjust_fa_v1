package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class TutorialVideoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TutorialVideoDTO.class);
        TutorialVideoDTO tutorialVideoDTO1 = new TutorialVideoDTO();
        tutorialVideoDTO1.setId(1L);
        TutorialVideoDTO tutorialVideoDTO2 = new TutorialVideoDTO();
        assertThat(tutorialVideoDTO1).isNotEqualTo(tutorialVideoDTO2);
        tutorialVideoDTO2.setId(tutorialVideoDTO1.getId());
        assertThat(tutorialVideoDTO1).isEqualTo(tutorialVideoDTO2);
        tutorialVideoDTO2.setId(2L);
        assertThat(tutorialVideoDTO1).isNotEqualTo(tutorialVideoDTO2);
        tutorialVideoDTO1.setId(null);
        assertThat(tutorialVideoDTO1).isNotEqualTo(tutorialVideoDTO2);
    }
}
