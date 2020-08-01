package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustMoveTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustMove.class);
        AdjustMove adjustMove1 = new AdjustMove();
        adjustMove1.setId(1L);
        AdjustMove adjustMove2 = new AdjustMove();
        adjustMove2.setId(adjustMove1.getId());
        assertThat(adjustMove1).isEqualTo(adjustMove2);
        adjustMove2.setId(2L);
        assertThat(adjustMove1).isNotEqualTo(adjustMove2);
        adjustMove1.setId(null);
        assertThat(adjustMove1).isNotEqualTo(adjustMove2);
    }
}
