package com.epam.utils.api;

import com.epam.model.swagger.petstore.Category;
import com.epam.model.swagger.petstore.Pet;
import com.epam.model.swagger.petstore.Tag;

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

    public static String createPet(){
        Category category = new Category(CATEGORY_ID, CATEGORY_NAME);

        Tag tag1 = new Tag(TAG_ID, TAG_NAME);

        List<Tag> tags = Arrays.asList(tag1);

        List<String> photoUrls = Arrays.asList(PHOTO_URL);
        Pet pet = new Pet(PET_ID, category, PET_NAME, photoUrls, tags, PET_STATUS);
        return pet.toString();
    }
}
