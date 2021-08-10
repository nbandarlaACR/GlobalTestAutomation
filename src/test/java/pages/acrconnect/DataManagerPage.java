package pages.acrconnect;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utility.PageActions;

import java.util.List;

public class DataManagerPage extends PageActions {

   // ===== Elements ===== //

   public String actualDataCount;
   // Datasets section Elements
   private By datasets_section = css("[href='\\#dataSetMenu']");
   private By search_input = id("datasetSearch");
   private By select_all_button = css(".dataset-actions > button:nth-of-type(2)");
   private By delete_confirmation = css("#deleteConfirmModal [class] [data-dismiss='modal']:nth-child(2)");
   private By copy_button = css("button[title='Copy selected data objects']");
   private By create_new_copy_dataset = css("input[name='copyDataSetName']");
   private By confirm_copy = css("#copyDatasetModal .btn_group_dataSet [data-dismiss='modal']:nth-of-type(1)");
   private By search_bar = css("input#datasetSearch");
   private By dataset_data = css(".dataSetListingParentDiv .col-cell-data-wide:nth-of-type(1) .row [class='col-sm-6']:nth-of-type(2)");
   private By copy_studies_button = css("input[name='includeTag']");
   private By list_view = css("button#toggleListView > i[title='list view']");
   private By first_dataset = css("div:nth-of-type(1) > .chk_dataset.ng-pristine.ng-untouched.ng-valid");
   private By confirmation_message = css("div[role='alertdialog']");
   private By anonymize_button = css("button[title='Anonymize selected data objects']");
   private By select_anonymization_profile_drop_down = xpath("//div[@id='anonymizeDataModal']/div[@class='modal-dialog modal-lg']/div[@class='modal-content']/div[@class='modal-body']/div[@class='container-fluid']//select");
   private By output_anonymization_dataset = css("input[role='combobox']");
   private By confirm_anonymization_button = css("#anonymizeDataModal .btn_group_dataSet [data-dismiss='modal']:nth-of-type(1)");
   private By anonymization_confirmation_message = css("div:nth-of-type(3) > div[role='alertdialog']");
   private By empty_spot = css("div#anonymizeDataModal > .modal-dialog.modal-lg .container-fluid > div:nth-of-type(3)");

   // Dataset Edit
   private By edit_dataset = css(".dataset-actions > button:nth-of-type(1)");
   private By edit_dataset_name_input = xpath("(//div[@class='col-sm-12']/div[@class='form-group']/input)[1]");
   private By edit_save = css("[aria-modal] [type='submit']");


   // Data section Elements
   private By data_section = css("[href='\\#home']");
   private By select_all_button_data_section = xpath("//*[text()='Select All']");
   private By create_new_dataset_from_data_section = css(".pull-right > button:nth-of-type(2)");
   private By add_to_existing_dataset = xpath("//div[@id='home']/home-selector/div[@class='card']/div[@class='card-body']//div[@class='pull-right']/button[5]");
   private By confirm_add_to_existing_dataset = css(".btn.btn-addtodataset");
   private By dataset_name_field = css("input#name");
   private By save_button = css("#createModel [type='submit']");
   private By first_data_in_data_section = css("home-selector > .card .div_listing.row > div:nth-of-type(1)");
   private By second_data_in_data_section = css("home-selector > .card .div_listing.row > div:nth-of-type(2)");
   private By third_data_in_data_section = css("home-selector > .card .div_listing.row > div:nth-of-type(3)");
   private By fourth_data_in_data_section = css("home-selector > .card .div_listing.row > div:nth-of-type(4)");
   private By fifth_data_in_data_section = css("home-selector > .card .div_listing.row > div:nth-of-type(5)");


   // User Actions
   public void searchForDataset(String datasetName) {
      pausefor(3);
      clear(search_bar);
      write(search_input, datasetName);
      pausefor(3);
   }

   public void searchAndSelectDataset(String dataset) {
      clear(search_bar);
      write(search_input, dataset);
      By result_dataset = css("div#left_reportItems > div[title]");
      click(result_dataset);
      pausefor(2);
   }

   public void assertDataset() {
      Assert.assertTrue(false, "Dataset exists but still Pending");
   }

   public void clickOnSelectAll() {
      click(select_all_button);
      pausefor(2);
      WebElement act_count = driver.findElement(By.cssSelector(".badge-success"));
      actualDataCount = act_count.getText();
      pausefor(3);
   }


   public void changeDatasetName(String dataset, String newName) {
      click(edit_dataset);
      click(edit_dataset_name_input);
      clear(edit_dataset_name_input);
      write(edit_dataset_name_input, newName);
      pausefor(1);
      click(edit_save);
   }

   public void deleteDataset(String title) {
      By dataset_delete_button = css("div[title='" + title + "'] .fa.fa-trash.text-danger");
     // By dataset_delete_button = css(".fa.fa-trash.text-danger");
      //waitUntilElementVisible(dataset_delete_button);
      click(dataset_delete_button);
      By delete_confirmation_modal = css("div#deleteConfirmModal > div .modal-content");
      waitUntilElementVisible(delete_confirmation_modal);
      click(delete_confirmation);
   }

   public void copyDatasetWithoutAnnotations(String dataset) {
      click(select_all_button);
      click(copy_button);
      write(create_new_copy_dataset, dataset);
      pausefor(2);
      click(confirm_copy);
      pausefor(3);
   }

   public void copyDatasetWithAnnotations(String dataset) {
      click(select_all_button);
      click(copy_button);
      write(create_new_copy_dataset, dataset);
      pausefor(1);
      click(copy_studies_button);
      pausefor(1);
      click(confirm_copy);
      pausefor(3);
   }

   public void createMixedModalityDataset(String datasetName) {
      // 1. click data
      click(css("li:nth-of-type(2) > .nav-link"));
      // 2. Search CH and click few
      By input_text = xpath("(//input[@type='text'])[5]");
      WebElement input = findElement(input_text);
      highlight(input);
      input.sendKeys("CH");
      pausefor(2);
      click(xpath("(//div[@class='row div_listing'])[2]/div[1]"));  // click item 1
      click(xpath("(//div[@class='row div_listing'])[2]/div[2]"));  // click item 2
      click(xpath("(//div[@class='row div_listing'])[2]/div[3]"));  // click item 3
      // 3. Create Dataset
      pausefor(2);
      click(css(".pull-right > button:nth-of-type(2)"));
      pausefor(1);

      // 4. Enter a name
      write(css("input#name"), datasetName);
      pausefor(1);

      // 5. Click save
      click(css("[aria-modal] [type='submit']"));

      // 6. search for breast
      pausefor(2);
      clear(input_text);
      pausefor(1);
      write(input_text, "Breast");
      pausefor(2);
      click(xpath("(//div/img[@class='pull-right'])[16]"));  // click item 1
      click(xpath("(//div/img[@class='pull-right'])[17]"));  // click item 2
      click(xpath("(//div/img[@class='pull-right'])[18]"));  // click item 3

      // 7. Add to dataset
      pausefor(1);
      click(css(".pull-right > button:nth-of-type(5)"));
      // search for dataste
      By targetDataset = xpath("//div[@class='row']//span[text()='"+datasetName+"']");
      moveViewToElement(targetDataset);
      click(targetDataset);
      By add_to_dataset = css(".btn.btn-addtodataset");
      moveViewToElement(add_to_dataset);
      click(add_to_dataset);

      // wait until visible
      pausefor(2);
      highlight(css("#toast-container"));
   }

   public void createDataset(String dataset) {
      click(data_section);
      click(select_all_button_data_section);
      pausefor(2);
      click(create_new_dataset_from_data_section);
      write(dataset_name_field, dataset);
      click(save_button);
      pausefor(1);
      click(confirmation_message);
      click(datasets_section);
      pausefor(4);
   }

   public void refreshPage(){
      reload();
   }

   public void addDataToExistingDataset() {
      click(data_section);
      click(first_data_in_data_section);
      click(second_data_in_data_section);
      click(third_data_in_data_section);
      click(fourth_data_in_data_section);
      click(fifth_data_in_data_section);
      click(add_to_existing_dataset);
      pausefor(1);
      click(first_dataset);
      click(confirm_add_to_existing_dataset);
      pausefor(1);
      click(confirmation_message);
   }

   public void anonymizationWithDefaultProfile(String datasetName) {
      click(select_all_button);
      click(anonymize_button);
      pausefor(2);
      WebElement select_anon_prof = driver.findElement(By.xpath("//div[@id='anonymizeDataModal']/div[@class='modal-dialog modal-lg']/div[@class='modal-content']/div[@class='modal-body']/div[@class='container-fluid']//select"));
      select_anon_prof.click();
      Select select = new Select(select_anon_prof);
      pausefor(1);
      select.selectByVisibleText("DefaultProfile");
      write(output_anonymization_dataset, datasetName);
      click(empty_spot);
      click(confirm_anonymization_button);
      pausefor(20);
      click(anonymization_confirmation_message);
   }

   public void anonymizationWithLookupProfile(String datasetName) {
      click(select_all_button);
      click(anonymize_button);
      pausefor(2);
      WebElement select_anon_prof = driver.findElement(By.xpath("//div[@id='anonymizeDataModal']/div[@class='modal-dialog modal-lg']/div[@class='modal-content']/div[@class='modal-body']/div[@class='container-fluid']//select"));
      select_anon_prof.click();
      Select select = new Select(select_anon_prof);
      pausefor(1);
      select.selectByVisibleText("LookupProfile");
      write(output_anonymization_dataset, datasetName);
      click(empty_spot);
      click(confirm_anonymization_button);
      pausefor(20);
      click(anonymization_confirmation_message);
   }

   public boolean validateBreastDensityDatasetAnnotation(String annotationValue){
      click(css("button#toggleListView"));  // toggle list view
      click(css(".table_listing tr:nth-of-type(1) [data-target]")); // click first study
      pausefor(2);
      WebElement calssification = findElement(css("[role='navigation']"));
      String text = calssification.getText();
      pausefor(2);
      click(css("div#viewDataModel > div[role='document'] button > span"));
      return text.contains(annotationValue);

   }


   public boolean validatePXSDatasetAnnotation(String annotationValue){
      click(css("button#toggleListView"));  // toggle list view
      click(css(".table_listing tr:nth-of-type(1) [data-target]")); // click first study
      List<WebElement> calssification = findElements(css("[role='navigation']"));
      WebElement chosen = calssification.get(1);
      highlight(chosen);
      String text = chosen.getText();
      pausefor(1);
      click(css("div#viewDataModel > div[role='document'] button > span"));  // click close button
      return text.contains(annotationValue);
   }
}
