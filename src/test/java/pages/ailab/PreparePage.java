package pages.ailab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.DriverUtil;
import utility.PageActions;

import java.util.List;

public class PreparePage extends PageActions {

   // ========= Elements ========= //
   private final By prepareTab = xpath("//span[text()='Prepare']");
   private final By usecase_dropdown = id("useCases");
   private final By usecase_PXS = xpath("//option[text()='Pulmonary X-Ray Severity Score']");
   private final By usecase_breast_density = xpath("//option[text()='Breast Density']");
   private final By dataset_dropdown = id("selectDataset");
   private final By dataset_breast_density_option = xpath("//option[text()='BD Invalid 1']");
   private final By dataset_PXS_option = xpath("//option[text()='mRALE_Dataset']");
   private final By prepare_data_button = id("btnConvertToPng");
   private final By progress_bar = xpath("//div[@class='progress']/div");
   private final By retry_button = css(".outlineBtn");


   // ========= User Actions ========= //
   public void open() {
      click(prepareTab);
   }


   public void prepareBreastDensityDataset() {
      click(usecase_dropdown);
      click(usecase_breast_density);
      click(dataset_dropdown);
      click(dataset_breast_density_option);
      click(prepare_data_button);
   }

   public boolean verifyRetryButton() {
      WebElement retryButton = findElement(retry_button);
      highlight(retryButton);
      pausefor(2);
      return retryButton.isDisplayed();
   }

   public void retry() {
      click(retry_button);
      pausefor(8);
      reload();
      pausefor(3);
   }

   public boolean verifyBreastDensityPrepFail() {
      pausefor(5);
      reload();
      pausefor(2);
      WebElement status = findElement(xpath("(//tr)[3]/td[5]/span"));
      highlight(status);
      pausefor(3);
      return status.getText().equalsIgnoreCase("Failed");
   }

   public boolean verifyStatusOf(String targetStatus) {
      pausefor(2);
      WebElement status = findElement(xpath("(//tr)[2]/td[5]/span"));
      return status.getText().equalsIgnoreCase(targetStatus);
   }

   public void preparePXSDataset() {
      click(usecase_dropdown);
      click(usecase_PXS);
      click(dataset_dropdown);
      click(dataset_PXS_option);
      click(prepare_data_button);
   }

   public void preparePXSDataset(String datasetName) {
      click(usecase_dropdown);
      click(usecase_PXS);
      click(dataset_dropdown);
      By dataset = xpath("//option[text()='" + datasetName + "']");
      click(dataset);
      click(prepare_data_button);
   }

   public void prepareBreastDensityDataset(String datasetName) {
      click(usecase_dropdown);
      pausefor(3);
      click(usecase_breast_density);
      pausefor(3);
      click(dataset_dropdown);
      pausefor(3);
      By targetDataset = xpath("//option[text()='" + datasetName + "']");
      click(targetDataset);
      click(prepare_data_button);
      pausefor(6);
   }


   public boolean waitUntilPrepareIsDone() {
      WebElement progressBar = findPresentElement(progress_bar);
      boolean result = waitUntilElementInvisible(progress_bar);
      return result;
   }

   public boolean isBreastDensityDatasetPrepDone() {
      pausefor(3);
      List<WebElement> td = findElements(css("div.prepareDataTableContainer > div > div:nth-of-type(2) > div"));
      WebElement statusCol = td.get(4);
      WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), 900);
      By progressBar = css(".tb-row:nth-of-type(2) .progress-bar-animated");
      highlight(progressBar);
      wait.until(ExpectedConditions.invisibilityOfElementLocated(progressBar));
      boolean result = waitUntilTextPresent(statusCol, "Completed");
      pausefor(3);
      return result;
   }

   public boolean isDatasetPrepDone() {
      pausefor(3);
      List<WebElement> td = findElements(css("div.prepareDataTableContainer > div > div:nth-of-type(2) > div"));
      WebElement statusCol = td.get(4);
      waitUntilElementInvisible(css(".tb-row:nth-of-type(2) .progress-bar-animated"));
      return waitUntilTextPresent(statusCol, "Completed");
   }

   public boolean isPXSDatasetPrepDone() {
      pausefor(2);
      WebElement status = findElement(xpath("(//tr)[2]/td[5]/span"));
      System.out.println("STATUS: " + status.getText());
      boolean result = status.getText().equalsIgnoreCase("completed");
      return result;
   }

   public boolean isPXSDatasetPrepared() {
      pausefor(2);
      List<WebElement> td = findElements(css("div.prepareDataTableContainer > div > div:nth-of-type(2) > div"));
      WebElement statusCol = td.get(4);
      boolean result = waitUntilTextPresent(statusCol, "Completed");
      pausefor(2);
      return result;
   }

//   public boolean verifyDatasetName(String datasetName) {
//      By dataset_name = xpath("(//tr[@class='dataRow'])[1]/td[2]");
//      String nameFromTable = grabAsWebElement(dataset_name).getText();
//      return datasetName.equalsIgnoreCase(nameFromTable);
//   }

   public boolean verifyDatasetName(String datasetName) {
      pausefor(3);
      By all_dataset_name = xpath("(//tr[@class='dataRow'])/td[2]");
      List<WebElement> datasetNames = findElements(all_dataset_name);

      for (int i = 0; i < datasetNames.size(); i++) {
         WebElement elem = datasetNames.get(i);
         highlight(elem);
         String nameFromTable = elem.getText();
         System.out.println("Dataset Name From Table: " + nameFromTable);
         if (datasetName.equalsIgnoreCase(nameFromTable)) {
            return true;
         }
      }

      // dataset with given name is not found on
      // prepare page
      return false;
   }


   public boolean verifyDatasetPrep(String dataset, String useCase, String user) {
      List<WebElement> rows = findElements(xpath("//tr"));
      for (WebElement r : rows) {
         WebElement dataseCol = r.findElement(xpath("//td[2]"));
         System.out.println(dataseCol.getText());
         WebElement usecasCol = r.findElement(xpath("//td[3]"));
         System.out.println(usecasCol.getText());
         WebElement userCol = r.findElement(xpath("//td[7]"));


         boolean ret1 = dataseCol.getText().equals(dataset);
         boolean ret2 = usecasCol.getText().equals(useCase);
         boolean ret3 = userCol.getText().equals(user);
         if (ret1 && ret2 && ret3) {
            boolean result = r.findElement(xpath("//td[5]")).getText().equals("Completed");
            return result;
         }
      }
      return false;
   }

}
