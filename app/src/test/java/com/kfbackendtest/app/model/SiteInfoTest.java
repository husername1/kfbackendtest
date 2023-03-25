package com.kfbackendtest.app.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SiteInfoTest {

    @Test
    public void modelIsCorrect() {
        SiteInfo siteInfo = SiteInfo.builder()
                .id("1")
                .name("site name")
                .devices(List.of(Device.builder().build()))
        .build();

        assertThat(siteInfo.getId()).isEqualTo("1");
        assertThat(siteInfo.getName()).isEqualTo("site name");
        assertEquals(siteInfo.getDevices().size(), 1);
    }
}
