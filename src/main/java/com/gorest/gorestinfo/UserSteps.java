package com.gorest.gorestinfo;

import com.gorest.constants.EndPoints;
import com.gorest.constants.Path;
import com.gorest.models.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {

    @Step("Creating User with name : {0}, email: {1}, gender: {2}, status: {3}")
    public ValidatableResponse createUsers(String name, String email, String gender,
                                             String status) {
        UserPojo userPojo= new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .body(userPojo)
                .when()
                .post(Path.USERS)
                .then();
    }
    @Step("Getting the User information with userName: {0}")
    public HashMap<String, Object> getUserInfoByName(String name){
        String p1 = "findAll{it.name == '";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
    }
    @Step("Updating User with UserID : {0}, name: {1}, email: {2}, gender: {3},status: {4}")
    public ValidatableResponse updateUsers(int userID,String name, String email, String gender,
                                           String status) {
        UserPojo userPojo= new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .pathParam("userID", userID)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }
    @Step("Deleting User information with userID: {0}")
    public ValidatableResponse deleteUser(int userID) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .pathParam("userID", userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }

    @Step("Getting User information with UserID: {0}")
    public ValidatableResponse getUserById(int userID){
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }
}
