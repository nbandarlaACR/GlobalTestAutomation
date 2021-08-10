package testcases.smoke;

import com.github.javafaker.Faker;
import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.acrconnect.DataManagerPage;
import pages.acrconnect.DicomServicePage;
import pages.acrconnect.HomePage;

public class ACRConnectSmokeTestBase extends TestBase {

   @Test(priority = 1, enabled = true)
   public void login_test() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";
      final String expectedHomepageTitle = "Home Page";

      // Test Steps
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. Retrieve the Home Page title and do Assertion
      Assert.assertEquals(homePage.getConnectHomepageTitle(), expectedHomepageTitle);
   }

   @Test(priority = 2, enabled = true)
   public void user_can_create_PXS_dataset() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";
      String name = new Faker().name().firstName();
      String testDatasetName = "COVID19_Site_" + name;

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to the DICOM service
      homePage.openDICOMService();
      DicomServicePage dicom = new DicomServicePage();
      dicom.clickImportData();
      dicom.createCovidDatasetTestEnv(testDatasetName);
      dicom.isDatasetIsCreated();
      dicom.goToHomePage();
      homePage.openDataManager();

      //3. User goes to Data Manger to verify Dataset created
      DataManagerPage dataManagerPage = new DataManagerPage();
      dataManagerPage.searchForDataset(testDatasetName);
   }

   @Test(priority = 3, enabled = true)
   public void user_can_create_breast_density_dataset() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";
      String name = new Faker().name().firstName();
      String testDatasetName = "Breast_Density_" + name;

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to the DICOM service
      homePage.openDICOMService();
      DicomServicePage dicom = new DicomServicePage();
      dicom.clickImportData();
      dicom.createBreastDensityDatasetTestEnv(testDatasetName);
      dicom.isDatasetIsCreated();
      dicom.goToHomePage();
      homePage.openDataManager();

      //3. User goes to Data Manger to verify Dataset created
      DataManagerPage dataManagerPage = new DataManagerPage();
      dataManagerPage.searchForDataset(testDatasetName);
   }

   @Test(priority = 4, enabled = true)
   public void verify_selectAll_functionality() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";
      final String expectedDataCount = "43";

      // Test Steps
      // 1.User navigates to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      // 2.User goes to Data Manager service > Datasets to verify Select All functionality
      homePage.openDataManager();
      DataManagerPage dataManagerPage = new DataManagerPage();
      dataManagerPage.searchForDataset("BD_DS1");
      dataManagerPage.clickOnSelectAll();

      Assert.assertEquals(expectedDataCount, dataManagerPage.actualDataCount);
   }

   @Test(priority = 5, enabled = true)
   public void verify_dataset_deletion() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";
      String name = new Faker().name().firstName();
      String testDatasetName = "COVID19_Site_" + name;

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to the DICOM service
      homePage.openDICOMService();
      DicomServicePage dicom = new DicomServicePage();
      dicom.clickImportData();
      dicom.createCovidDatasetTestEnv(testDatasetName);
      dicom.isDatasetIsCreated();
      dicom.goToHomePage();

      //3. User goes to Data Manger to verify Dataset created
      homePage.openDataManager();
      DataManagerPage dataManagerPage = new DataManagerPage();
      dataManagerPage.searchForDataset(testDatasetName);

      //4. User deletes new created dataset
      dataManagerPage.deleteDataset(testDatasetName);
   }

   @Test(priority = 6, enabled = true)
   public void user_can_copy_dataset_without_annotations() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";
      String name = new Faker().name().firstName();
      String testDatasetName = "BD_DS1";
      String copyDatasetName = "Copy_" + name;
      final String expectedDataCount = "43";

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to Data Manger to search the target dataset
      homePage.openDataManager();
      DataManagerPage dataManagerPage = new DataManagerPage();
      dataManagerPage.searchForDataset(testDatasetName);

      //3. User copies the target dataset
      dataManagerPage.copyDatasetWithoutAnnotations(copyDatasetName);

      //4. User navigates to copy dataset and verifies the count of data
      dataManagerPage.searchForDataset(copyDatasetName);
      dataManagerPage.clickOnSelectAll();

      Assert.assertEquals(dataManagerPage.actualDataCount, expectedDataCount);
   }

   @Test(priority = 7, enabled = true)
   public void user_can_copy_dataset_with_annotations() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";
      String name = new Faker().name().firstName();
      String testDatasetName = "BD_DS1";
      String copyDatasetName = "Copy_" + name;
      final String expectedDataCount = "43";

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to Data Manger to search the target dataset
      homePage.openDataManager();
      DataManagerPage dataManagerPage = new DataManagerPage();
      dataManagerPage.searchForDataset(testDatasetName);

      //3. User copies the target dataset
      dataManagerPage.copyDatasetWithAnnotations(copyDatasetName);

      //4. User navigates to copy dataset and verifies the count of data
      dataManagerPage.searchForDataset(copyDatasetName);
      dataManagerPage.clickOnSelectAll();

      Assert.assertEquals(dataManagerPage.actualDataCount, expectedDataCount);
   }

   @Test(priority = 8, enabled = true)
   public void user_can_create_file_server() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";
      String name = new Faker().name().firstName();
      String serverName = "FileServer_" + name;

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to the DICOM service
      homePage.openDICOMService();

      //3. User creates a new File Server
      DicomServicePage dicom = new DicomServicePage();
      dicom.createNewFileServer(serverName);
   }

   @Test(priority = 9, enabled = true)
   public void user_can_create_webDicom_server() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";
      String name = new Faker().name().firstName();
      String serverName = "WebDICOM_" + name;

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to the DICOM service
      homePage.openDICOMService();

      //3. User creates a new WebDICOM Server
      DicomServicePage dicom = new DicomServicePage();
      dicom.createNewWebDICOMServer(serverName);
   }

   @Test(priority = 10, enabled = true)
   public void user_can_delete_server() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to the DICOM service
      homePage.openDICOMService();

      //3. User deletes the target server
      DicomServicePage dicom = new DicomServicePage();
      dicom.deleteServer();
   }

   @Test(priority = 11, enabled = true)
   public void create_dataset_via_data_section() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";
      String name = new Faker().name().firstName();
      String newDatasetName = "NewDS_" + name;

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to the Data Manager > Data service
      homePage.openDataManager();
      DataManagerPage dataManagerPage = new DataManagerPage();

      //3. User creates a new dataset and search for the new created dataset in datasets section
      dataManagerPage.createDataset(newDatasetName);
      dataManagerPage.searchForDataset(newDatasetName);
   }

   @Test(priority = 12, enabled = true)
   public void add_data_to_existing_dataset() {
      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to the Data Manager > Data service
      homePage.openDataManager();
      DataManagerPage dataManagerPage = new DataManagerPage();

      //3. User adds data to an existing dataset
      dataManagerPage.addDataToExistingDataset();
   }

   @Test(priority = 13, enabled = true)
   public void verify_search_and_retrieve() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to the DICOM service
      homePage.openDICOMService();
      DicomServicePage dicomServicePage = new DicomServicePage();

      //3. User retrieves data by Search and Retrieve functionality
      dicomServicePage.searchAndRetrieve();
   }

   @Test(priority = 14, enabled = true)
   public void anonymization_with_default_profile() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";
      String name = new Faker().name().firstName();
      String testDatasetName = "BD_DS1";
      final String anonymized_datasetName = "Anon_" + name;
      final String expectedDataCount = "43";

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to Data Manger to search the target dataset
      homePage.openDataManager();
      DataManagerPage dataManagerPage = new DataManagerPage();
      dataManagerPage.searchForDataset(testDatasetName);

      //3. User anonymizes the target dataset with Default Anonymization Profile
      dataManagerPage.anonymizationWithDefaultProfile(anonymized_datasetName);

      //4. User navigates to anonymized dataset and verifies the count of anonymized data
      dataManagerPage.searchForDataset(anonymized_datasetName);
      dataManagerPage.clickOnSelectAll();

      Assert.assertEquals(dataManagerPage.actualDataCount, expectedDataCount);
   }

   @Test(priority = 15, enabled = true)
   public void anonymization_with_lookup_profile() {

      // Test Data
      final String username = "acrconnect.testuser1@yahoo.com";
      final String pass = "TestAccount1";
      String name = new Faker().name().firstName();
      String testDatasetName = "BD_DS1";
      final String anonymized_datasetName = "Lookup_" + name;
      final String expectedDataCount = "43";

      // TEST STEPS
      //1. User goes to ACR Connect home page
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.singInTest(username, pass);

      //2. User goes to Data Manger to search the target dataset
      homePage.openDataManager();
      DataManagerPage dataManagerPage = new DataManagerPage();
      dataManagerPage.searchForDataset(testDatasetName);

      //3. User anonymizes the target dataset with Lookup Anonymization Profile
      dataManagerPage.anonymizationWithLookupProfile(anonymized_datasetName);

      //4. User navigates to anonymized dataset and verifies the count of anonymized data
      dataManagerPage.searchForDataset(anonymized_datasetName);
      dataManagerPage.clickOnSelectAll();

      Assert.assertEquals(dataManagerPage.actualDataCount, expectedDataCount);
   }

}