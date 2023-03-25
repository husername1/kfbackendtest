package com.kfbackendtest.app.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeviceTest {

    @Test
    public void modelIsCorrect() {
        Device device = Device.builder()
                .id("1")
                .name("device name")
        .build();

        assertThat(device.getId()).isEqualTo("1");
        assertThat(device.getName()).isEqualTo("device name");
    }
}
