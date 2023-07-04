package com.epam.api.utils;

import com.epam.api.model.Category;
import com.epam.api.model.Pet;
import com.epam.api.model.TagDto;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class PetCreator {
    private static final Integer PET_ID = Integer.valueOf(TestDataReader.getTestData("pet.id"));
    private static final Integer CATEGORY_ID = Integer.valueOf(TestDataReader.getTestData("category.id"));
    private static final String CATEGORY_NAME = TestDataReader.getTestData("category.name");
    private static final String PET_NAME = TestDataReader.getTestData("pet.name");
    private static final String PHOTO_URL = TestDataReader.getTestData("pet.photoUrl");
    private static final Integer TAG_ID = Integer.valueOf(TestDataReader.getTestData("tag.id"));
    private static final String TAG_NAME = TestDataReader.getTestData("tag.name");
    private static final String PET_STATUS = TestDataReader.getTestData("pet.status");

    public static String createJsonPetObject(){
        Category category = new Category(CATEGORY_ID, CATEGORY_NAME);

        TagDto tag1 = new TagDto(TAG_ID, TAG_NAME);

        List<TagDto> tags = Arrays.asList(tag1);

        List<String> photoUrls = Arrays.asList(PHOTO_URL);
        Pet pet = new Pet(PET_ID, category, PET_NAME, photoUrls, tags, PET_STATUS);
        return new Gson().toJson(pet);
    }
    public static String createJsonPetObject(String name){
        Category category = new Category(CATEGORY_ID, CATEGORY_NAME);

        TagDto tag1 = new TagDto(TAG_ID, TAG_NAME);

        List<TagDto> tags = Arrays.asList(tag1);

        List<String> photoUrls = Arrays.asList(PHOTO_URL);
        Pet pet = new Pet(PET_ID, category, name, photoUrls, tags, PET_STATUS);
        return new Gson().toJson(pet);
    }
}
