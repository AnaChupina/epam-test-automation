package com.epam.tests.api.swagger.petstore.pet;

import com.epam.api.services.PetHandler;
import com.epam.api.utils.*;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.File;
import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;

public class PetTests extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(PetTests.class);
    private String pet;
    private String updatedPet;
    private static Response response;
    private PetHandler petHandle;
    private final int petId = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","pet.id"));
    private final String petName = FileHandler.getDataFromProperties("petstoretestdata.properties","pet.name");
    private final String updatedPetName = "Doggie";
    private final String imageLocation = "src/test/resources/sobaka.jpeg";
    private final String imageFileName = "sobaka.jpeg";
    private final Integer imageFileSize = 148538;

    @BeforeEach
    public void setUp() {
        pet = ObjectToJsonConvertor.convertObjectToJson(PetDataGenerator.createPet());
        petHandle = new PetHandler();
        response = petHandle.addNewPetToStore(pet)
                .extract().response();
        LOGGER.info("Inside SwaggerPetTests beforeEach ");
        LOGGER.info("Pet with petId=1 was created ");
    }
    @Test
    @DisplayName("api_test_pet_1")
    public void addNewPetToStoreTest(){
        LOGGER.info("Inside addNewPetToStoreTest test ");
       response = petHandle.addNewPetToStore(pet)
               .body("name", equalTo(petName))
               .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("api_test_pet_2")
    public void updateAnExistingPetTest(){
        updatedPet = ObjectToJsonConvertor.convertObjectToJson(PetDataGenerator.createPet(updatedPetName));
        response = petHandle.updateAnExistingPet(updatedPet)
                .body("name", equalTo(updatedPetName))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("api_test_pet_3")
    public void findPetsByStatusAvailableTest(){
        response = petHandle.findPetsByStatus(String.valueOf(PetStatus.AVAILABLE))
//                .body("status", equalTo("available"))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("api_test_pet_4")
    public void findPetByIDTest(){
        response = petHandle.findPetById(petId)
                .body("name", equalTo(petName))
                .extract().response();;
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("api_test_pet_6")
    public void uploadsPetImageTest(){
        File petImage = new File(imageLocation);
        response = petHandle.uploadsPetImage(petImage, petId)
                .body("message", equalTo("additionalMetadata: data\nFile uploaded to " + "./" +
                        imageFileName + ", " + imageFileSize + " bytes"))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerPetTests afterEach ");
        response = petHandle.deletePet(petId)
                .body("message", equalTo("1"))
                .extract().response();;

    }
}
