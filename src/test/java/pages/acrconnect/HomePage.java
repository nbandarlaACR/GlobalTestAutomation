package pages.acrconnect;

import org.openqa.selenium.By;
import utility.PageActions;

public class HomePage extends PageActions {

   final private String URL = "https://rst-ltgpudemo2/";
   final private String URL2 = "https://connect-test.acr.org/";
   final private String URL3 = "https://connect-staging.acr.org/";
   final private String URL4 = "https://cdv-connectdeploy/";
   final private String HOME_PAGE_TITLE = "Home Page";
   final private String AILAB_PAGE_TITLE = "Home - AI-LAB";
   final private String DICOM_PAGE_TITLE = "DICOM";
   final private String DATA_MANGER_PAGE_TITLE = "Data Manager";

   // ========= Elements ========= //

   // Getting home ( login )
   private final By advanced_button = id("details-button");
   private final By proceed_link = id("proceed-link");
   private final By acrId_signin = xpath("//*[text()='Sign in with ACR ID']");
   private final By username_input = id("idp-discovery-username");
   private final By username_input_test_env = css("input#idp-discovery-username");
   private final By next_button = id("idp-discovery-submit");
   private final By password_input = id("okta-signin-password");
   private final By password_input_test_env = css("input#okta-signin-password");
   private final By signin_button = id("okta-signin-submit");

   // Home Page Icons
   private final By ailab_logo = xpath("//div/div/img[@alt='image']");
   private final By dicom_service_logo = xpath("(//a/div)[4]");
   private final By data_manager_logo = xpath("(//a/div)[3]");
   private final By ailab_homepage = css(".col-md-12.mt-10 h1");
   private final By enter_button = xpath("//button[text()=' ENTER ' ]");

   // AI-LAB Side Tabs
   private final By annotate_tab = xpath("//a[@href='Annotation/Index']/span[@class='site-menu-title']");
   private final By evaluate_tab = xpath("//ul//a[@href='Evaluate/OnPrem']/span[@class='site-menu-title']");
   private final By create_tab = xpath("//ul//a[@href='Create/TrainAndTest']");
   private By run_tab = xpath("//ul//a[@href='Run/OnPrem']/span[@class='site-menu-title']");


   // ========= User Actions ========= //
   public void openTestEnv() {
      gotoSite(URL2);
   }

   public void openCPUEnv() {
      gotoSite(URL4);
   }

   public void openDemo2() {
      gotoSite(URL);
      click(advanced_button);
      click(proceed_link);
   }

   public void signIn(String username, String password) {
      click(acrId_signin);
      write(username_input, username);
      click(next_button);
      write(password_input, password);
      click(signin_button);
   }

   public void singInTest(String username, String password) {
      click(acrId_signin);
      write(username_input_test_env, username);
      click(next_button);
      write(password_input_test_env, password);
      click(signin_button);
      waitUntilElementVisible(data_manager_logo);
   }


   /**
    * First time Opening AILAB app from ACR Connect home page
    */
   public void openAilab() {
      switchToTab(HOME_PAGE_TITLE);
      click(ailab_logo);
      switchToTab(AILAB_PAGE_TITLE);
      click(enter_button);
      pausefor(2);
   }

   public void swithToTheTab(String title) {
      switchToTab(title);
      pausefor(2);
   }


   /**
    * Switches to the open tabs with provided name
    *
    * @param title page title
    */
   public void gotoOpenPage(String title) {
      switchToTab(title);
      pausefor(1);
   }


   public void gotoAilab() {
      click(ailab_logo);
      // switchToTab(1);
      switchToTab(AILAB_PAGE_TITLE);
      click(enter_button);
   }


   /**
    * First time opening DICOM Service page from ACR Connect home page
    */
   public void openDICOMService() {
      click(dicom_service_logo);
      switchToTab(DICOM_PAGE_TITLE);
      pausefor(2);
   }

   /**
    * First time opening Data Manager page from the ACR Connect home page
    */
   public void openDataManager() {
      switchToTab(HOME_PAGE_TITLE);
      click(data_manager_logo);
      switchToTab(DATA_MANGER_PAGE_TITLE);
   }

   public void gotoDataManagerPage() {
      switchToTab(DATA_MANGER_PAGE_TITLE);
   }

   public String getConnectHomepageTitle() {
      return driver.getTitle();
   }

   // ========= Verifications  ========= //

   public void gotoAnnotatePage() {
      click(annotate_tab);
   }

   public void gotoEvaluatePage() {
      click(evaluate_tab);
   }

   public void gotoRunPage() {
      click(run_tab);
   }

   public void gotoCreatePage() {
      click(create_tab);
   }
}