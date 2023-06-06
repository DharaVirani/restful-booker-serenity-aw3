package com.restful.booker.restfulinfo;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.LoginPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class LoginSteps {

    @Step
    public ValidatableResponse createToken(String username, String password){

        LoginPojo loginPojo = LoginPojo.getLoginDetails(username, password);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .when()
                .body(loginPojo)
                .post(EndPoints.LOGIN)
                .then().log().all();

    }

//    @Step("Creating Token")
//    public ValidatableResponse createToken(String username, String password){
//        LoginPojo authorisationPojo = new LoginPojo();
//        authorisationPojo.setUsername(username);
//        authorisationPojo.setPassword(password);
//        return SerenityRest.given().log().all()
//                .header("Content-Type","application/json")
//                .when()
//                .body(authorisationPojo)
//                .post(EndPoints.LOGIN)
//                .then();
//    }
}
