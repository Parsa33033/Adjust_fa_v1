package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustShopingItemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustShopingItemDTO.class);
        AdjustShopingItemDTO adjustShopingItemDTO1 = new AdjustShopingItemDTO();
        adjustShopingItemDTO1.setId(1L);
        AdjustShopingItemDTO adjustShopingItemDTO2 = new AdjustShopingItemDTO();
        assertThat(adjustShopingItemDTO1).isNotEqualTo(adjustShopingItemDTO2);
        adjustShopingItemDTO2.setId(adjustShopingItemDTO1.getId());
        assertThat(adjustShopingItemDTO1).isEqualTo(adjustShopingItemDTO2);
        adjustShopingItemDTO2.setId(2L);
        assertThat(adjustShopingItemDTO1).isNotEqualTo(adjustShopingItemDTO2);
        adjustShopingItemDTO1.setId(null);
        assertThat(adjustShopingItemDTO1).isNotEqualTo(adjustShopingItemDTO2);
    }
}
