package com.holytrinityorthodox.service;

import com.holytrinityorthodox.model.PrayerModel;

public interface IPrayerService {
	public PrayerModel getJson(String day, String month, String year, String defaultLent, String address) throws Exception;
}
