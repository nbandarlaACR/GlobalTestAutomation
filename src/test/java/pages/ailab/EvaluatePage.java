package pages.ailab;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utility.PageActions;

public class EvaluatePage extends PageActions {
   //------ ELEMENT ----------//
   private By use_case_select = id("useCaseSelect");
   private By breast_density = css("option[value='16']");
   private By pxs = css("option[value='18']");
   // models
   private By model_select = id("modelsSelect");
   private By BD_MGH = css("option[value='5']");
   // dataset
   private By dataset_select = id("datasetSelect");
   private By modelSelect = css("select#modelsSelect");
   private By datasetSelect = css("select#datasetSelect");
   private By bd_invalid = css("option[value='14']");
   private By mRALE_dataset = css("option[value='1']");
   // run
   private By run_button = css("button#runButton > div");
   private By run_evaluation = css("button#runButton > div");
   private By cancel_button = css("div:nth-of-type(2) > .actions > .outlineBtn");
   // errors
   private By dataset_validation_error_text = xpath("//div[@class='prompt']/div/div[1]");
   private By dataset_validation_error_ok = xpath("//div[@class='prompt']/div/div[2]/div");


   //------ USER ACTIONS ---------//
   public void evaluateBreastDensity() {
      click(use_case_select);
      click(breast_density);
      click(dataset_select);
      click(bd_invalid);
      click(xpath("//span[text()='Date']")); // click EVALUATION title
      click(run_button);
      pausefor(2);
      click(xpath("//div[text()='Pending']"));
      waitUntilElementInvisible(xpath("//div[text()='Pending']"));
      waitUntilElementInvisible(css(".progress-bar-animated"));
      pausefor(2);
   }

   public void evaluateBDModel(String datasetName) {
      // Select the use case
      click(use_case_select);

      // Select the model
      click(modelSelect);
      selectOptionWithValue(id("modelsSelect"), "5");

      // Select the data-set
      click(datasetSelect);
      pausefor(1);
      By dataset_dropdown = id("datasetSelect");
      selectOptionWithText(dataset_dropdown, datasetName);

      //click(datasetSelect);
      click(run_evaluation);
   }

   public void cancelEvaluation() {
      click(cancel_button);
      Alert alert = driver.switchTo().alert();
      alert.accept();
   }

   public boolean verifyModelEvaluation() {
      // wait until progress bar invisible
      waitUntilElementInvisible(css(".progress"));
      pausefor(2);
      return waitUntilTextPresent(xpath("(//div[@class='tb-row'])[1]/div[7]"), "Upload");
   }

   public boolean verifyPXSModelEvaluation() {
      // wait until progress bar invisible
     // waitUntilElementInvisible(css(".progress"));
      pausefor(5);
      return waitUntilTextPresent(xpath("(//div[@class='tb-row'])[1]/div[7]"), "Upload");
   }

   public boolean uploadEvaluation() {
      By upload_button = xpath("(//div[@class='tb-row'])[1]/div[7]/div[@class='outlineBtn']");
      click(upload_button);
      pausefor(2);
      By upload_success_ok = css(".promptOptions > div");
      WebElement okButton = waitUntilElementVisible(upload_success_ok);
      okButton.click();
      return okButton.isDisplayed();
   }

   public boolean verifyFailedEvaluation() {
      pausefor(2);
      By failed_status = xpath("//div/div[2]/div[@class='failed otherstats']");
      WebElement element = waitUntilElementVisible(failed_status);
      highlight(element);
      pausefor(1);
      return element.getText().equalsIgnoreCase("Failed");
   }


   public boolean verifyUnableToEvaluate() {
      return true;
   }

   public void evaluatePXS(String dataset) {
      // Select the use case
      click(use_case_select);
      click(pxs);

      // Select the dataset
      click(dataset_select);
      WebElement datasets = findElement(id("datasetSelect"));
      Select datasetOptions = new Select(datasets);
      for(WebElement opt : datasetOptions.getOptions()) {
         String txt = opt.getText().trim();
         if(txt.equalsIgnoreCase(dataset)) {
            opt.click();
            break;
         }
      }
      click(run_button);
      pausefor(15);
   }

   public void evaluatePXSWithCovid19V2(String dataset) {
      // Select the use case
      click(use_case_select);
      click(pxs);

      // Select the model 
      click(id("modelsSelect"));
      selectOptionWithValue(id("modelsSelect"), "8");

      // Select the dataset
      click(dataset_select);
      WebElement datasets = findElement(id("datasetSelect"));
      Select datasetOptions = new Select(datasets);
      for(WebElement opt : datasetOptions.getOptions()) {
         String txt = opt.getText().trim();
         if(txt.equalsIgnoreCase(dataset)) {
            opt.click();
            break;
         }
      }
      click(run_button);
   }

   public boolean deleteEvaluation(String datasetName) {
      // Initiate the delete
      WebElement firstRow = findElement(xpath("(//div[@class='tb-row'])[1]"));
      highlight(firstRow);
      By first_delete_button = xpath("(//div[@class='tb-row'])[1]/div[8]//span");
      click(first_delete_button);

      // Accept the Alert
      Alert alert = waits.until(ExpectedConditions.alertIsPresent());
      pausefor(2);
      String alertText = alert.getText();
      System.out.println("Alert text was: " + alertText);
      pausefor(1);
      alert.accept();
      boolean result = alertText.contains("delete this evaluation");
      return result;
   }

   public boolean verifyDatasetValidationError() {
      String errorMessage = findElement(dataset_validation_error_text).getText();
      String expecting = "This dataset has studies which have not been annotated";
      boolean result = errorMessage.contains(expecting);
      pausefor(1);
      click(dataset_validation_error_ok);
      return result;
   }
}
