package testcases.smoke;

import base.TestBase;

public class AilabSmoke extends TestBase {
//
//   //---- AUTH,DEFINE PAGE ------//
//   @Test
//   public void login_test() {
//      // Test Data
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Pubg!super2020";
//
//      // Test Steps
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//      homePage.gotoAilab();
//
//      boolean actual = homePage.verifyAilabHomePageDisplayed();
//      Assert.assertTrue(actual);
//   }
//
//   @Test
//   public void verify_welcome_page() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      WelcomePage welcomePage = new WelcomePage();
//      welcomePage.verify_page_sections();
//   }
//
//   @Test
//   public void verify_define_page() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//      final String expected = "HCC Screening";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      DefinePage definePage = new DefinePage();
//      definePage.open();
//      definePage.freeTextSeach("HCC Screening");
//      String actual = definePage.getFirstRowUseCaseColText();
//
//      Assert.assertEquals(expected, actual);
//   }
//
//
//
//   //---- PREPARE PAGE ------//
//   @Test
//   public void verify_prepare_breast_density() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      PreparePage prepare = new PreparePage();
//      prepare.open();
//      prepare.prepareBreastDensityDataset();
//      boolean actual = prepare.waitUntilPrepareIsDone();
//
//      // take care of the progress bar
//      // completion detection
//      Assert.assertTrue(actual, "Prepare operation has failed");
//   }
//
//   @Test
//   public void verify_prepare_PXS() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      PreparePage prepare = new PreparePage();
//      prepare.open();
//      prepare.preparePXSDataset();
//      boolean actual = prepare.verifyDatasetPrep("mRALE_Dataset", "Pulmonary X-Ray Severity Score", "tomprogrammanager@yahoo.com");
//      // take care of the progress bar
//      // completion detection
//      Assert.assertTrue(actual, "Prepare operation has failed");
//   }
//
//
//
//   //---- ANNOTATE PAGE ------//
//   @Test
//   public void verify_breastDensity_dataset_annotation() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      homePage.gotoAnnotatePage();
//
//      AnnotatePage annotate = new AnnotatePage();
//      annotate.selectBreastDensity();
//      annotate.goToAnnotatedSection();
//      annotate.validate_annoatation_update();
//   }
//
//   @Test
//   public void verify_annoated_PXS_dataset() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      homePage.gotoAnnotatePage();
//
//      AnnotatePage annotate = new AnnotatePage();
//      annotate.selectPXS();
//      annotate.goToAnnotatedSection();
//      annotate.validatePXSAnnotation();
//   }
//
//
//
//   //---- EVALUATE PAGE ------//
//   @Test
//   public void verify_breasetDensity_evaluate() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      homePage.gotoEvaluatePage();
//
//      EvaluatePage eval = new EvaluatePage();
//      eval.evaluateBreastDensity();
//   }
//
//   @Test
//   public void validate_PXS_evaluate() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      homePage.gotoEvaluatePage();
//
//      EvaluatePage eval = new EvaluatePage();
//      eval.evaluatePXS();
//   }
//
//
//
//   //---- RUN PAGE ------//
//   @Test
//   public void validate_run_inference_BD() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      homePage.gotoRunPage();
//      RunPage runPage = new RunPage();
//      runPage.verifyBreastDensityInference();
//   }
//
//   @Test
//   public void validate_run_inference_PXS() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      homePage.gotoRunPage();
//      RunPage runPage = new RunPage();
//      runPage.verifyPXSInference();
//   }
//
//
//
//   //---- CREATE PAGE & MODELS PAGE ----//
//   @Test
//   public void verify_model_creation() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      homePage.gotoCreatePage();
//
//      CreatePage createPage = new CreatePage();
//      createPage.selectModel();
//      createPage.configureModel();
//      createPage.configureTraining();
//   }
//
//   @Test
//   public void verify_model_public_share() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      homePage.gotoCreatePage();
//
//      CreatePage createPage = new CreatePage();
//     
//   }
//
//   @Test
//   public void verify_model_private_share() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      homePage.gotoCreatePage();
//
//      CreatePage createPage = new CreatePage();
//      
//   }
//
//   @Test
//   public void verify_model_deletion() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      homePage.gotoCreatePage();
//
//      CreatePage createPage = new CreatePage();
//    
//   }
//
//   @Test
//   public void verify_shared_model_visibility() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      homePage.gotoCreatePage();
//
//      CreatePage createPage = new CreatePage();
//   
//   }
//
//   @Test
//   public void verify_shared_model_edition() {
//      final String username = "tomprogrammanager@yahoo.com";
//      final String pass = "Amina2020!!max";
//
//      // ----- TEST STEPS ----- //
//      //1. User goes to ACR Connect home page
//      HomePage homePage = new HomePage();
//      homePage.openDemo2();
//      homePage.signIn(username, pass);
//
//      //2. User goes to the AILAB
//      homePage.gotoAilab();
//      homePage.gotoCreatePage();
//
//      CreatePage createPage = new CreatePage();
//    
//   }

}