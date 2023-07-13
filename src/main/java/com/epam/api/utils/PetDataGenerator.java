package com.epam.api.utils;

public class PetDataGenerator {
    protected static final Integer PET_ID = Integer.valueOf(TestDataReader.getTestData("pet.id"));
    protected static final Integer CATEGORY_ID = Integer.valueOf(TestDataReader.getTestData("category.id"));
    protected static final String CATEGORY_NAME = TestDataReader.getTestData("category.name");
    protected static final String PET_NAME = TestDataReader.getTestData("pet.name");
    protected static final String PHOTO_URL = TestDataReader.getTestData("pet.photoUrl");
    protected static final Integer TAG_ID = Integer.valueOf(TestDataReader.getTestData("tag.id"));
    protected static final String TAG_NAME = TestDataReader.getTestData("tag.name");
    protected static final String PET_STATUS = TestDataReader.getTestData("pet.status");
}
