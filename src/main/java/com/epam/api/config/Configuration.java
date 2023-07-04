package com.epam.api.config;

import com.epam.api.utils.TestDataReader;

public class Configuration {
    public static final String BASE_URL_PET_STORE = TestDataReader.getTestData("base.url");
    public static final String BASE_PATH_PET_STORE = TestDataReader.getTestData("base.path");

    public static final String BASE_URL_REPORT_PORTAL = TestDataReader.getTestData("base.url.report.portal");
    public static final String BASE_PATH_REPORT_PORTAL = TestDataReader.getTestData("base.path.report.portal");

    public static final int STATUS_CODE = Integer.parseInt(TestDataReader.getTestData("success.status"));


}
