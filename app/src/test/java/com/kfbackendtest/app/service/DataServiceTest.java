package com.kfbackendtest.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.kfbackendtest.app.model.Outage;
import com.kfbackendtest.app.model.SiteInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataServiceTest {

    private DataService dataService = new DataService();

    private ObjectMapper objectMapper = new ObjectMapper();

    String outagesJson = """
            [
              {
                "id": "002b28fc-283c-47ec-9af2-ea287336dc1b",
                "begin": "2021-07-26T17:09:31.036Z",
                "end": "2021-08-29T00:37:42.253Z"
              },
              {
                "id": "002b28fc-283c-47ec-9af2-ea287336dc1b",
                "begin": "2022-05-23T12:21:27.377Z",
                "end": "2022-11-13T02:16:38.905Z"
              },
              {
                "id": "002b28fc-283c-47ec-9af2-ea287336dc1b",
                "begin": "2022-12-04T09:59:33.628Z",
                "end": "2022-12-12T22:35:13.815Z"
              },
              {
                "id": "04ccad00-eb8d-4045-8994-b569cb4b64c1",
                "begin": "2022-07-12T16:31:47.254Z",
                "end": "2022-10-13T04:05:10.044Z"
              },
              {
                "id": "086b0d53-b311-4441-aaf3-935646f03d4d",
                "begin": "2022-07-12T16:31:47.254Z",
                "end": "2022-10-13T04:05:10.044Z"
              },
              {
                "id": "27820d4a-1bc4-4fc1-a5f0-bcb3627e94a1",
                "begin": "2021-07-12T16:31:47.254Z",
                "end": "2022-10-13T04:05:10.044Z"
              }
            ]
            """;

    String siteInfoJson = """
            {
              "id": "kingfisher",
              "name": "KingFisher",
              "devices": [
                {
                  "id": "002b28fc-283c-47ec-9af2-ea287336dc1b",
                  "name": "Battery 1"
                },
                {
                  "id": "086b0d53-b311-4441-aaf3-935646f03d4d",
                  "name": "Battery 2"
                }
              ]
            }
            """;

    @Test
    void shouldReturnResult() throws JsonProcessingException {
        objectMapper
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        List<Outage> outages = objectMapper.readValue(outagesJson, new TypeReference<>() {
        });
        SiteInfo siteInfo = objectMapper.readValue(this.siteInfoJson, SiteInfo.class);

        String expectedJson = """
                [
                  {
                    "id": "002b28fc-283c-47ec-9af2-ea287336dc1b",
                    "name": "Battery 1",
                    "begin": "2022-05-23T12:21:27.377Z",
                    "end": "2022-11-13T02:16:38.905Z"
                  },
                  {
                    "id": "002b28fc-283c-47ec-9af2-ea287336dc1b",
                    "name": "Battery 1",
                    "begin": "2022-12-04T09:59:33.628Z",
                    "end": "2022-12-12T22:35:13.815Z"
                  },
                  {
                    "id": "086b0d53-b311-4441-aaf3-935646f03d4d",
                    "name": "Battery 2",
                    "begin": "2022-07-12T16:31:47.254Z",
                    "end": "2022-10-13T04:05:10.044Z"
                  }
                ]
                """;
        List<Outage> expected = objectMapper.readValue(expectedJson, new TypeReference<List<Outage>>() {
        });

        List<Outage> actual = dataService.processData(outages, siteInfo);

        assertEquals(expected, actual);
    }
}
