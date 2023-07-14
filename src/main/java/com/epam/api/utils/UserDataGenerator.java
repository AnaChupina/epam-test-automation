package com.epam.api.utils;

import com.epam.api.builders.UserBuilder;
import com.epam.api.model.User;

public class UserDataGenerator {
    protected static final Integer USER_ID = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","user.id"));
    protected static final String USERNAME = FileHandler.getDataFromProperties("petstoretestdata.properties","username");
    protected static final String FIRST_NAME = FileHandler.getDataFromProperties("petstoretestdata.properties","first.name");
    protected static final String LAST_NAME = FileHandler.getDataFromProperties("petstoretestdata.properties","last.name");
    protected static final String EMAIL = FileHandler.getDataFromProperties("petstoretestdata.properties","email");
    protected static final String PASSWORD = FileHandler.getDataFromProperties("petstoretestdata.properties","password");
    protected static final String PHONE = FileHandler.getDataFromProperties("petstoretestdata.properties","phone");
    protected static final Integer USER_STATUS = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","user.status"));
    public static User createUser(){
        return new UserBuilder()
                .setId(USER_ID)
                .setUsername(USERNAME)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setPassword(PASSWORD)
                .setPhone(PHONE)
                .setUserStatus(USER_STATUS)
                .build();
    }
}
