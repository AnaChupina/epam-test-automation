package com.epam.api.utils;

import com.epam.api.builders.PetBuilder;
import com.epam.api.model.CategoryDTO;
import com.epam.api.model.Pet;
import com.epam.api.model.TagDto;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import static com.epam.api.utils.PetDataGenerator.*;

public class PetCreator {

    public static String createJsonPetObject(){
        CategoryDTO category = new CategoryDTO(CATEGORY_ID, CATEGORY_NAME);

        TagDto tag1 = new TagDto(TAG_ID, TAG_NAME);

        List<TagDto> tags = Arrays.asList(tag1);

        List<String> photoUrls = Arrays.asList(PHOTO_URL);
        Pet pet = new PetBuilder()
                .setId(PET_ID)
                .setCategory(category)
                .setName(PET_NAME)
                .setPhotoUrls(photoUrls)
                .setTags(tags)
                .setStatus(PET_STATUS)
                .build();
        return new Gson().toJson(pet);
    }
    public static String createJsonPetObject(String name){
        CategoryDTO category = new CategoryDTO(CATEGORY_ID, CATEGORY_NAME);

        TagDto tag1 = new TagDto(TAG_ID, TAG_NAME);

        List<TagDto> tags = Arrays.asList(tag1);

        List<String> photoUrls = Arrays.asList(PHOTO_URL);
        Pet pet = new PetBuilder()
                .setId(PET_ID)
                .setCategory(category)
                .setName(name)
                .setPhotoUrls(photoUrls)
                .setTags(tags)
                .setStatus(PET_STATUS)
                .build();
        return new Gson().toJson(pet);
    }
}
