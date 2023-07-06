package com.epam.tests.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static com.epam.api.config.Configuration.BASE_PATH_PET_STORE;
import static com.epam.api.config.Configuration.BASE_URL_PET_STORE;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseAPITest {
    @BeforeAll
    public void beforeAll(){
        RestAssured.baseURI = BASE_URL_PET_STORE;
        RestAssured.basePath = BASE_PATH_PET_STORE;
    }
    @AfterAll
    public void afterAll(){
        RestAssured.reset();
    }
}
