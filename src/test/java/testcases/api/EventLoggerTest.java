package testcases.api;

import base.ApiTestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.Steps;

public class EventLoggerTest extends ApiTestBase {

   @Test(priority = 1, enabled = true)
   public void verify_event_messages_count() throws Exception {
      // Test Data
      String cookie = ApiTestBase.cookie;

      // Test Step
      Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-event-logger-service/api/internal/sources/messages/count");
      RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-event-logger-service/";
      RequestSpecification httpRequest = RestAssured.given();
      httpRequest.header("Cookie", cookie);
      httpRequest.header("Accept", "application/json");
      Response response = httpRequest.get("/api/internal/sources/messages/count");
      ApiTestBase.checkStatus(response);
      Steps.log("Status code is 200");
      ResponseBody body = response.getBody();
      String json = body.asString();
      Steps.log("Receiving the response");
      String beauty = beautify(json);
      Steps.logJson(beauty);

      // Test Assertions
      Steps.log("Asserting to check if the count of messages retrieved correctly");
      SoftAssert sAssert = new SoftAssert();
      sAssert.assertNotNull(beauty);
      sAssert.assertAll();
   }

   @Test(priority = 2, enabled = true)
   public void verify_event_logger_services() throws Exception {
      // Test Data
      String cookie = ApiTestBase.cookie;
      String expectedService = "acrconnect-homepage-service";
      String expectedService2 = "acrconnect-data-manager-service";

      // Test Step
      Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-event-logger-service/api/Export/services");
      RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-event-logger-service/";
      RequestSpecification httpRequest = RestAssured.given();
      httpRequest.header("Cookie", cookie);
      httpRequest.header("Accept", "application/json");
      Response response = httpRequest.get("/api/Export/services");
      ApiTestBase.checkStatus(response);
      Steps.log("Status code is 200");
      ResponseBody body = response.getBody();
      String json = body.asString();
      Steps.log("Receiving the response");
      String beauty = beautify(json);
      Steps.logJson(beauty);

      // Test Assertions
      Steps.log("Asserting to check if the target services are retrieved successfully");
      SoftAssert sAssert = new SoftAssert();
      sAssert.assertTrue(beauty.contains(expectedService), expectedService + " couldn't be retrieved");
      sAssert.assertTrue(beauty.contains(expectedService2), expectedService2 + " couldn't be retrieved");
      sAssert.assertAll();
   }

}