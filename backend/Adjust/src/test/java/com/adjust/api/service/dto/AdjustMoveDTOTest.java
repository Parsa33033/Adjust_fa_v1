package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustMoveDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustMoveDTO.class);
        AdjustMoveDTO adjustMoveDTO1 = new AdjustMoveDTO();
        adjustMoveDTO1.setId(1L);
        AdjustMoveDTO adjustMoveDTO2 = new AdjustMoveDTO();
        assertThat(adjustMoveDTO1).isNotEqualTo(adjustMoveDTO2);
        adjustMoveDTO2.setId(adjustMoveDTO1.getId());
        assertThat(adjustMoveDTO1).isEqualTo(adjustMoveDTO2);
        adjustMoveDTO2.setId(2L);
        assertThat(adjustMoveDTO1).isNotEqualTo(adjustMoveDTO2);
        adjustMoveDTO1.setId(null);
        assertThat(adjustMoveDTO1).isNotEqualTo(adjustMoveDTO2);
    }
}
