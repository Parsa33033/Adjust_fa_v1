package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class ExerciseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExerciseDTO.class);
        ExerciseDTO exerciseDTO1 = new ExerciseDTO();
        exerciseDTO1.setId(1L);
        ExerciseDTO exerciseDTO2 = new ExerciseDTO();
        assertThat(exerciseDTO1).isNotEqualTo(exerciseDTO2);
        exerciseDTO2.setId(exerciseDTO1.getId());
        assertThat(exerciseDTO1).isEqualTo(exerciseDTO2);
        exerciseDTO2.setId(2L);
        assertThat(exerciseDTO1).isNotEqualTo(exerciseDTO2);
        exerciseDTO1.setId(null);
        assertThat(exerciseDTO1).isNotEqualTo(exerciseDTO2);
    }
}
