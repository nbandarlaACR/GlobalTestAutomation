package pages.acrconnect;

import org.openqa.selenium.By;
import utility.PageActions;

import java.io.File;

public class DicomServicePage extends PageActions {

   // ===== Elements ===== //

   // Import Data & Annotations sub-tab Elements
   private final By import_data_tab = xpath("//li/a[text()='Import data & annotations']");
   private final By data_source_dropdown = xpath("//select[@ID='sever']");
   private final By dcm4chee_web = xpath("//option[text()='DCM4CHEE Web']");
   private final By dcm4chee_web_test_env = xpath("//option[text()='Local PACS (DICOMWEB)']");
   private final By choose_file_button = xpath("//input");
   private final By file_drop_area = css(".upload_background");

   // Servers sub-tab Elements
   private final By servers_tab = css("[href='\\#\\/servers']");
   private final By create_new_server = css(".btn.btn-secondary.float-right");
   private final By server_name_field = css("#createModal [placeholder='Name']");
   private final By server_drop_down = xpath("(/html//select[@id='source_type'])[1]");
   private final By file_server = xpath("//option[text()='File System']");
   private final By webDicom_server = xpath("//option[text()='Web DICOM']");
   private final By host_ip_field = css("#createModal [placeholder='Host\\/IP']");
   private final By acr_dataset_pilot1_file = xpath("(//*[text()='acr-dataset-pilot1'])[2]");
   private final By browse_file = css("#createModal [data-toggle]");
   private final By ok_button = xpath("(//*[text()='OK'])[1]");
   private final By save_button = xpath("(//*[text()='Save'])[1]");
   private final By verify_server_button = css("tr:nth-of-type(2) > td:nth-of-type(4) > button:nth-of-type(1)");
   private final By delete_server = css("tr:nth-of-type(7) > td:nth-of-type(4) > button:nth-of-type(3)");

   // Search & Retrieve sub-tab Elements
   private final By search_and_retrieve_sub_tab = css("li:nth-of-type(3) > .nav-link");
   private final By server_drop_down_search_and_retrieve = css("select#sever");
   private final By accession_number_field = css("[class] [class='col-3']:nth-of-type(3) [type]");
   private final By search_button = css(".float-right > .btn.btn-primary");
   private final By search_and_retrieve_result_confirmation = css("snack-bar-container[role='status']  span");
   private final By select_all_search_result = css("th:nth-of-type(1) > input[type='checkbox']");
   private final By retrieve_search_result = css("app-search-and-retrieve .row:nth-of-type(4) .btn-secondary.ng-star-inserted");

   // File upload map window
   private final By annotations = xpath("//span[text()='Select to map']");
   private final By annotatinos_select_all = xpath("//div[text()='Select All']");
   private final By annotations_select_breast_density = css(".item2  div");
   private final By create_dataset_radio = xpath("//span[text()='Create data set']");
   private final By dataset_name_input = xpath("//input[@name='createDataSetName']");
   private final By done_button = xpath("//button[text()='Done ']");
   private final By dataset_creation_confirmation = css("snack-bar-container[role='status']  span");

   // Flash elements
   private final By dataset_creation_comfirmation = css("snack-bar-container[role='status']  span");
   private final By study_retrieval_status = css("app-import .ng-star-inserted:nth-of-type(5) .float-right");


   // User Actions
   public void clickImportData() {
      pausefor(2);
      click(import_data_tab);
   }


   // ===== Actions on Page ===== //

   public void createPXSDataset(String datasetName) {
      pausefor(2);
      click(data_source_dropdown);
      pausefor(1);
    //  click(dcm4chee_web);
      click(dcm4chee_web_test_env);
      pausefor(3);
      String file = System.getProperty("user.dir") + "/src/test/resources/excels/pxs/covid19.xlsx";
      dropFile(new File(file), file_drop_area);
      pausefor(2);
      click(annotations);
      click(annotatinos_select_all);
      click(create_dataset_radio);

      // create randomized dataset name
      write(dataset_name_input, datasetName);
      click(done_button);
   }

   public void createPXSDataset(String datasetName, String excel) {
      click(data_source_dropdown);
//      click(dcm4chee_web);
      click(dcm4chee_web_test_env);
      pausefor(3);
      String file = System.getProperty("user.dir") + "/src/test/resources/excels/pxs/" + excel + ".xlsx";
      dropFile(new File(file), file_drop_area);
      pausefor(2);
      click(annotations);
      click(annotatinos_select_all);
      click(create_dataset_radio);

      // create randomized dataset name
      write(dataset_name_input, datasetName);
      click(done_button);
   }

   public void createBreastDensityDataset(String datasetName) {
      click(data_source_dropdown);
    //  click(dcm4chee_web);
      click(dcm4chee_web_test_env);
     // click(choose_file_button);
      pausefor(2);
      String file = System.getProperty("user.dir") + "/src/test/resources/excels/breastdensity/bd_98.xlsx";
      dropFile(new File(file), file_drop_area);
      pausefor(2);
      click(annotations);
      click(annotations_select_breast_density);
      click(create_dataset_radio);

      // create randomized dataset name
      write(dataset_name_input, datasetName);
      click(done_button);
      By process = css("app-import .ng-star-inserted:nth-of-type(5) .float-right");
      waitUntilElementInvisible(process);
      pausefor(5);
   }

   public void createBreastDensityDataset(String datasetName, String excel) {
      click(data_source_dropdown);
     // click(dcm4chee_web);
      click(dcm4chee_web_test_env);
    //  click(choose_file_button);
      pausefor(1);
      String file = System.getProperty("user.dir") + "/src/test/resources/excels/breastdensity/" + excel + ".xlsx";
      dropFile(new File(file), file_drop_area);
      pausefor(2);
      click(annotations);
      click(annotations_select_breast_density);
      click(create_dataset_radio);

      // create randomized dataset name
      write(dataset_name_input, datasetName);
      click(done_button);
      waitUntilElementInvisible(study_retrieval_status);
      waitUntilElementVisible(dataset_creation_comfirmation);
   }

   public void createCovidDatasetTestEnv(String datasetName) {
      click(data_source_dropdown);
      click(dcm4chee_web_test_env);
      click(choose_file_button);
      pausefor(1);
      String file = System.getProperty("user.dir") + "/src/test/resources/excels/pxs/covid19.xlsx";
      dropFile(new File(file), file_drop_area);
      pausefor(2);
      click(annotations);
      click(annotatinos_select_all);
      click(create_dataset_radio);

      // create randomized dataset name
      write(dataset_name_input, datasetName);
      click(done_button);
   }

   public void createBreastDensityDatasetTestEnv(String datasetName) {
      click(data_source_dropdown);
      click(dcm4chee_web_test_env);
      click(choose_file_button);
      pausefor(1);
      String file = System.getProperty("user.dir") + "/src/test/resources/excels/breastdensity/bd_98.xlsx";
      dropFile(new File(file), file_drop_area);
      pausefor(2);
      click(annotations);
      click(annotations_select_breast_density);
      click(create_dataset_radio);

      // create randomized dataset name
      write(dataset_name_input, datasetName);
      click(done_button);
      pausefor(12);
   }

   public void isDatasetIsCreated() {
      waitUntilElementInvisible(dataset_creation_confirmation);
   }

   public void goToHomePage() {
      switchToTab(0);
   }

   public void switchToHomePage() {
      System.out.println("Switching to Home Page");
      switchToTab("Home Page");
   }

   public void createNewFileServer(String fileServerName) {
      click(servers_tab);
      click(create_new_server);
      write(server_name_field, fileServerName);
      pausefor(2);
      click(server_drop_down);
      click(file_server);
      pausefor(2);
      click(browse_file);
      click(acr_dataset_pilot1_file);
      click(ok_button);
      pausefor(2);
      click(save_button);
      pausefor(3);
   }

   public void createNewWebDICOMServer(String webDicomServerName) {
      click(servers_tab);
      click(create_new_server);
      write(server_name_field, webDicomServerName);
      pausefor(2);
      click(server_drop_down);
      click(webDicom_server);
      write(host_ip_field, "http://dcm4chee-arc:8080/dcm4chee-arc/aets/DCM4CHEE/rs/");
      pausefor(2);
      click(save_button);
      pausefor(3);
   }

   public void deleteServer() {
      click(servers_tab);
      pausefor(1);
      click(delete_server);
      pausefor(3);
   }

   public void searchAndRetrieve() {
      waitUntilElementVisible(search_and_retrieve_sub_tab);
      click(search_and_retrieve_sub_tab);
      click(server_drop_down_search_and_retrieve);
      click(dcm4chee_web_test_env);
      write(accession_number_field, "*");
      click(search_button);
      click(search_and_retrieve_result_confirmation);
      pausefor(2);
      click(select_all_search_result);
      click(retrieve_search_result);
      pausefor(10);
      click(search_and_retrieve_result_confirmation);
      pausefor(2);
   }


}
