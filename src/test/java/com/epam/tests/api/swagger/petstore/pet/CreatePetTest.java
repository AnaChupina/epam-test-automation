package com.epam.tests.api.swagger.petstore.pet;

import com.epam.api.utils.FileHandler;
import com.epam.api.utils.ObjectToJsonConvertor;
import com.epam.api.utils.PetDataGenerator;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;

public class CreatePetTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(PetTest.class);
    private String pet;
    private final int petId = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","pet.id"));
    private static Response response;
    private final String petName = FileHandler.getDataFromProperties("petstoretestdata.properties","pet.name");

    @Test
    @DisplayName("Add a new pet to the store")
    public void addNewPetToStoreTest(){
        pet = ObjectToJsonConvertor.convertObjectToJson(PetDataGenerator.createPet());
        LOGGER.info("JSON request with pet was created");
        LOGGER.info(pet);
        response = petHandler.addNewPetToStore(pet)
                .body("name", equalTo(petName))
                .extract().response();
        LOGGER.info("Request to add new pet to the store was sent and server response was received");
        LOGGER.debug(response.asString());
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerPetTests afterEach ");
        response = petHandler.deletePet(petId)
                .body("message", equalTo("1"))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
        LOGGER.debug(response.asString());
    }
}
