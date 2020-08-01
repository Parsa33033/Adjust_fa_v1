package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class TutorialDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TutorialDTO.class);
        TutorialDTO tutorialDTO1 = new TutorialDTO();
        tutorialDTO1.setId(1L);
        TutorialDTO tutorialDTO2 = new TutorialDTO();
        assertThat(tutorialDTO1).isNotEqualTo(tutorialDTO2);
        tutorialDTO2.setId(tutorialDTO1.getId());
        assertThat(tutorialDTO1).isEqualTo(tutorialDTO2);
        tutorialDTO2.setId(2L);
        assertThat(tutorialDTO1).isNotEqualTo(tutorialDTO2);
        tutorialDTO1.setId(null);
        assertThat(tutorialDTO1).isNotEqualTo(tutorialDTO2);
    }
}
