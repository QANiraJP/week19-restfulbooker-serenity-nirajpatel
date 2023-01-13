package com.restful.booker.cucumber.steps;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.restfulinfo.RestfulSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyCURDSteps {
    static ValidatableResponse response;
    static int id;
    static int totalprice;

    static String token;

    @Steps
    RestfulSteps restfulSteps;
    @When("^I send POST request to create a new booking with firstName\"([^\"]*)\", lastName\"([^\"]*)\", totalprice \"([^\"]*)\", depositpaid \"([^\"]*)\", checkin \"([^\"]*)\", checkout\"([^\"]*)\" additionalneeds \"([^\"]*)\"$")
    public void iSendPOSTRequestToCreateANewBookingWithFirstNameLastNameTotalpriceDepositpaidCheckinCheckoutAdditionalneeds(String firstName, String lastName, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        BookingPojo.BookingDates bookingdates = new BookingPojo.BookingDates();
        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(checkout);
        totalprice = 400;
        response = restfulSteps.createBooking(firstName, lastName, totalprice, depositpaid, bookingdates, additionalneeds);
        id = response.extract().path("bookingid");
    }

    @And("^I send a Put request with  firstName\"([^\"]*)\", lastName\"([^\"]*)\", totalprice \"([^\"]*)\", depositpaid \"([^\"]*)\", checkin \"([^\"]*)\", checkout\"([^\"]*)\" additionalneeds \"([^\"]*)\"$")
    public void iSendAPutRequestWithFirstNameLastNameTotalpriceDepositpaidCheckinCheckoutAdditionalneeds(String firstName, String lastName, String _totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        response = restfulSteps.getTokken();
        token = response.extract().path("token");
        totalprice = 120;
        BookingPojo.BookingDates bookingdates = new BookingPojo.BookingDates();
        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(checkout);
        response = restfulSteps.updateBookingWithID(id, token, firstName, lastName, totalprice, depositpaid, bookingdates, additionalneeds);
    }

    @And("^The totalprice \"([^\"]*)\" is successfully updated$")
    public void theTotalpriceIsSuccessfullyUpdated(String _id) {
        response = restfulSteps.getSingleBookingIDs(id);
        HashMap<String, ?> booking = response.extract().path("");
        Assert.assertThat(booking, hasValue(totalprice));
    }

    @Then("^The booking is successfully deleted from the record$")
    public void theBookingIsSuccessfullyDeletedFromTheRecord() {
        restfulSteps.getSingleBookingIDs(id).statusCode(404);
    }

    @Then("^I verify that new booking is created by id$")
    public void iVerifyThatNewBookingIsCreatedById() {
        restfulSteps.getSingleBookingIDs(id);
    }

    @And("^I send delete booking by id$")
    public void iSendDeleteBookingById() {
        restfulSteps.deleteABookingID(id, token).statusCode(201);
    }
}
