package com.epam.api.utils;

public class UserDataGenerator {
    protected static final Integer USER_ID = Integer.valueOf(TestDataReader.getTestData("user.id"));
    protected static final String USERNAME = TestDataReader.getTestData("username");
    protected static final String FIRST_NAME = TestDataReader.getTestData("first.name");
    protected static final String LAST_NAME = TestDataReader.getTestData("last.name");
    protected static final String EMAIL = TestDataReader.getTestData("email");
    protected static final String PASSWORD = TestDataReader.getTestData("password");
    protected static final String PHONE = TestDataReader.getTestData("phone");
    protected static final Integer USER_STATUS = Integer.valueOf(TestDataReader.getTestData("user.status"));
}
