package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class ShopingItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShopingItem.class);
        ShopingItem shopingItem1 = new ShopingItem();
        shopingItem1.setId(1L);
        ShopingItem shopingItem2 = new ShopingItem();
        shopingItem2.setId(shopingItem1.getId());
        assertThat(shopingItem1).isEqualTo(shopingItem2);
        shopingItem2.setId(2L);
        assertThat(shopingItem1).isNotEqualTo(shopingItem2);
        shopingItem1.setId(null);
        assertThat(shopingItem1).isNotEqualTo(shopingItem2);
    }
}
