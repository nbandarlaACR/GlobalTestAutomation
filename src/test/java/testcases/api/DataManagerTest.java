package testcases.api;

import base.ApiTestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.Steps;

public class DataManagerTest extends ApiTestBase {

   @Test(priority = 1, enabled = true)
   public void verify_dataset_data() throws Exception {
      // Test Data
      String cookie = ApiTestBase.cookie;
      String expectedStudyUID = "1.2.840.113619.2.66.2199087723.11364030520100155.20000";

      // Test Step
      Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/api/datasets/46/tags");
      RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/";
      RequestSpecification httpRequest = RestAssured.given();
      httpRequest.header("Cookie", cookie);
      httpRequest.header("Accept", "application/json");
      Response response = httpRequest.get("api/datasets/46/tags");
      ApiTestBase.checkStatus(response);
      Steps.log("Status code is 200");
      ResponseBody body = response.getBody();
      String json = body.asString();
      Steps.log("Receiving the response");
      String beauty = beautify(json);
      Steps.logJson(beauty);

      // Extracting data from response body
      Steps.log("Extracting target data from response body for assertion");
      String actualStudyUID = extractData(json, "$.[0].dataName");

      // Test Assertions
      Steps.log("Asserting to check if response body includes target data");
      SoftAssert sAssert = new SoftAssert();
      sAssert.assertEquals(actualStudyUID, expectedStudyUID);
      sAssert.assertAll();
   }

   @Test(priority = 2, enabled = true)
   public void dataset_validation() throws Exception {
      // Test Data
      String cookie = ApiTestBase.cookie;
      String expectedDatasetName = "Covid19_DS";
//        String expectedDataCount = "353";

      // Test Step
      Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/api/DataSets");
      RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/";
      RequestSpecification httpRequest = RestAssured.given();
      httpRequest.header("Cookie", cookie);
      httpRequest.header("Accept", "application/json");
      Response response = httpRequest.get("/api/DataSets");
      ApiTestBase.checkStatus(response);
      Steps.log("Status code is 200");
      ResponseBody body = response.getBody();
      String json = body.asString();
      Steps.log("Receiving the response");
      String beauty = beautify(json);
      Steps.logJson(beauty);

      // Extracting data from response body
      Steps.log("Extracting target data from response body for assertion");
//        String actualDataCount = extractData(json, "$.data[0].dataCount");
      String actualDatasetName = extractData(json, "$.data[0].name");

      // Test Assertion
      Steps.log("Asserting to check if target dataset_name matches the returned dataset_name");
      SoftAssert sAssert = new SoftAssert();
//        sAssert.assertEquals(actualDataCount, expectedDataCount);
      sAssert.assertEquals(actualDatasetName, expectedDatasetName);
      sAssert.assertAll();
   }

   @Test(priority = 3, enabled = true)
   public void validate_dataset_items() throws Exception {
      // Test Data
      String cookie = ApiTestBase.cookie;
      String expectedDataId1 = "d9124b6d-543a-458d-92dd-9d3c426e7620";
      String expectedDataId2 = "2a732963-3986-482e-a561-3d38a4f0122d";

      // Test Step
      Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/api/DataSets/46/items");
      RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/";
      RequestSpecification httpRequest = RestAssured.given();
      httpRequest.header("Cookie", cookie);
      httpRequest.header("Accept", "application/json");
      Response response = httpRequest.get("/api/DataSets/46/items");
      ApiTestBase.checkStatus(response);
      Steps.log("Status code is 200");
      ResponseBody body = response.getBody();
      String json = body.asString();
      Steps.log("Receiving the response");
      String beauty = beautify(json);
//        Steps.logJson(beauty);

      // Extracting data from response body
      Steps.log("Extracting target data from response body for assertion");
      String actualDataId1 = extractData(json, "$.data[0].dataId");
      String actualDataId2 = extractData(json, "$.data[1].dataId");

      // Test Assertions
      Steps.log("Asserting to check if target dataId matches");
      SoftAssert sAssert = new SoftAssert();
      sAssert.assertEquals(expectedDataId1, actualDataId1);
      sAssert.assertEquals(expectedDataId2, actualDataId2);
      sAssert.assertAll();
   }

   @Test(priority = 4, enabled = true)
   public void validate_dataset_deletion() throws Exception {
      // Test Data
      String cookie = ApiTestBase.cookie;
      String datasetName = "Server_Test2";

      // Test Step
      Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/api/DataSets/44");
      RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/";
      RequestSpecification httpRequest = RestAssured.given();
      httpRequest.header("Cookie", cookie);
      httpRequest.header("Accept", "application/json");
      Response response = httpRequest.delete("/api/DataSets/44");
      ApiTestBase.checkStatus(response);
      Steps.log("Status code is 200");
      ResponseBody body = response.getBody();
      String json = body.asString();
      Steps.log("Receiving the response. Response body should be null");
      String beauty = beautify(json);
      Steps.logJson(beauty);

      // Test Assertions
      Steps.log("Asserting to check if target dataset is deleted");
      SoftAssert sAssert = new SoftAssert();
      sAssert.assertTrue(json.isEmpty(), "Dataset:" + datasetName + " didn't get deleted");
      sAssert.assertAll();
   }

   @Test(priority = 5, enabled = true)
   public void verify_anonymization_profiles() throws Exception {
      // Test Data
      String cookie = ApiTestBase.cookie;
      String expectedProfile1 = "LookupProfile";
      String expectedProfile2 = "DefaultProfile";

      // Test Step
      Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/api/Profiles/anonymization");
      RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/";
      RequestSpecification httpRequest = RestAssured.given();
      httpRequest.header("Cookie", cookie);
      httpRequest.header("Accept", "application/json");
      Response response = httpRequest.get("/api/Profiles/anonymization");
      ApiTestBase.checkStatus(response);
      Steps.log("Status code is 200");
      ResponseBody body = response.getBody();
      String json = body.asString();
      Steps.log("Receiving the response");
      String beauty = beautify(json);
      Steps.logJson(beauty);

      // Extracting data from response body
      Steps.log("Extracting target data from response body for assertion");
      String actualProfile1 = extractData(json, "$.[0].name");
      String actualProfile2 = extractData(json, "$.[1].name");

      // Test Assertions
      Steps.log("Asserting to check if response body includes target anonymization profiles");
      SoftAssert sAssert = new SoftAssert();
      sAssert.assertEquals(expectedProfile1, actualProfile1);
      sAssert.assertEquals(expectedProfile2, actualProfile2);
      sAssert.assertAll();
   }

   @Test(priority = 6, enabled = true)
   public void verify_dataset_annotations_count() throws Exception {
      // Test Data
      String cookie = ApiTestBase.cookie;
      int expectedAnnotationsCount = 353;

      // Test Step
      Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/api/DataSets/9/annotations/counts");
      RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/";
      RequestSpecification httpRequest = RestAssured.given();
      httpRequest.header("Cookie", cookie);
      httpRequest.header("Accept", "application/json");
      Response response = httpRequest.get("/api/DataSets/9/annotations/counts");
      ApiTestBase.checkStatus(response);
      Steps.log("Status code is 200");
      ResponseBody body = response.getBody();
      String json = body.asString();
      Steps.log("Receiving the response");
      String beauty = beautify(json);
      Steps.logJson(beauty);

      // Extracting data from response body
      Steps.log("Extracting target data from response body for assertion");
      JsonPath jp = response.jsonPath();
      int actualAnnotationsCount = jp.getInt("annotationsCount");

      // Test Assertion
      Steps.log("Asserting to check if annotations count matches");
      SoftAssert sAssert = new SoftAssert();
      sAssert.assertEquals(expectedAnnotationsCount, actualAnnotationsCount);
      sAssert.assertAll();
   }

   @Test(priority = 7, enabled = true)
   public void verify_data_annotations() throws Exception {
      // Test Data
      String cookie = ApiTestBase.cookie;
      String expectedContent = "{\"right-lung-extent\":\"2\",\"right-lung-density\":null,\"left-lung-extent\":null,\"left-lung-density\":null}";

      // Test Step
      Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/api/Annotations/1633");
      RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-data-manager-service/";
      RequestSpecification httpRequest = RestAssured.given();
      httpRequest.header("Cookie", cookie);
      httpRequest.header("Accept", "application/json");
      Response response = httpRequest.get("/api/Annotations/1633");
      ApiTestBase.checkStatus(response);
      Steps.log("Status code is 200");
      ResponseBody body = response.getBody();
      String json = body.asString();
      Steps.log("Receiving the response");
      String beauty = beautify(json);
      Steps.logJson(beauty);

      // Extracting data from response body
      Steps.log("Extracting target data from response body for assertion");
      JsonPath jp = response.jsonPath();
      String actualContent = jp.get("content");

      // Test Assertion
      Steps.log("Asserting to check if Contents match");
      SoftAssert sAssert = new SoftAssert();
      sAssert.assertEquals(expectedContent, actualContent);
      sAssert.assertAll();
   }
}