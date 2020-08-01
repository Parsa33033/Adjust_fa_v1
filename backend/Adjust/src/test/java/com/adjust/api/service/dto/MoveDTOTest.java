package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class MoveDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MoveDTO.class);
        MoveDTO moveDTO1 = new MoveDTO();
        moveDTO1.setId(1L);
        MoveDTO moveDTO2 = new MoveDTO();
        assertThat(moveDTO1).isNotEqualTo(moveDTO2);
        moveDTO2.setId(moveDTO1.getId());
        assertThat(moveDTO1).isEqualTo(moveDTO2);
        moveDTO2.setId(2L);
        assertThat(moveDTO1).isNotEqualTo(moveDTO2);
        moveDTO1.setId(null);
        assertThat(moveDTO1).isNotEqualTo(moveDTO2);
    }
}
