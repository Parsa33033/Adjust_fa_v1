package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustShopingItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustShopingItem.class);
        AdjustShopingItem adjustShopingItem1 = new AdjustShopingItem();
        adjustShopingItem1.setId(1L);
        AdjustShopingItem adjustShopingItem2 = new AdjustShopingItem();
        adjustShopingItem2.setId(adjustShopingItem1.getId());
        assertThat(adjustShopingItem1).isEqualTo(adjustShopingItem2);
        adjustShopingItem2.setId(2L);
        assertThat(adjustShopingItem1).isNotEqualTo(adjustShopingItem2);
        adjustShopingItem1.setId(null);
        assertThat(adjustShopingItem1).isNotEqualTo(adjustShopingItem2);
    }
}
