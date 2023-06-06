package com.restful.booker.restfulinfo;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.CustomerPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class CustomerSteps{


    @Step("Creating booking with firstName: {0}, lastName: {1}, email: {2}, totalprice: {3}, depositpaid: {4}, bookingdates: {5}, additionalneeds: {6}")
    public ValidatableResponse createBooking(String firstName, String lastName, String email, int totalprice,
                                             boolean depositpaid, HashMap<String, Object> bookingdates, String additionalneeds){

        CustomerPojo customerPojo = CustomerPojo.getCustomersPojo(firstName,lastName, email, totalprice,depositpaid,
                                                                      bookingdates,additionalneeds);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Connection","keep-alive")
                .body(customerPojo)
                .when()
                .post(EndPoints.GET_ALL_BOOKING)
                .then().log().all();

    }

    @Step("Retrieving all booking Ids}")
    public ValidatableResponse getAllBookingIds(){
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_BOOKING)
                .then().log().all();
    }

    @Step("Retrieving single booking Id: {0}")
    public ValidatableResponse getSingleBookingId(String token,int id){
        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .pathParam("bookingId",id)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then().log().all();
    }

    @Step("Updating booking with bookingId: {0}, name: {1}")
    public ValidatableResponse updateBooking(String token,int id,String name){

        name = name+ "_updated";

        CustomerPojo customerPojo = CustomerPojo.getCustomerPojo(name);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("bookingId", id)
                .body(customerPojo)
                .when()
                .patch(EndPoints.UPDATE_BOOKING_BY_ID)
                .then().log().all();

    }


    @Step("Deleting booking with bookingId: {0}")
    public ValidatableResponse deleteBooking(String token,int id){
        return SerenityRest.given()
                .header("Content-Type", "application-json")
                .header("Cookie","token=" + token)
                .header("Accept", "application/json")
               .pathParam("bookingId", id)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then().log().all();
    }

    @Step("Getting the Booking information with firstName : {0}")
    public HashMap<String, Object> getBookingInfoByFirstName(String token,String firstName){
        String s1 = "findAll{it.firstname == '";
        String s2 = "'}.get(0)";
        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .header("Accept", "application/json")
                .queryParam("firstName", firstName)
                .when()
                .get(EndPoints.GET_ALL_BOOKING)
                .then().log().all()
                .statusCode(200)
                .extract()
                .path(s1 + firstName + s2);
    }


}
