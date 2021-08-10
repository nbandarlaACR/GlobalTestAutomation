package testcases.e2e;

import base.TestBase;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.acrconnect.DataManagerPage;
import pages.acrconnect.DicomServicePage;
import pages.acrconnect.HomePage;
import pages.ailab.*;
import utility.Steps;

@Listeners(utility.TestListener.class)
public class E2E extends TestBase {
   // As a user, WHEN I create PXS data-set
   // THEN I can only use it on PREPARE page
   // if it is un-prepared
   // --- DONE X
   @Test
   public void moon_t309() {
      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      String name = new Faker().name().firstName();
      String datasetName = "Test_PXS_" + name;

      // ----- TEST STEPS ----- //
      HomePage homePage = new HomePage();
      DataManagerPage dataManager = new DataManagerPage();

      // Test Script
      Steps.log("Opening ACR Connect on connect-staging.org");
      homePage.openTestEnv();

      Steps.log("Signing in to ACR Connect as Platform user with AILAB app access.");
      homePage.signIn(username, pass);
      homePage.openDICOMService();

      Steps.log("On DICOM Service, preparing PXS related dataset");
      DicomServicePage dicom = new DicomServicePage();
      dicom.clickImportData();
      dicom.createPXSDataset(datasetName);
      dicom.isDatasetIsCreated();
      dicom.switchToHomePage();
      homePage.openDataManager();

      Steps.log("On Ailab, preparing dataset that was created");
      
      DataManagerPage dataManagerPage = new DataManagerPage();
      dataManagerPage.searchForDataset(datasetName);
      homePage.openAilab();
      PreparePage prepare = new PreparePage();
      prepare.open();

      prepare.preparePXSDataset(datasetName);
      boolean actual = prepare.isPXSDatasetPrepDone();
      Steps.log("Verifying that dataset is prepared");
      Steps.imgLog("Prepared Dataset");

      //homePage.openDataManager();
      homePage.gotoDataManagerPage();
      dataManager.searchForDataset(datasetName);
      dataManager.deleteDataset(datasetName);

      Assert.assertTrue(actual);
   }


   // AS a user, WHEN I create [Breast Density] data-set,
   // THEN I can only use it on Prepare page in its un-prepared
   // --- DONE X
   @Test
   public void moon_t308() {

      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      String datasetName = "Test_BD_" + randomeName();

      // ----- TEST STEPS ----- //
      HomePage homePage = new HomePage();
      DataManagerPage dataManager = new DataManagerPage();

      // Test Script
      homePage.openTestEnv();
      homePage.signIn(username, pass);

      homePage.openDICOMService();
      DicomServicePage dicom = new DicomServicePage();
      dicom.clickImportData();
      dicom.createBreastDensityDataset(datasetName);

      homePage.openAilab();
      PreparePage prepare = new PreparePage();
      prepare.open();

      prepare.prepareBreastDensityDataset(datasetName);
      boolean actual = prepare.isDatasetPrepDone();

      homePage.openDataManager();
      dataManager.searchForDataset(datasetName);
      dataManager.deleteDataset(datasetName);

      Assert.assertTrue(actual);
   }


   // AS a user, WHEN I create and prepare [Breast Density] data-set,
   // THEN I can use it on various AILAB pages
   //  ---  Annotate page
   //  ---  Create page
   //  ---  Evaluate page
   //  ---  Run page
   //  ---  DONE !
   @Test
   public void moon_t306() {
      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      String datasetName = "Test_BD_" + randomeName();

      // Go to AILAB
      homePage().openTestEnv();
      homePage().signIn(username, pass);
   
      // Create breast density dataset
      homePage().openDICOMService();
      dicomService().clickImportData();

      // Creates Breast Density dataset based on specified excel sheet
      dicomService().createBreastDensityDataset(datasetName, "bd_20");
      dicomService().goToHomePage();


      // Go to AILAB Prepare page and prepare the dataset
      homePage().gotoAilab();
      prepPage().open();
      prepPage().prepareBreastDensityDataset(datasetName);
      prepPage().isDatasetPrepDone();

      // Go to AILAB Annotate page, and annotate the dataset
      homePage().gotoAnnotatePage();
      annotatePage().goToAnnotatedSection();
      annotatePage().selectBreastDensity(datasetName);
      pauseBrowser(2);

      // Go to Create page and configure the model
      homePage().gotoCreatePage();
      createPage().selectModel();
      createPage().configureModel();
      createPage().configureTraining(datasetName);
      IMG_LOG("Create a model based on the dataset");

      // Go to Evaluate page and evaluate the model
      // using the prepared dataset
      homePage().gotoEvaluatePage();
      evaluatePage().evaluateBDModel(datasetName);
      boolean ret1 = evaluatePage().verifyModelEvaluation();

      // Go to Run page, and run inference
      // using the prepared dataset
      homePage().gotoRunPage();
      boolean ret2 = runPage().verifyBreastDensityInference(datasetName);

      // Go to Data Manager, and delete the prepared dataset
      homePage().openDataManager();
      dataManager().searchForDataset(datasetName);
      dataManager().deleteDataset(datasetName);

      Assert.assertTrue(ret1 && ret2);
   }


   // AS a user, WHEN I create and prepare [PXS] dataset,
   // THEN I can use it on various AILAB pages
   //  ---  Annotate page
   //  ---  Evaluate page
   //  ---  Run page
   //  --- DONE!
   @Test
   public void moon_t307() {
      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      String datasetName = "Test_PXS_" + randomeName();

      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.signIn(username, pass);
      homePage.openDICOMService();
      DicomServicePage dicom = new DicomServicePage();
      dicom.clickImportData();
      dicom.createPXSDataset(datasetName);
      dicom.isDatasetIsCreated();
      IMG_LOG("Create PXS related dataset");
      dicom.goToHomePage();

      homePage.gotoAilab();
      PreparePage prepare = new PreparePage();
      prepare.open();
      prepare.preparePXSDataset(datasetName);
      prepare.isPXSDatasetPrepDone();
      IMG_LOG("PXS related dataset was prepared");

      homePage.gotoAnnotatePage();
      AnnotatePage annotate = new AnnotatePage();
      annotate.goToAnnotatedSection();
      annotate.selectPXS(datasetName);
      annotate.clickNext();
      IMG_LOG("Annotations for PXS related datset is complete");
      pauseBrowser(10);


      homePage.gotoEvaluatePage();
      EvaluatePage evaluate = new EvaluatePage();
      evaluate.evaluatePXS(datasetName);
      boolean ret1 = evaluate.verifyPXSModelEvaluation();
      IMG_LOG("Evaluating model using PXS related dataset");

      homePage.gotoRunPage();
      RunPage run = new RunPage();
      boolean ret2 = run.verifyPXSInference(datasetName);
      IMG_LOG("Verifying Run Inference using the pxs datset");

      homePage.openDataManager();
      deleteDataset(datasetName);

      Assert.assertTrue(ret1 && ret2);
   }


   // AS a user, WHEN I modify the [Breast Density] data-set on Data Manager,
   // THEN I can see the modifications on AILAB pages
   // --- DONE
   @Test
   public void moon_t304() {
      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      final String dataset = "BD_Edit_" + randomeName();

      // go to DICOM service
      HomePage homePage = new HomePage();
      homePage.openTestEnv();
      homePage.signIn(username, pass);
      homePage.openDICOMService();
      IMG();

      // Prepare a test breast density dataset
      DicomServicePage dicom = new DicomServicePage();
      dicom.clickImportData();
      dicom.createBreastDensityDataset(dataset, "bd_20");
      dicom.goToHomePage();
      IMG();

      //  Verify that dataset is created
      homePage.openDataManager();
      DataManagerPage dataManager = new DataManagerPage();
      dataManager.searchForDataset(dataset);
      homePage.openAilab();
      IMG();

      //  Prepare the dataset and verify the dataset name
      PreparePage prepare = new PreparePage();
      prepare.open();
      prepare.prepareBreastDensityDataset(dataset);
      boolean ret1 = prepare.verifyDatasetName(dataset);
      IMG();

      // At DataManager change the dataset name
      String newDatasetName = dataset + randomeName();
      homePage.gotoDataManagerPage();
      dataManager.changeDatasetName(dataset, newDatasetName);
      homePage.swithToTheTab("Prepare - AI-LAB");
      IMG();

      prepare.open();
      boolean ret2 = prepare.verifyDatasetName(newDatasetName);
      homePage.gotoDataManagerPage();
      dataManager.searchForDataset(newDatasetName);
      dataManager.deleteDataset(newDatasetName);

      Assert.assertTrue(ret1 && ret2);

   }


   // AS a user, WHEN I modify the [PXS] dataset on Data Manager,
   // THEN I can see the modifications on AILAB pages
   // ---- DONE
   @Test
   public void moon_t305() {
      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      final String dataset = "PXS_Edit_" + randomeName();

      // Page objects preparation
      HomePage homePage = new HomePage();
      DicomServicePage dicomService = new DicomServicePage();
      DataManagerPage dataManager = new DataManagerPage();
      PreparePage prepare = new PreparePage();


      // ---- Test Scripts ----- //
      homePage.openTestEnv();
      homePage.signIn(username, pass);
      homePage.openDICOMService();
      IMG();
      // Prepare a test breast density dataset
      dicomService.clickImportData();
      dicomService.createPXSDataset(dataset);
      IMG();

      //  Verify that dataset is created
      homePage.openDataManager();
      dataManager.searchForDataset(dataset);
      homePage.openAilab();
      IMG();

      //  Prepare the dataset and verify the dataset name

      prepare.open();
      prepare.preparePXSDataset(dataset);
      boolean ret1 = prepare.verifyDatasetName(dataset);
      IMG();

      // At DataManager change the dataset name
      String newDatasetName = dataset + randomeName();
      homePage.gotoOpenPage("Data Manager");
      dataManager.searchForDataset(dataset);
      IMG();

      dataManager.changeDatasetName(dataset, newDatasetName);
      homePage.gotoOpenPage("Prepare - AI-LAB");
      prepare.open();
      prepare.preparePXSDataset(newDatasetName);
      boolean ret2 = prepare.verifyDatasetName(newDatasetName);
      IMG();

      homePage.gotoDataManagerPage();
      dataManager.deleteDataset(newDatasetName);

      Assert.assertTrue(ret1 && ret2);
   }


   // AS ailab user, WHEN I run inference on [Breast Density] model and dataset,
   // THEN I can see the prediction result.
   // ---- DONE
   @Test
   public void moon_t339() {
      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      final String dataset = "Test_BD_" + randomeName();

      // Page objects preparation
      HomePage homePage = new HomePage();
      RunPage runPage = new RunPage();

      homePage.openTestEnv();
      homePage.singInTest(username, pass);
      homePage.openDICOMService();
      DicomServicePage dicom = new DicomServicePage();
      dicom.clickImportData();
      IMG();

      // Creates Breast Density dataset based on specified excel sheet
      dicom.createBreastDensityDataset(dataset, "bd_20");
      dicom.goToHomePage();
      homePage.gotoAilab();
      PreparePage prepare = new PreparePage();
      prepare.open();
      prepare.prepareBreastDensityDataset(dataset);
      prepare.isDatasetPrepDone();
      IMG();

      homePage.gotoRunPage();
      boolean result = runPage.verifyBreastDensityInference(dataset);
      IMG();

      homePage.openDataManager();
      DataManagerPage dataManager = new DataManagerPage();
      dataManager.searchForDataset(dataset);
      dataManager.deleteDataset(dataset);

      Assert.assertTrue(result);
   }


   // AS ailab user, WHEN I run inference on downloaded [Breast Density] model and dataset,
   // THEN I can see the prediction result.
   // -- DONE
   @Test
   public void moon_t341() {
      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      final String downloaded_model = "Test Share 2";
      final String dataset = "BD_200_" + randomeName();

      // Page objects preparation
      HomePage homePage = new HomePage();
      PreparePage prepare = new PreparePage();
      DicomServicePage dicomService = new DicomServicePage();
      DataManagerPage dataManager = new DataManagerPage();
      RunPage runPage = new RunPage();

      homePage.openTestEnv();
      homePage.signIn(username, pass);
      IMG();
      homePage.openDICOMService();
      dicomService.clickImportData();
      dicomService.createBreastDensityDataset(dataset, "bd_20");
      IMG();
      homePage.openAilab();

      //  Prepare the dataset and verify the dataset name
      prepare.open();
      prepare.prepareBreastDensityDataset(dataset);
      prepare.waitUntilPrepareIsDone();
      prepare.isDatasetPrepDone();
      IMG();


      homePage.gotoRunPage();
      boolean result = runPage.verifyBreastDensityInference(downloaded_model, dataset);
      IMG();

      // delete the dataset as a clean up ops
      homePage.openDataManager();
      dataManager.refreshPage();
      dataManager.searchForDataset(dataset);
      dataManager.deleteDataset(dataset);

      Assert.assertTrue(result);
   }


   // AS ailab user, WHEN I run inference on [PXS] model and dataset,
   // THEN I can see the prediction result
   // --- DONE
   @Test(priority = 8)
   public void moon_t340() {
      final String dataset = "PXS_Edit_" + randomeName();

      loginToACRConnect();
      gotoDICOMService();
      IMG();
      createPXSDataset(dataset);
      openAilabApp();
      preparePXSDataset(dataset);
      IMG();
      gotoRunInferencePage();
      boolean result = verifyPXSInference(dataset);
      IMG();
      deleteDataset(dataset);

      Assert.assertTrue(result);
   }


   // AS ailab user, WHEN I am evaluating a model, THEN I can cancel the evaluations.
   //--DONE
   @Test
   public void moon_t336() {
      final String dataset = "PXS_Edit_" + randomeName();

      loginToACRConnect();
      gotoDICOMService();
      createPXSDataset(dataset);
      openAilabApp();
      IMG();
      preparePXSDataset(dataset);
      homePage().gotoEvaluatePage();
      evaluatePage().evaluatePXSWithCovid19V2(dataset);
      IMG();
      evaluatePage().cancelEvaluation();
      IMG();
      deleteDataset(dataset);
   }


   // AS ailab user, WHEN I evaluate [Breast Density] model with breast density dataset,
   // THEN I can see the evaluation result
   // --- DONE
   @Test
   public void moon_t332() {
      final String dataset = "BD_"+ randomeName();
      loginToACRConnect();
      gotoDICOMService();
      createBreastDensityDataset(dataset, "bd_20");
      IMG();
      openAilabApp();
      prepareBreastDensityDataset(dataset);
      homePage().gotoEvaluatePage();
      IMG();
      evaluatePage().evaluateBDModel(dataset);
      boolean result = evaluatePage().verifyModelEvaluation();
      IMG();
      Assert.assertTrue(result);
      deleteDataset(dataset);
   }


   // AS ailab user, WHEN I evaluate [PXS] model with dataset,
   // THEN I can see the evaluation result
   // - DONE
   @Test
   public void moon_t333() {
 
      final String dataset = "PXS_" + randomeName();
      loginToACRConnect();
      gotoDICOMService();
      createPXSDataset(dataset);
      openAilabApp();
      IMG();
      preparePXSDataset(dataset);
      homePage().gotoEvaluatePage();
      evaluatePage().evaluatePXSWithCovid19V2(dataset);
      IMG();
      boolean result = evaluatePage().verifyModelEvaluation();
      IMG();
      Assert.assertTrue(result);
      deleteDataset(dataset);
   }


   // AS ailab user, WHEN I have evaluated a model, THEN I can delete the evaluations.
   // -- Done
   @Test
   public void moon_t338() {
      final String dataset = "PXS_" + randomeName();
      //homePage.openTestEnv();
      loginToACRConnect();
      gotoDICOMService();
      createPXSDataset(dataset);
      IMG();
      openAilabApp();
      preparePXSDataset(dataset);
      homePage().gotoEvaluatePage();
      evaluatePage().evaluatePXSWithCovid19V2(dataset);
      IMG();
      evaluatePage().verifyModelEvaluation();
      boolean isDeleted = evaluatePage().deleteEvaluation(dataset);
      IMG();
      Assert.assertTrue(isDeleted);
      deleteDataset(dataset);
   }


   // AS ailab user, WHEN I try to evaluate a model with un-annotated dataset,
   // THEN I cannot do model evaluation
   //-- DONE
   @Test
   public void moon_t334() {

      final String dataset = "covid_empty_" + randomeName();

      loginToACRConnect();
      gotoDICOMService();
      IMG();
      createPXSDataset(dataset, "covid19_empty");
      openAilabApp();
      preparePXSDataset(dataset);
      homePage().gotoEvaluatePage();
      IMG();
      evaluatePage().evaluatePXSWithCovid19V2(dataset);
      boolean result = evaluatePage().verifyDatasetValidationError();
      IMG();
      Assert.assertTrue(result);
      deleteDataset(dataset);
   }


   // AS ailab user, WHEN I prepared the dataset from Data Manager,
   // THEN I can skip few annotations and its visible on [Skipped] section
   // -- Manul intervention, needs revision
   // --DONE
   @Test
   public void moon_t324() {
      final String dataset = "PXS_Gene" + randomeName();
      // go to DICOM service
      HomePage homePage = new HomePage();
      PreparePage prepare = new PreparePage();
      DataManagerPage dataManager = new DataManagerPage();
      AnnotatePage annotate = new AnnotatePage();

      // ---- Test Scripts ----- //
      loginToACRConnect();
      gotoDICOMService();
      createPXSDataset(dataset, "covid19_empty");
      openAilabApp();

      //  Prepare the dataset and verify the dataset name
      prepare.open();
      prepare.preparePXSDataset(dataset);
      prepare.isDatasetPrepDone();
      homePage.gotoAnnotatePage();

      // choose the empty covid19 dataset
      annotate.selectPXS(dataset);
      annotate.clickSkip();
      IMG();
      annotate.clickSkip();
      annotate.clickNotAnnotatedDropdown();
      String result = annotate.getSkippedCount();
      IMG();
      homePage.openDataManager();
      dataManager.searchForDataset(dataset);
      dataManager.deleteDataset(dataset);
      IMG();
      Assert.assertEquals(result, "[2]");
   }


   // AS ailab user, WHEN I prepared and annotated the dataset from Data Manager,
   // THEN I can update the annotations on [Annotated] section.
   // -- DONE 
   @Test
   public void moon_t325() {
      final String dataset = "BD20_" + randomeName();
      // ---- Test Scripts ----- //
      loginToACRConnect();
      gotoDICOMService();
      createBreastDensityDataset(dataset, "bd_20_empty");
      openAilabApp();

      //  Prepare the dataset and verify the dataset name
      prepareBreastDensityDataset(dataset);
      homePage().gotoAnnotatePage();
      IMG();
      // choose the empty covid19 dataset
      annotatePage().selectBreastDensity(dataset);
      annotatePage().clickNotAnnotatedDropdown();
      IMG();
      annotatePage().annotateBreastDensityDataset();
      annotatePage().goToAnnotatedSection();
      IMG();
      boolean result = annotatePage().validateAnnoataion();
      homePage().openDataManager();
      deleteDataset(dataset);
      Assert.assertTrue(result);
   }


   // AS ailab user,  WHEN I annotate the [Breast Density] dataset,
   // THEN I can see annotations made on Data Manager Tags section
   // -- DONE
   @Test
   public void moon_t313() {
      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      final String dataset = "BD20_" + randomeName();

      // Prepare the page object models
      HomePage homePage = new HomePage();
      PreparePage prepare = new PreparePage();
      DicomServicePage dicomService = new DicomServicePage();
      DataManagerPage dataManager = new DataManagerPage();
      AnnotatePage annotate = new AnnotatePage();

      // ---- Test Scripts ----- //
      homePage.openTestEnv();
      homePage.signIn(username, pass);
      homePage.openDICOMService();
      dicomService.clickImportData();
      dicomService.createBreastDensityDataset(dataset, "bd_20_empty");
      homePage.openAilab();
      IMG();

      //  Prepare the dataset and verify the dataset name
      prepare.open();
      prepare.prepareBreastDensityDataset(dataset);
      prepare.waitUntilPrepareIsDone();
      prepare.isDatasetPrepDone();
      homePage.gotoAnnotatePage();
      IMG();

      // choose the empty covid19 dataset
      annotate.selectBreastDensity(dataset);
      annotate.clickNotAnnotatedDropdown();
      IMG();
      annotate.annotateBreastDensityDataset();
      homePage.openDataManager();
      dataManager.searchAndSelectDataset(dataset);
      IMG();
      
      boolean result = dataManager.validateBreastDensityDatasetAnnotation("2");

      homePage.gotoDataManagerPage();
      dataManager.refreshPage();
      dataManager.searchForDataset(dataset);
      dataManager.deleteDataset(dataset);
      Assert.assertTrue(result);
   }

   // AS ailab user,  WHEN I annotate the [PXS] dataset,
   // THEN I can see the annotations on Data Manager Tags section
   // -- DONE 
   @Test
   public void moon_t312() {
      final String dataset = "PXS_" + randomeName();

      // Prepare the page object models
      HomePage homePage = new HomePage();
      PreparePage prepare = new PreparePage();
      DataManagerPage dataManager = new DataManagerPage();
      AnnotatePage annotate = new AnnotatePage();

      // ---- Test Scripts ----- //
      loginToACRConnect();
      gotoDICOMService();
      createPXSDataset(dataset, "covid19_empty");
      openAilabApp();
      IMG();

      //  Prepare the dataset and verify the dataset name
      prepare.open();
      prepare.preparePXSDataset(dataset);
      prepare.isDatasetPrepDone();
      homePage.gotoAnnotatePage();

      // annotate the empty covid19 dataset
      annotate.selectPXS(dataset);
      annotate.clickNotAnnotatedDropdown();
      IMG();
      annotate.annotatePXSDataset();
      IMG();

      // validate that annotations made was observable on Data Manager
      homePage.openDataManager();
      dataManager.searchAndSelectDataset(dataset);
      boolean result = dataManager.validatePXSDatasetAnnotation("1");

      // delete the dataset as a clean up ops
      homePage.gotoDataManagerPage();
      dataManager.refreshPage();
      IMG();
      dataManager.searchForDataset(dataset);
      dataManager.deleteDataset(dataset);

      Assert.assertTrue(result);
   }

   // As ailab user, WHEN I evaluate a model, 
   // THEN I can upload the model evaluations.
   // -- DONE 
   @Test
   public void moon_t337() {
      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      final String dataset = "PXS_" + randomeName();

      // Prepare the page object models
      HomePage homePage = new HomePage();
      PreparePage prepare = new PreparePage();
      DicomServicePage dicomService = new DicomServicePage();
      DataManagerPage dataManager = new DataManagerPage();
      EvaluatePage evaluate = new EvaluatePage();

      // ---- Test Scripts ----- //
      homePage.openTestEnv();
      homePage.signIn(username, pass);
      homePage.openDICOMService();
      dicomService.clickImportData();
      dicomService.createPXSDataset(dataset, "covid19");
      homePage.openAilab();
      IMG();

      //  Prepare the dataset and verify the dataset name
      prepare.open();
      prepare.preparePXSDataset(dataset);
      prepare.isDatasetPrepDone();

      homePage.gotoEvaluatePage();
      evaluate.evaluatePXS(dataset);
      evaluate.verifyModelEvaluation();
      IMG();
      boolean result = evaluate.uploadEvaluation();
      IMG();

      // delete the dataset as a clean up ops
      homePage.openDataManager();
      dataManager.refreshPage();
      IMG();
      dataManager.searchForDataset(dataset);
      dataManager.deleteDataset(dataset);

      Assert.assertTrue(result);
   }

   // AS ailab user, WHEN I try to do model evaluation with corrupted model or incorrect dataset,
   // THEN I should see "Failed" evaluation status
   // -- DONE
   @Test
   public void moon_t335() {
      final String dataset = "BD_FAIL_" + randomeName();

      // Prepare the page object models
      HomePage homePage = new HomePage();
      PreparePage prepare = new PreparePage();
      EvaluatePage evaluate = new EvaluatePage();

      // ---- Test Scripts ----- //
      loginToACRConnect();
      gotoDICOMService();
      createBreastDensityDataset(dataset, "bd_20_skewed");
      openAilabApp();
      
      //  Prepare the dataset and verify the dataset name
      prepare.open();
      prepare.prepareBreastDensityDataset(dataset);
      prepare.waitUntilPrepareIsDone();
      prepare.isDatasetPrepDone();
      IMG();

      // Go to Evaluation page and try to evaluate the skewed dataset
      homePage.gotoEvaluatePage();
      IMG();
      evaluate.evaluateBDModel(dataset);
      IMG();
      //evaluate.verifyModelEvaluation();
      boolean result = evaluate.verifyFailedEvaluation();

      // delete the dataset as a clean up ops
      deleteDataset(dataset);

      Assert.assertTrue(result);
   }


   // As ailab user, WHEN I evaluate [PXS] model with skewed annotation distribution,
   // THEN I cannot evaluate the model
   // -- DONE 
   @Test
   public void moon_t368() {
      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      final String dataset = "PXS_SKEWED_" + randomeName();

      // Prepare the page object models
      HomePage homePage = new HomePage();
      PreparePage prepare = new PreparePage();
      DicomServicePage dicomService = new DicomServicePage();
      DataManagerPage dataManager = new DataManagerPage();
      EvaluatePage evaluate = new EvaluatePage();

      // ---- Test Scripts ----- //
      homePage.openTestEnv();
      homePage.signIn(username, pass);
      homePage.openDICOMService();
      dicomService.clickImportData();
      IMG();
      dicomService.createPXSDataset(dataset, "covid19_skewed");
      homePage.openAilab();
      IMG();

      //  Prepare the dataset and verify the dataset name
      prepare.open();
      prepare.preparePXSDataset(dataset);
      prepare.isDatasetPrepDone();
      IMG();

      // Go to Evaluation page and try to evaluate the skewed dataset
      homePage.gotoEvaluatePage();
      evaluate.evaluatePXS(dataset);
      //evaluate.verifyModelEvaluation();
      boolean result = evaluate.verifyUnableToEvaluate();
      IMG();

      // delete the dataset as a clean up ops
      homePage.openDataManager();
      dataManager.refreshPage();
      dataManager.searchForDataset(dataset);
      dataManager.deleteDataset(dataset);
      Assert.assertTrue(result);
   }

   // AS ailab user, WHEN I prepare a dataset and it failed,
   // THEN I can retry the dataset preparation again.
   // -- DONE 
   @Test
   public void moon_t322() {

      PreparePage prepare = new PreparePage();

      // ---- Test Scripts ----- //
      loginToACRConnect();
      openAilabApp();
   
      //  Prepare the dataset and verify the dataset name
      prepare.open();
      IMG();
      prepare.verifyRetryButton();
      IMG();
      prepare.retry();
      boolean result = prepare.verifyStatusOf("Failed");
      IMG();
      Assert.assertTrue(result);
   }

   // AS ailab user, WHEN I prepare a dataset that contains mixed modality,
   // THEN I should see "failed" dataset preparation
   // -- DONE 
   @Test
   public void moon_t367() {
      final String dataset = "Mixed_Dataset";

      // Prepare the page object models
      HomePage homePage = new HomePage();
      PreparePage prepare = new PreparePage();
      DataManagerPage dataManager = new DataManagerPage();

      // ---- Test Scripts ----- //
      loginToACRConnect();
      homePage.openDataManager();
      dataManager.createMixedModalityDataset(dataset);
      IMG();


      homePage.openAilab();
      prepare.open();
      prepare.prepareBreastDensityDataset(dataset);
      IMG();
      boolean result = prepare.verifyBreastDensityPrepFail();
      IMG();

      // Delete the dataset
      homePage.openDataManager();
      dataManager.refreshPage();
      dataManager.searchForDataset(dataset);
      dataManager.deleteDataset(dataset);
      IMG();
      Assert.assertTrue(result);
   }

}
















