package com.epam.utils.api;

import com.epam.model.swagger.petstore.User;
import com.google.gson.Gson;

public class UserCreator {
    private static final Integer USER_ID = Integer.valueOf(TestDataReader.getTestData("user.id"));
    private static final String USERNAME = TestDataReader.getTestData("username");
    private static final String FIRST_NAME = TestDataReader.getTestData("first.name");
    private static final String LAST_NAME = TestDataReader.getTestData("last.name");
    private static final String EMAIL = TestDataReader.getTestData("email");
    private static final String PASSWORD = TestDataReader.getTestData("password");
    private static final String PHONE = TestDataReader.getTestData("phone");
    private static final Integer USER_STATUS = Integer.valueOf(TestDataReader.getTestData("user.status"));

    public static String createJsonUserObject(){
        User user = new User(USER_ID, USERNAME, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHONE, USER_STATUS);
        return new Gson().toJson(user);
    }
}
