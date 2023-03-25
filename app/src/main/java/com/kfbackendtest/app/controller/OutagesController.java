package com.kfbackendtest.app.controller;

import com.kfbackendtest.app.model.Outage;
import com.kfbackendtest.app.model.SiteInfo;
import com.kfbackendtest.app.service.DataService;
import com.kfbackendtest.app.service.InterviewTestMockAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OutagesController {

    @Autowired
    DataService dataService;
    @Autowired
    InterviewTestMockAPI interviewTestMockAPI;

    @PostMapping(value = "/outages")
    private void handleOutages(@RequestBody String siteId) {

        // Get outages
        List<Outage> outages = interviewTestMockAPI.getOutages();

        // Get site info
        SiteInfo siteInfo = interviewTestMockAPI.getSiteInfo(siteId);

        // Process data into accepted data to post
        List<Outage> processedOutages = dataService.processData(outages, siteInfo);

        // Post data
        interviewTestMockAPI.postSiteOutage(siteId, processedOutages);
    }
}
