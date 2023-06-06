package com.restful.booker.testsuite;

import com.restful.booker.restfulinfo.CustomerSteps;
import com.restful.booker.restfulinfo.LoginSteps;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
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
public class BookingCRUDTest extends TestBase {


    static String firstName = "Piyush" + TestUtils.getRandomValue();
    static String lastName = "Gorasiya";
    static String email = "piyush" + TestUtils.getRandomValue() + "gorasiya@gmail.com";
    static int totalPrice = 121;
    static boolean depositPaid = true;
    static String additionalNeeds = "Breakfast";
    static int bookingId;

    static String username = "admin";

    static String password = "password123";

    static String token;


    @Steps
    LoginSteps loginSteps;





    @Title("This will create a token")
    @Test
    public void test001(){

        ValidatableResponse response =  loginSteps.createToken(username,password);
        token = response.extract().path("token");
    }


    @Steps
    CustomerSteps customerSteps;


    @Title("This will create a booking")
    @Test
    public void test002() {

        HashMap<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("check in", 2024 - 01 - 01);
        bookingDates.put("checkout", 2025 - 01 - 01);

        ValidatableResponse response = customerSteps.createBooking(firstName, lastName, email, totalPrice, depositPaid, bookingDates, additionalNeeds).statusCode(200);
        bookingId = response.extract().path("bookingid");
    }


    @Title("Verify if the booking has been added to the app")
    @Test
    public void test003(){
        HashMap<String, Object> bookingMap = customerSteps.getBookingInfoByFirstName(token,firstName);
        Assert.assertThat(bookingMap, hasValue(firstName));
        bookingId = (int) bookingMap.get("bookingid");
    }

    @Title("This will retrieve a booking")
    @Test
    public void test004(){
        customerSteps.getSingleBookingId(token,bookingId).statusCode(200);
    }

    @Title("This will update a booking")
    @Test
    public void test005(){
        //update the info with PATCH
        customerSteps.updateBooking(token,bookingId,firstName).statusCode(200);

        //Verify it is updated
        HashMap<String, Object> bookingMap = customerSteps.getBookingInfoByFirstName(token,firstName);
        Assert.assertThat(bookingMap, hasValue(firstName));
    }

    @Title("This will delete a booking")
    @Test
    public void test006(){
        //delete the booking
        customerSteps.deleteBooking(token,bookingId).statusCode(201);

        //verify the booking is deleted
        customerSteps.getSingleBookingId(token,bookingId)
                .statusCode(404);
    }

    @Title("This will retrieve all bookings")
    @Test
    public void test007(){
        customerSteps.getAllBookingIds().statusCode(200);
    }
}
