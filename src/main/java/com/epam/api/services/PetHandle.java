package com.epam.api.services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static com.epam.api.constant.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetHandle {
    private final String deletedPetQuantity = "1";
    public Response addNewPetToStore(String pet, String checkNameInResponse) {
        return given()
                .body(pet)
                .when()
                .contentType (ContentType.JSON)
                .post(PET)
                .then().log().all()
                .body("name", equalTo(checkNameInResponse))
                .extract().response();
    }
    public Response findPetsByStatus(String status){
        return given()
                .param("status",status)
                .when()
                .get(PET_FIND_BY_STATUS + status)
                .then().log().all()
                .extract().response();
    }
    public Response updateAnExistingPet(String updatedPet, String checkNameInResponse){
        return given()
                .body(updatedPet)
                .when()
                .contentType (ContentType.JSON)
                .post(PET)
                .then().log().all()
                .body("name", equalTo(checkNameInResponse))
                .extract().response();
    }

    public Response findPetById(int petId, String checkNameInResponse) {
        return given()
                .when()
                .get(  PET + "/" + petId)
                .then().log().all()
                .body("name", equalTo(checkNameInResponse))
                .extract().response();
    }
    public Response deletePet(int petId){
        return given()
                .when()
                .delete(PET + "/" + petId)
                .then().log().all()
                .body("message", equalTo(deletedPetQuantity))
                .extract().response();
    }
    public Response uploadsPetImage(File petImage, int petId, String fileName, Integer fileSizeInBytes){
        return given()
                .contentType("multipart/form-data")
                .multiPart("additionalMetadata","data")
                .multiPart("file", petImage)
                .when()
                .post(PET + "/" + petId + IMAGE)
                .then().log().all()
                .body("message", equalTo("additionalMetadata: data\nFile uploaded to " + "./" +
                        fileName + ", " + fileSizeInBytes + " bytes"))
                .extract().response();
    }

}
