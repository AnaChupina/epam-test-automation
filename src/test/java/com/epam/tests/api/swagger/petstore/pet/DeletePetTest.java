package com.epam.tests.api.swagger.petstore.pet;

import com.epam.api.services.PetHandle;
import com.epam.api.utils.PetCreator;
import com.epam.api.utils.TestDataReader;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.epam.api.config.Configuration.STATUS_CODE;

public class DeletePetTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(PetTests.class);
    private static Response response;
    private PetHandle petHandle;
    private final int petId = Integer.valueOf(TestDataReader.getTestData("pet.id"));
    private final String petName = TestDataReader.getTestData("pet.name");

    @BeforeEach
    public void setUp() {
        String pet = PetCreator.createJsonPetObject();
        petHandle = new PetHandle();
        response = petHandle.addNewPetToStore(pet, petName);
        LOGGER.info("Inside SwaggerPetTests beforeEach ");
        LOGGER.info("Pet with petId=1 was created ");
    }
    @Test
    @DisplayName("api_test_pet_5")
    public void deletePetTest(){
        response = petHandle.deletePet(petId);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
}
