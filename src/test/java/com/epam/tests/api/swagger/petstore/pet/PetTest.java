package com.epam.tests.api.swagger.petstore.pet;

import com.epam.api.utils.*;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.File;
import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;

public class PetTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(PetTest.class);
    private String pet;
    private String updatedPet;
    private static Response response;
    private final int petId = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","pet.id"));
    private final String petName = FileHandler.getDataFromProperties("petstoretestdata.properties","pet.name");
    private final String updatedPetName = "Doggie";
    private final String imageLocation = "src/test/resources/sobaka.jpeg";
    private final String imageFileName = "sobaka.jpeg";
    private final Integer imageFileSize = 148538;

    @BeforeEach
    public void setUp() {
        pet = ObjectToJsonConvertor.convertObjectToJson(PetDataGenerator.createPet());
        LOGGER.info("JSON request with pet was created");
        LOGGER.info(pet);
        response = petHandler.addNewPetToStore(pet)
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
        LOGGER.info("Request to create the pet was sent to the server");
        LOGGER.debug(response.asString());
    }

    @Test
    @DisplayName("Updates a pet in the store with form data")
    public void updateAnExistingPetTest(){
        updatedPet = ObjectToJsonConvertor.convertObjectToJson(PetDataGenerator.createPet(updatedPetName));
        response = petHandler.updateAnExistingPet(updatedPet)
                .body("name", equalTo(updatedPetName))
                .extract().response();
        LOGGER.info("Request to update the pet was sent and server response was received");
        LOGGER.debug(response.asString());
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
        petHandler.findPetById(petId)
                .body("name", equalTo(updatedPetName))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }

    @Test
    @DisplayName("Find a pet by status = available")
    public void findPetsByStatusAvailableTest(){
        response = petHandler.findPetsByStatus(String.valueOf(PetStatus.AVAILABLE))
                .extract().response();
        LOGGER.info("Request to find pets by status was sent and server response was received");
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }

    @Test
    @DisplayName("Find pet by ID = 1")
    public void findPetByIDTest(){
        response = petHandler.findPetById(petId)
                .body("name", equalTo(petName))
                .extract().response();
        LOGGER.info("Request to find the pet by ID was sent and server response was received");
        LOGGER.debug(response.asString());
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }

    @Test
    @DisplayName("Uploads  image of pet")
    public void uploadsPetImageTest(){
        File petImage = new File(imageLocation);
        response = petHandler.uploadsPetImage(petImage, petId)
                .body("message", equalTo("additionalMetadata: data\nFile uploaded to " + "./" +
                        imageFileName + ", " + imageFileSize + " bytes"))
                .extract().response();
        LOGGER.info("Request to upload pet image was sent and server response was received");
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
