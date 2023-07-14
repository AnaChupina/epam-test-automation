package com.epam.api.utils;

import com.epam.api.builders.PetBuilder;
import com.epam.api.model.CategoryDTO;
import com.epam.api.model.Pet;
import com.epam.api.model.TagDto;

import java.util.Arrays;
import java.util.List;

public class PetDataGenerator {
    protected static final Integer PET_ID = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","pet.id"));
    protected static final Integer CATEGORY_ID = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","category.id"));
    protected static final String CATEGORY_NAME = FileHandler.getDataFromProperties("petstoretestdata.properties","category.name");
    protected static final String PET_NAME = FileHandler.getDataFromProperties("petstoretestdata.properties","pet.name");
    protected static final String PHOTO_URL = FileHandler.getDataFromProperties("petstoretestdata.properties","pet.photoUrl");
    protected static final Integer TAG_ID = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","tag.id"));
    protected static final String TAG_NAME = FileHandler.getDataFromProperties("petstoretestdata.properties","tag.name");
    protected static final String PET_STATUS = FileHandler.getDataFromProperties("petstoretestdata.properties","pet.status");
    public static Pet createPet(){
        CategoryDTO category = new CategoryDTO(CATEGORY_ID, CATEGORY_NAME);

        TagDto tag1 = new TagDto(TAG_ID, TAG_NAME);

        List<TagDto> tags = Arrays.asList(tag1);

        List<String> photoUrls = Arrays.asList(PHOTO_URL);
        return new PetBuilder()
                .setId(PET_ID)
                .setCategory(category)
                .setName(PET_NAME)
                .setPhotoUrls(photoUrls)
                .setTags(tags)
                .setStatus(PET_STATUS)
                .build();
    }
    public static Pet createPet (String name){
        CategoryDTO category = new CategoryDTO(CATEGORY_ID, CATEGORY_NAME);

        TagDto tag1 = new TagDto(TAG_ID, TAG_NAME);

        List<TagDto> tags = Arrays.asList(tag1);

        List<String> photoUrls = Arrays.asList(PHOTO_URL);
        return new PetBuilder()
                .setId(PET_ID)
                .setCategory(category)
                .setName(name)
                .setPhotoUrls(photoUrls)
                .setTags(tags)
                .setStatus(PET_STATUS)
                .build();
    }
}
