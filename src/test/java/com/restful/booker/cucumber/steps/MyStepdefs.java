package com.restful.booker.cucumber.steps;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.restfulinfo.RestfulSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class MyStepdefs {

    static int id;
    static String token;
    static ValidatableResponse response;
    static int totalprice;

    @Steps
    RestfulSteps restfulSteps;
    @Given("^User is on restful booker website$")
    public void userIsOnRestfulBookerWebsite() {
    }

    @When("^I send a GET request to booking endpoint$")
    public void userSendAGETRequestToBookingEndpoint() {
        response = restfulSteps.getAllBookingIDs();
    }

    @Then("^I must get back a valid response code 200$")
    public void userMustGetBackAValidResposeCode() {
        response.statusCode(200);
    }

    @When("^I send a POST request to create a new booking with firstName\"([^\"]*)\", lastName\"([^\"]*)\", totalprice \"([^\"]*)\", depositpaid \"([^\"]*)\", checkin \"([^\"]*)\", checkout\"([^\"]*)\" additionalneeds \"([^\"]*)\"$")
    public void iSendAPOSTRequestToCreateANewBookingWihtFirstNameLastNameTotalpriceDepositpaidCheckinCheckoutAdditionalneeds(String firstName, String lastName, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        BookingPojo.BookingDates bookingdates = new BookingPojo.BookingDates();
        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(checkout);
        response = restfulSteps.createBooking(firstName, lastName, totalprice, depositpaid, bookingdates, additionalneeds);
    }

    @Then("^I must get back a valid response code 201$")
    public void iMustGetBackAValidResponseCode() {
        response.statusCode(200);
        id = response.extract().path("bookingid");
    }

    @And("^I verify that new booking is created by <\"([^\"]*)\">$")
    public void iVerifyThatNewBookingIsCreatedBy(String _id) {
        restfulSteps.getSingleBookingIDs(id).statusCode(200);
    }

    @When("^I send Put request with  firstName\"([^\"]*)\", lastName\"([^\"]*)\", totalprice \"([^\"]*)\", depositpaid \"([^\"]*)\", checkin \"([^\"]*)\", checkout\"([^\"]*)\" additionalneeds \"([^\"]*)\"$")
    public void iSendPutRequestWithFirstNameLastNameTotalpriceDepositpaidCheckinCheckoutAdditionalneeds(String firstName, String lastName, String _totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        response = restfulSteps.getTokken();
        token = response.extract().path("token");
        firstName = "Manisha";
        lastName = "Mavani";
        totalprice = 100;
        BookingPojo.BookingDates bookingdates = new BookingPojo.BookingDates();
        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(checkout);
        response = restfulSteps.updateBookingWithID(id, token, firstName, lastName, totalprice, depositpaid, bookingdates, additionalneeds);
    }

    @When("^I send delete requested id$")
    public void iSendDeleteRequestedId() {
        response = restfulSteps.deleteABookingID(id, token);
    }

    @Then("^I should see the response code 201$")
    public void iShouldSeeTheResponseCode() {
        response.statusCode(201);
        restfulSteps.getSingleBookingIDs(id).statusCode(404);
    }
}
