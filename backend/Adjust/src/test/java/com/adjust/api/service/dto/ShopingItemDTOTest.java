package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class ShopingItemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShopingItemDTO.class);
        ShopingItemDTO shopingItemDTO1 = new ShopingItemDTO();
        shopingItemDTO1.setId(1L);
        ShopingItemDTO shopingItemDTO2 = new ShopingItemDTO();
        assertThat(shopingItemDTO1).isNotEqualTo(shopingItemDTO2);
        shopingItemDTO2.setId(shopingItemDTO1.getId());
        assertThat(shopingItemDTO1).isEqualTo(shopingItemDTO2);
        shopingItemDTO2.setId(2L);
        assertThat(shopingItemDTO1).isNotEqualTo(shopingItemDTO2);
        shopingItemDTO1.setId(null);
        assertThat(shopingItemDTO1).isNotEqualTo(shopingItemDTO2);
    }
}
