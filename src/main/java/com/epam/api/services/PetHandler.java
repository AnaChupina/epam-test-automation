package com.epam.api.services;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.io.File;

import static com.epam.api.constant.Endpoint.*;
import static io.restassured.RestAssured.given;

public class PetHandler {
    public ValidatableResponse addNewPetToStore(String pet) {
        return given()
                .body(pet)
                .when()
                .contentType (ContentType.JSON)
                .post(PET_PATH)
                .then().log().all();
    }
    public ValidatableResponse findPetsByStatus(String status){
        return given()
                .param("status",status)
                .when()
                .get(PET_FIND_BY_STATUS_PATH + status)
                .then().log().all();
    }
    public ValidatableResponse updateAnExistingPet(String updatedPet){
        return given()
                .body(updatedPet)
                .when()
                .contentType (ContentType.JSON)
                .post(PET_PATH)
                .then().log().all();
    }

    public ValidatableResponse findPetById(int petId) {
        return given()
                .when()
                .get(  PET_PATH + "/" + petId)
                .then().log().all();
    }
    public ValidatableResponse deletePet(int petId){
        return given()
                .when()
                .delete(PET_PATH + "/" + petId)
                .then().log().all();
    }
    public ValidatableResponse uploadsPetImage(File petImage, int petId){
        return given()
                .contentType("multipart/form-data")
                .multiPart("additionalMetadata","data")
                .multiPart("file", petImage)
                .when()
                .post(PET_PATH + "/" + petId + UPLOAD_IMAGE_PATH)
                .then().log().all();
    }

}
