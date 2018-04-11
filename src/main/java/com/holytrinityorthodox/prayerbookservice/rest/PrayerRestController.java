package com.holytrinityorthodox.prayerbookservice.rest;
import com.holytrinityorthodox.prayerbookservice.model.PrayerModel;
import com.holytrinityorthodox.prayerbookservice.service.PrayerService;
import com.holytrinityorthodox.prayerbookservice.util.ILoggerProxy;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrayerRestController {

    @Autowired
    ILoggerProxy logger;

    @Autowired
    PrayerService service;

    @Value("${base.lent}")
    private String defaultLent;

    @Value("${base.address}")
    private String serverAddress;

    @RequestMapping("/pbs")
    public PrayerModel greeting(@RequestParam(value="d", required=true) String day,
                                @RequestParam(value="m", required=true) String month,
                                @RequestParam(value="y", required=true) String year) {
        try {
            return service.getJson(day, month, year, defaultLent, serverAddress);
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
            return null;
        }
    }
}
