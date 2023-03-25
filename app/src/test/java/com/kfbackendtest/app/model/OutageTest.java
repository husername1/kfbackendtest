package com.kfbackendtest.app.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OutageTest {

    @Test
    public void modelIsCorrect() {
        Outage outage = Outage.builder()
                .id("1")
                .begin("123")
                .end("321")
                .name("outage-name")
        .build();

        assertThat(outage.getId()).isEqualTo("1");
        assertThat(outage.getBegin()).isEqualTo("123");
        assertThat(outage.getEnd()).isEqualTo("321");
        assertThat(outage.getName()).isEqualTo("outage-name");
    }
}
