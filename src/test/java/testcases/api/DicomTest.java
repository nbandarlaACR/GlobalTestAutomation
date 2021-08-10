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


public class DicomTest extends ApiTestBase {

    @Test(priority = 1, enabled = true)
    public void verify_dicom_servers() throws Exception {
        // Test Data
        String cookie = ApiTestBase.cookie;
        String expectedServer1 = "webDicom";
        String expectedServer2 = "dicom";

        // Test Step
        Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-dicom-service/api/dicomServers");
        RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-dicom-service/";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Cookie", cookie);
        httpRequest.header("Accept", "application/json");
        Response response = httpRequest.get("/api/dicomServers");
        ApiTestBase.checkStatus(response);
        Steps.log("Status code is 200");
        ResponseBody body = response.getBody();
        String json = body.asString();
        Steps.log("Receiving the response");
        String beauty = beautify(json);
        Steps.logJson(beauty);

        // Extracting data from response body
        Steps.log("Extracting target data from response body for assertion");
        String actualServer1 = extractData(json, "$.data[0].type");
        String actualServer2 = extractData(json, "$.data[1].type");

        // Test Assertions
        Steps.log("Asserting to check if the target servers are retrieved successfully");
        SoftAssert sAssert = new SoftAssert();
        sAssert.assertEquals(actualServer1, expectedServer1);
        sAssert.assertEquals(actualServer2, expectedServer2);
        sAssert.assertAll();
    }

    @Test(priority = 2, enabled = true)
    public void verify_dicom_server_network_connectivity() throws Exception {
        // Test Data
        String cookie = ApiTestBase.cookie;
        int expectedStatus = 0;

        // Test Step
        Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-dicom-service/api/dicomServers/ba102d92-0431-4c96-9f05-0ca265ed36d4/verification");
        RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-dicom-service/";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Cookie", cookie);
        httpRequest.header("Accept", "application/json");
        Response response = httpRequest.get("/api/dicomServers/ba102d92-0431-4c96-9f05-0ca265ed36d4/verification");
        ApiTestBase.checkStatus(response);
        Steps.log("Status code is 200");
        ResponseBody body = response.getBody();
        String json = body.prettyPrint();
        Steps.log("Receiving the response");
        Steps.logJson(json);

        // Extracting data from response body
        Steps.log("Extracting target data from response body for assertion");
        JsonPath jp = response.jsonPath();
        int actualStatus = jp.get("status");

        // Test Assertion
        Steps.log("Asserting to check if status matches");
        SoftAssert sAssert = new SoftAssert();
        sAssert.assertEquals(actualStatus, expectedStatus);
        sAssert.assertAll();
    }

    @Test(priority = 3, enabled = true)
    public void verify_dicom_listener() throws Exception {
        // Test Data
        String cookie = ApiTestBase.cookie;
        String expectedListener = "Default ACR Connect Listener";

        // Test Step
        Steps.log("Making a request to Endpoint: " + "https://connect-dev.acr.org/gateway/acrconnect-dicom-service/api/listeners");
        RestAssured.baseURI = "https://connect-dev.acr.org/gateway/acrconnect-dicom-service/";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Cookie", cookie);
        httpRequest.header("Accept", "application/json");
        Response response = httpRequest.get("/api/listeners");
        ApiTestBase.checkStatus(response);
        Steps.log("Status code is 200");
        ResponseBody body = response.getBody();
        String json = body.asString();
        Steps.log("Receiving the response");
        String beauty = beautify(json);
        Steps.logJson(beauty);

        // Test Assertions
        Steps.log("Asserting to check if the target Listener is retrieved successfully");
        SoftAssert sAssert = new SoftAssert();
        sAssert.assertTrue(beauty.contains(expectedListener), expectedListener + " couldn't be retrieved");
        sAssert.assertAll();
    }
}
