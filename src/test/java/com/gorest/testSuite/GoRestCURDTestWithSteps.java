package com.gorest.testSuite;

import com.gorest.gorestinfo.UserSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class GoRestCURDTestWithSteps extends TestBase {

    static String name = "PrimUser" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static String gender = "male";
    static String status = "active";

    static int userID;
    @Steps
    UserSteps userSteps;


    @Title("This will create a new Users")
    @Test
    public void test001() {

        ValidatableResponse response = userSteps.createUsers(name, email, gender, status);
        response.log().all().statusCode(201);


    }
    @Title("Verify id the User was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> userMap = userSteps.getUserInfoByName(name);
        Assert.assertThat(userMap, hasValue(name));
        userID = (int) userMap.get("id");
        System.out.println("Userid : " + userID);
    }
    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        userSteps.updateUsers(userID,name, email, gender, status)
                .log().all().statusCode(200);
        // Verification of updated name
        HashMap<String, Object> studentMap = userSteps.getUserInfoByName(name);
        Assert.assertThat(studentMap, hasValue(name));

    }
    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004() {

        userSteps.deleteUser(userID).statusCode(204);
        //verify the deleted user fetch single record data method
        userSteps.getUserById(userID).statusCode(404);

    }

    }
