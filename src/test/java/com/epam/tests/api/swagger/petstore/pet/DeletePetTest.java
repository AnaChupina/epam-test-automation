package com.epam.tests.api.swagger.petstore.pet;

import com.epam.api.utils.ObjectToJsonConvertor;
import com.epam.api.utils.FileHandler;
import com.epam.api.utils.PetDataGenerator;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;


public class DeletePetTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(PetTest.class);
    private static Response response;
    private final int petId = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","pet.id"));
    private final String petName = FileHandler.getDataFromProperties("petstoretestdata.properties","pet.name");

    @BeforeEach
    public void setUp() {
        String pet = ObjectToJsonConvertor.convertObjectToJson(PetDataGenerator.createPet());
        response = petHandler.addNewPetToStore(pet)
                .body("name", equalTo(petName))
                .extract().response();
        LOGGER.info("Inside SwaggerPetTests beforeEach ");
        LOGGER.info("Pet with petId=1 was created ");
    }
    @Test
    @DisplayName("Delete a user by their ID")
    public void deletePetTest(){
        response = petHandler.deletePet(petId)
                .body("message", equalTo("1"))
                .extract().response();;
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
}
