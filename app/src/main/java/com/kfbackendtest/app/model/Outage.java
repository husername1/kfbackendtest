package com.kfbackendtest.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Outage {
    private String id;
    private String begin;
    private String end;
    private String name;
}
