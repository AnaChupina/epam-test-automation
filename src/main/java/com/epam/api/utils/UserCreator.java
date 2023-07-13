package com.epam.api.utils;

import com.epam.api.builders.UserBuilder;
import com.epam.api.model.User;
import com.google.gson.Gson;

import static com.epam.api.utils.UserDataGenerator.*;

public class UserCreator {

    public static String createJsonUserObject(){
        User user = new UserBuilder()
                .setId(USER_ID)
                .setUsername(USERNAME)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setPassword(PASSWORD)
                .setPhone(PHONE)
                .setUserStatus(USER_STATUS)
                .build();
        return new Gson().toJson(user);
    }
}
