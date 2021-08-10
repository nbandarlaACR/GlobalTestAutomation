package base;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.acrconnect.DataManagerPage;
import pages.acrconnect.DicomServicePage;
import pages.acrconnect.HomePage;
import pages.ailab.*;
import utility.DriverUtil;
import utility.Steps;

import java.util.ArrayList;
import java.util.List;


public abstract class TestBase {

   private HomePage homePage;
   private PreparePage prepare;
   private DicomServicePage dicomService;
   private DataManagerPage dataManager;
   private EvaluatePage evaluate;
   private RunPage runPage;
   private AnnotatePage annotate;
   private CreatePage create;

   @BeforeMethod
   public void beforeEachTestCase() {
      DriverUtil.openBrowser();
   }

   @AfterMethod
   public void afterEachTestCase() {
      DriverUtil.closeBrowser();
   }


   public void pauseBrowser(int second) {
      try {
         Thread.sleep(second * 1000);
      } catch (InterruptedException ie) {

      }
   }

   public String randomeName() {
      Faker faker = new Faker();
      return faker.name().firstName();
   }

   protected void switchTab(String targetPageTitle) {
      WebDriver driver = DriverUtil.getDriver();
      List<String> tabs = new ArrayList<>(driver.getWindowHandles());
      for (String title : tabs) {
         System.out.println("currently: " + title);
         if (title.equalsIgnoreCase(targetPageTitle)) {
            driver.switchTo().window(title);
            break;
         }
      }
   }

   // ===== Page Object Common Methods ======/
   protected void loginToACRConnect() {
      homePage().openTestEnv();
      homePage().signIn("tomprogrammanager@yahoo.com", "Pubg!super2023");
   }

   //--------------------------------Page Navigations Related ----------------------------------------
   protected void gotoDICOMService() {
      homePage().openDICOMService();
   }

   protected void openAilabApp() {
      homePage().openAilab();
   }

   protected void gotoRunInferencePage() {
      homePage().gotoRunPage();
   }


   //--------------------------------Dataset Related ----------------------------------------
   // Dataset creation
   protected void createBreastDensityDataset(String dataset, String excle) {
      dicomService().clickImportData();
      dicomService().createBreastDensityDataset(dataset, excle);
   }

   protected void createPXSDataset(String dataset) {
      dicomService().clickImportData();
      dicomService().createPXSDataset(dataset);
      dicomService().isDatasetIsCreated();
   }

   protected void createPXSDataset(String dataset, String excel) {
      dicomService().clickImportData();
      dicomService().createPXSDataset(dataset, excel);
      dicomService().isDatasetIsCreated();
   }


   // Dataset Prep
   protected void prepareBreastDensityDataset(String dataset) {
      prepPage().open();
      prepPage().prepareBreastDensityDataset(dataset);
      prepPage().waitUntilPrepareIsDone();
      prepPage().isDatasetPrepDone();
   }

   protected void preparePXSDataset(String dataset) {
      prepPage().open();
      prepPage().preparePXSDataset(dataset);
      prepPage().verifyDatasetName(dataset);
   }

   // Dataset Deletion
   protected void deleteDataset(String dataset) {
      homePage().openDataManager();
      //dataManager().refreshPage();
      dataManager().searchForDataset(dataset);
      dataManager().deleteDataset(dataset);
   }


   //--------------------------------Inference Related ------------------------------------------
   protected boolean verifyPXSInference(String dataset) {
      return runPage().verifyPXSInference(dataset);
   }


   //--------------------------------Page Object Instances----------------------------------------
   //==== Page Object Instances ====//
   protected HomePage homePage() {
      if (this.homePage == null) {
         this.homePage = new HomePage();
         return this.homePage;
      }
      return this.homePage;
   }

   protected EvaluatePage evaluatePage() {
      if (this.evaluate == null) {
         this.evaluate = new EvaluatePage();
         return this.evaluate;
      }
      return this.evaluate;
   }

   protected CreatePage createPage() {
      if (this.create == null) {
         this.create = new CreatePage();
         return this.create;
      }
      return this.create;
   }

   protected RunPage runPage() {
      if (this.runPage == null) {
         this.runPage = new RunPage();
         return this.runPage;
      }
      return this.runPage;
   }


   protected DataManagerPage dataManager() {
      if (this.dataManager == null) {
         this.dataManager = new DataManagerPage();
         return this.dataManager;
      }
      return this.dataManager;
   }

   protected DicomServicePage dicomService() {
      if (this.dicomService == null) {
         this.dicomService = new DicomServicePage();
         return this.dicomService;
      }
      return this.dicomService;
   }

   protected PreparePage prepPage() {
      if (this.prepare == null) {
         this.prepare = new PreparePage();
         return this.prepare;
      }
      return this.prepare;
   }

   protected AnnotatePage annotatePage() {
      if (this.annotate == null) {
         this.annotate = new AnnotatePage();
         return this.annotate;
      }
      return this.annotate;
   }


   //--------------- Report Related -----------------------------------------
   public void LOG(String message) {
      Steps.log(message);
   }

   public void IMG_LOG(String message) {
      Steps.log(message);
      Steps.imgStep("");
   }

   public void IMG() {
      Steps.imgStep("");
   }
}
