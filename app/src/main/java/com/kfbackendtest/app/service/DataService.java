package com.kfbackendtest.app.service;

import com.kfbackendtest.app.model.Device;
import com.kfbackendtest.app.model.Outage;
import com.kfbackendtest.app.model.SiteInfo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    public List<Outage> processData(List<Outage> outages, SiteInfo siteInfo) {
        Date earliestBegin = parseDate("2022-01-01T00:00:00.000Z");

        List<Outage> collect = outages.stream().filter(outage -> {

            // Filters out any outages that began before `2022-01-01T00:00:00.000Z`
            Date begin = parseDate(outage.getBegin());
            if (begin.before(earliestBegin)) {
                return false;
            }
            // or don't have an ID that is in the list of devices in the site information
            if (!siteInfo.getDevices().stream().anyMatch(x -> x.getId().equals(outage.getId()))) {
                return false;
            }
            return true;
        }).map(outage -> {

            // attach the display name of the device in the site information to each appropriate outage
            Device device = siteInfo.getDevices().stream().filter(x -> x.getId().equals(outage.getId())).findFirst().get();
            outage.setName(device.getName());
            return outage;
        }).collect(Collectors.toList());

        return collect;
    }

    private Date parseDate(String dateString) {
        TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(dateString);
        Instant i = Instant.from(ta);
        return Date.from(i);
    }
}
