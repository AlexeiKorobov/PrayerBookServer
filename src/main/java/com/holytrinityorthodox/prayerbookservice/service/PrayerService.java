package com.holytrinityorthodox.prayerbookservice.service;

import com.holytrinityorthodox.prayerbookservice.model.PrayerModel;

public interface PrayerService {
	PrayerModel getJson(String day, String month, String year, String defaultLent, String address) throws Exception;
}
