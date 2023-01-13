package com.restful.booker.datadriventests;

import com.restful.booker.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class RestfulBookerWithTags extends TestBase {
    @WithTag("feature:NEGATIVE")
    @Title("Provide a 404 status code when incorrect HTTP method is used to access resource")
    @Test
    public void invalidMethod() {
        SerenityRest.rest()
                .given()
                .when()
                .patch()
                .then()
                .statusCode(404).log().all();
    }

    @WithTags({@WithTag("feature:NEGATIVE"),
            @WithTag("feature:SMOKE")})
    @Title("This test will provide an error code 403 when user doesn't provide authentication")
    @Test
    public void authenticationMissing() {
        SerenityRest.rest().
                given()
                .when().delete("/id")
                .then().log().all().statusCode(403);
    }

    @WithTags({@WithTag("feature:POSITIVE"),
            @WithTag("feature:SMOKE")})
    @Title("This test will verify if a status code of 200 is returned for GET request")
    @Test
    public void verifyingStatusCodeIsCorrect() {
        SerenityRest.given()
                .when()
                .get()
                .then().log().all().statusCode(200);
    }
}
