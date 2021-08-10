package testcases.e2e;

import base.TestBase;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ailab.PreparePage;

@Listeners(utility.TestListener.class)
public class E2EPartOne extends TestBase {

   @Test
   public void moon_t309() {
      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      String name = new Faker().name().firstName();
      String datasetName = "Test_PXS_" + name;

      // ----- TEST STEPS ----- //

      // Test Script

      homePage().openTestEnv();
      homePage().signIn(username, pass);
      homePage().openDICOMService();

      dicomService().clickImportData();
      dicomService().createPXSDataset(datasetName);
      dicomService().isDatasetIsCreated();
      dicomService().switchToHomePage();
      homePage().openDataManager();

      dataManager().searchForDataset(datasetName);
      homePage().openAilab();
      PreparePage prepare = new PreparePage();
      prepare.open();

      prepare.preparePXSDataset(datasetName);
      boolean actual = prepare.isPXSDatasetPrepared();

      //homePage.openDataManager();
      homePage().gotoDataManagerPage();
      dataManager().searchForDataset(datasetName);
      dataManager().deleteDataset(datasetName);

      Assert.assertTrue(actual);
   }

   // AS a user, WHEN I create [Breast Density] data-set,
   // THEN I can only use it on Prepare page in its un-prepared
   // --- DONE
   @Test
   public void moon_t308() {

      final String username = "tomprogrammanager@yahoo.com";
      final String pass = "Pubg!super2023";
      String name = new Faker().name().firstName();
      String datasetName = "Test_BD_" + name;

      // ----- TEST STEPS ----- //
      homePage().openTestEnv();
      homePage().signIn(username, pass);
      homePage().openDICOMService();

      dicomService().clickImportData();
      dicomService().createBreastDensityDataset(datasetName);
      dicomService().isDatasetIsCreated();

      homePage().openAilab();
      prepPage().open();
      prepPage().prepareBreastDensityDataset(datasetName);
      boolean actual = prepPage().isBreastDensityDatasetPrepDone();
      deleteDataset(datasetName);

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
      prepPage().isBreastDensityDatasetPrepDone();

      // Go to AILAB Annotate page, and annotate the dataset
      homePage().gotoAnnotatePage();
      annotatePage().goToAnnotatedSection();
      annotatePage().selectBreastDensity(datasetName);

      // Go to Create page and configure the model
      homePage().gotoCreatePage();
      createPage().selectModel();
      createPage().configureModel();
      createPage().configureTraining(datasetName);

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
}
