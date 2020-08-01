package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustPriceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustPriceDTO.class);
        AdjustPriceDTO adjustPriceDTO1 = new AdjustPriceDTO();
        adjustPriceDTO1.setId(1L);
        AdjustPriceDTO adjustPriceDTO2 = new AdjustPriceDTO();
        assertThat(adjustPriceDTO1).isNotEqualTo(adjustPriceDTO2);
        adjustPriceDTO2.setId(adjustPriceDTO1.getId());
        assertThat(adjustPriceDTO1).isEqualTo(adjustPriceDTO2);
        adjustPriceDTO2.setId(2L);
        assertThat(adjustPriceDTO1).isNotEqualTo(adjustPriceDTO2);
        adjustPriceDTO1.setId(null);
        assertThat(adjustPriceDTO1).isNotEqualTo(adjustPriceDTO2);
    }
}
