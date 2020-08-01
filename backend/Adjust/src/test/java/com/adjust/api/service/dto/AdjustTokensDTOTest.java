package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustTokensDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustTokensDTO.class);
        AdjustTokensDTO adjustTokensDTO1 = new AdjustTokensDTO();
        adjustTokensDTO1.setId(1L);
        AdjustTokensDTO adjustTokensDTO2 = new AdjustTokensDTO();
        assertThat(adjustTokensDTO1).isNotEqualTo(adjustTokensDTO2);
        adjustTokensDTO2.setId(adjustTokensDTO1.getId());
        assertThat(adjustTokensDTO1).isEqualTo(adjustTokensDTO2);
        adjustTokensDTO2.setId(2L);
        assertThat(adjustTokensDTO1).isNotEqualTo(adjustTokensDTO2);
        adjustTokensDTO1.setId(null);
        assertThat(adjustTokensDTO1).isNotEqualTo(adjustTokensDTO2);
    }
}
