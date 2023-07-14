package com.epam.api.utils;

public class PetDataGenerator {
    protected static final Integer PET_ID = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","pet.id"));
    protected static final Integer CATEGORY_ID = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","category.id"));
    protected static final String CATEGORY_NAME = FileHandler.getDataFromProperties("petstoretestdata.properties","category.name");
    protected static final String PET_NAME = FileHandler.getDataFromProperties("petstoretestdata.properties","pet.name");
    protected static final String PHOTO_URL = FileHandler.getDataFromProperties("petstoretestdata.properties","pet.photoUrl");
    protected static final Integer TAG_ID = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","tag.id"));
    protected static final String TAG_NAME = FileHandler.getDataFromProperties("petstoretestdata.properties","tag.name");
    protected static final String PET_STATUS = FileHandler.getDataFromProperties("petstoretestdata.properties","pet.status");
}
