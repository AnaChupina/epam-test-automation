package com.epam.api.config;

import com.epam.api.utils.FileHandler;

public class Configuration {
    public static final String BASE_URL_PET_STORE = FileHandler.getDataFromProperties("petstoreconnection.properties","base.url");
    public static final String BASE_PATH_PET_STORE = FileHandler.getDataFromProperties("petstoreconnection.properties","base.path");

    public static final String BASE_URL_REPORT_PORTAL = FileHandler.getDataFromProperties("reportportalconnection.properties", "base.url.report.portal");
    public static final String BASE_PATH_REPORT_PORTAL = FileHandler.getDataFromProperties("reportportalconnection.properties", "base.path.report.portal");


}
