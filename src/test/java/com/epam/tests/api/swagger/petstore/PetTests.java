package com.epam.tests.api.swagger.petstore;

import com.epam.api.services.PetHandle;
import com.epam.api.utils.PetCreator;
import com.epam.api.utils.PetStatus;
import com.epam.api.utils.TestDataReader;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.File;

import static com.epam.api.config.Configuration.*;

public class PetTests extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(PetTests.class);
    private String pet;
    private String updatedPet;
    private static Response response;
    private PetHandle petHandle;
    private final int petId = Integer.valueOf(TestDataReader.getTestData("pet.id"));
    private final String petName = TestDataReader.getTestData("pet.name");
    private final String updatedPetName = "Doggie";
    private final String imageLocation = "src/test/resources/sobaka.jpeg";
    private final String imageFileName = "sobaka.jpeg";
    private final Integer imageFileSize = 148538;
    private Boolean deleteTestFlag = false;

    @BeforeEach
    public void setUp() {
        pet = PetCreator.createJsonPetObject();
        petHandle = new PetHandle();
        response = petHandle.addNewPetToStore(pet, petName);
        LOGGER.info("Inside SwaggerPetTests beforeEach ");
        LOGGER.info("Pet with petId=1 was created ");
    }
    @Test
    @DisplayName("api_test_pet_1")
    public void addNewPetToStoreTest(){
        LOGGER.info("Inside addNewPetToStoreTest test ");
       response = petHandle.addNewPetToStore(pet, petName);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_pet_2")
    public void updateAnExistingPetTest(){
        updatedPet = PetCreator.createJsonPetObject(updatedPetName);
        response = petHandle.updateAnExistingPet(updatedPet, updatedPetName);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_pet_3")
    public void findPetsByStatusAvailableTest(){
        response = petHandle.findPetsByStatus(String.valueOf(PetStatus.AVAILABLE));
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_pet_4")
    public void findPetByIDTest(){
        response = petHandle.findPetById(petId, petName);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_pet_5")
    public void deletePetTest(){
        deleteTestFlag = true;
        response = petHandle.deletePet(petId);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_pet_6")
    public void uploadsPetImageTest(){
        File petImage = new File(imageLocation);
        response = petHandle.uploadsPetImage(petImage, petId, imageFileName, imageFileSize);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerPetTests afterEach ");
        if (!deleteTestFlag) {
            response = petHandle.deletePet(petId);
        }
    }
}
