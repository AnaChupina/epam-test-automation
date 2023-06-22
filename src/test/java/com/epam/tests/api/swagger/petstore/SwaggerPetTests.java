package com.epam.tests.api.swagger.petstore;

import com.epam.utils.api.PetCreator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SwaggerPetTests {
    private static final Logger LOGGER = LogManager.getLogger(SwaggerPetTests.class);

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
        RequestSpecification requestSpec = given()
                .contentType(ContentType.JSON)
                .body(PetCreator.createJsonPetObject());

        requestSpec.when()
                        .post("/pet");
        LOGGER.info("Inside SwaggerPetTests beforeEach ");
        LOGGER.info("Pet with petId=1 was created ");
    }
    @Test
    @DisplayName("api_test_pet_1")
    public void addNewPetToStoreTest_petId_1(){
        LOGGER.info("Inside addNewPetToStoreTest test ");
        given()
                .body(PetCreator.createJsonPetObject())
                .when()
                .contentType (ContentType.JSON)
                .post("/pet")
                .then().log().all()
                .body("name", equalTo("doggie"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_pet_2")
    public void updateAnExistingPetTest_petId_1(){
        given()
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"Doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .contentType (ContentType.JSON)
                .put("/pet")
                .then().log().all()
                .body("name", equalTo("Doggie"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_pet_3")
    public void findPetsByStatus_Available(){
        given()
                .param("status","available")
                .when()
                .get("/pet/findByStatus?status=available")
                .then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_pet_4")
    public void findPetByIDTest_petId_1(){
        given()
                .when()
                .get("/pet/1")
                .then().log().all()
                .body("name", equalTo("doggie"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_pet_5")
    public void deletePetTest_petId_1(){
        given()
                .when()
                .delete("/pet/1")
                .then().log().all()
                .body("message", equalTo("1"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_pet_6")
    public void uploadsPetImageTest_petId_1(){
        String location = "src/test/resources/sobaka.jpeg";
        File petImage = new File(location);
        given()
                .contentType("multipart/form-data")
                .multiPart("additionalMetadata","data")
                .multiPart("file", petImage)
                .when()
                .post("/pet/1/uploadImage")
                .then().log().all()
                .body("message", equalTo("additionalMetadata: data\nFile uploaded to ./sobaka.jpeg, 148538 bytes"))
                .assertThat().statusCode(200);
    }

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerPetTests afterEach ");
        RestAssured.reset();
    }
}
