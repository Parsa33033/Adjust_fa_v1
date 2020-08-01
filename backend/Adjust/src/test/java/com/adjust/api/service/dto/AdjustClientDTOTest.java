package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustClientDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustClientDTO.class);
        AdjustClientDTO adjustClientDTO1 = new AdjustClientDTO();
        adjustClientDTO1.setId(1L);
        AdjustClientDTO adjustClientDTO2 = new AdjustClientDTO();
        assertThat(adjustClientDTO1).isNotEqualTo(adjustClientDTO2);
        adjustClientDTO2.setId(adjustClientDTO1.getId());
        assertThat(adjustClientDTO1).isEqualTo(adjustClientDTO2);
        adjustClientDTO2.setId(2L);
        assertThat(adjustClientDTO1).isNotEqualTo(adjustClientDTO2);
        adjustClientDTO1.setId(null);
        assertThat(adjustClientDTO1).isNotEqualTo(adjustClientDTO2);
    }
}
