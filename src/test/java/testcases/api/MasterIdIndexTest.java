package testcases.api;

import base.ApiTestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.Steps;

public class MasterIdIndexTest extends ApiTestBase {

   @Test(priority = 1, enabled = true)
   public void get_all_mapped_id() throws Exception {
      // Test Data
      String cookie = ApiTestBase.cookie;
      String originalId = "30650";

      // Test Step
      Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-master-id-index-service/api/IdMapping/getAll");
      RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-master-id-index-service/";
      RequestSpecification httpRequest = RestAssured.given();
      httpRequest.header("Cookie", cookie);
      httpRequest.header("Accept", "application/json");
      Response response = httpRequest.get("api/IdMapping/getAll");
      ApiTestBase.checkStatus(response);
      ResponseBody body = response.getBody();
      String json = body.asString();
      Steps.log("Receiving the response");
      String beauty = beautify(json);
      Steps.logJson(beauty);

      // Test Assertions
      Steps.log("Asserting to check if response body includes target data");
      SoftAssert sAssert = new SoftAssert();
      sAssert.assertTrue(beauty.contains(originalId), "Element with Id:" + originalId + " not found");
      sAssert.assertAll();
   }

   @Test(priority = 2, enabled = true)
   public void get_specific_element_mapped_id() throws Exception {
      // Test Data
      String cookie = ApiTestBase.cookie;
      String expectedOriginalId = "37104";
      String expectedMappedId = "PQrs2eutvEeOjbwsvjFS4g";

      // Test Step
      Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-master-id-index-service/api/IdMapping/get/originalId");
      RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-master-id-index-service/";
      RequestSpecification httpRequest = RestAssured.given();
      httpRequest.header("Cookie", cookie);
      httpRequest.header("Accept", "application/json");
      httpRequest.header("Content-Type", "application/json");
      JSONObject jsonBody = new JSONObject();
      jsonBody.put("id", 4);
      jsonBody.put("element", "PatientId");
      jsonBody.put("originalId", "37104");
      jsonBody.put("mappedId", "PQrs2eutvEeOjbwsvjFS4g");
      jsonBody.put("createdOn", "2020-08-15 00:25:14");
      jsonBody.put("updatedOn", "2020-08-15 00:25:14");
      httpRequest.body(jsonBody);
      Response response = httpRequest.post("/api/IdMapping/get/originalId");
//        ApiTestConfig.checkStatus(response);
      ResponseBody body = response.getBody();
      String json = body.asString();
      Steps.log("Receiving the response");
      String beauty = beautify(json);
      Steps.logJson(beauty);

      // Extracting data from response body
      Steps.log("Extracting target data from response body for assertion");
      JsonPath jp = response.jsonPath();
      String actualOriginalId = jp.get("originalId");
      String actualMappedId = jp.get("mappedId");

      // Test Assertions
      Steps.log("Asserting to check if originalId and mappedID match");
      SoftAssert sAssert = new SoftAssert();
      sAssert.assertEquals(actualOriginalId, expectedOriginalId);
      sAssert.assertEquals(actualMappedId, expectedMappedId);
      sAssert.assertAll();
   }
}