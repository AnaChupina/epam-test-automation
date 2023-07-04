package com.epam.api.config;

import com.epam.api.utils.TestDataReader;

public class Configuration {
    public static final String BASE_URL = TestDataReader.getTestData("base.url");
    public static final String BASE_PATH = TestDataReader.getTestData("base.path");

    public static final int STATUS_CODE = Integer.parseInt(TestDataReader.getTestData("success.status"));

}
