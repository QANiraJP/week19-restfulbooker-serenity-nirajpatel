package com.restful.booker.cucumber;


import com.restful.booker.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resourse/feature/")
public class CucumberRunner extends TestBase {
}
