package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CartMapperTest {

    private CartMapper cartMapper;

    @BeforeEach
    public void setUp() {
        cartMapper = new CartMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cartMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cartMapper.fromId(null)).isNull();
    }
}
