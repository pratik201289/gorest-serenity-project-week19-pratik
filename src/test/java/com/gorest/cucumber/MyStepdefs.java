package com.gorest.cucumber;

import com.gorest.gorestinfo.UserSteps;
import com.gorest.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class MyStepdefs {
    static ValidatableResponse response;

    static String name = null;
    static String gender;
    static String email= null;
    static String status;
    static int userId;
    @Steps
    UserSteps usersSteps;
    @Given("^I create a new user by providing the information name \"([^\"]*)\" email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iCreateANewUserByProvidingTheInformationNameEmailGenderStatus(String _name, String _email, String gender, String status) {

        name = TestUtils.getRandomValue()+_name;
        email = TestUtils.getRandomValue()+_email;
        response = usersSteps.createUsers(name, email, gender, status).statusCode(201);
        userId = response.extract().path("id");
    }

    @Then("^I get user information by id$")
    public void iGetUserInformationById() {

        usersSteps.getUserById(userId);
    }

    @And("^Update user details by providing the information name \"([^\"]*)\" email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void updateUserDetailsByProvidingTheInformationNameEmailGenderStatus(String _name, String _email, String gender, String status) {
        name = name + "_updated";
//        response = usersSteps.getUserById(userId);
        response = usersSteps.updateUsers(userId, name, email, gender, status).statusCode(200);

    }
    @Then("^Verify user is updated$")
    public void verifyUserIsUpdated() {
        usersSteps.getUserById(userId);
    }

    @Then("^The user id deleted successfully$")
    public void theUserIdDeletedSuccessfully() {
        usersSteps.deleteUser(userId);

    }
}
