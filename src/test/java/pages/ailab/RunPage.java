package pages.ailab;

import org.openqa.selenium.By;

import utility.DriverUtil;
import utility.PageActions;

public class RunPage extends PageActions  {

   private By usecase_select = id("useCases");
   private By breaset_density = css("option[value='16']");
   private By PXS = css("option[value='22']");
   private By model = id("trainModels");   // Breast Density MGH
   private By bd_model = xpath("//option[@value='Breast Density MGH']");
   private By dataset = id("selectDataset");
   private By bd_dataset = css("option[value='14']");
   private By mRALE_dataset = css("option[value='1']");

   /**
    * Verifies run inference for Breast Density model and dataset
    * @param datasetName
    * @return
    */
   public boolean verifyBreastDensityInference(String datasetName) {
      click(usecase_select);
      click(breaset_density);
      click(model);
      click(bd_model);

      // Select the data-set
      click(dataset);

      By dataset = id("selectDataset");
      selectOptionWithText(dataset, datasetName);
//      WebElement element = grabAsWebElement(id("selectDataset"));
//      Select datasetOptions = new Select(element);
//      for(WebElement opt : datasetOptions.getOptions()) {
//         if(opt.getText().equalsIgnoreCase(datasetName)){
//            opt.click();
//            break;
//         }
//      }
      click(dataset);

      // Run the prediction
      click(id("btnPredictionModelDesktop"));
      pausefor(5);
      waitUntilElementInvisible(css(".spinner-border"));
      // Verify the result
      return findElement(id("results-tbl")).isDisplayed();
   }

   public boolean verifyBreastDensityInference(String modelName, String datasetName) {
      click(usecase_select);
      click(breaset_density);
      click(model);
      pausefor(1);
      selectOptionWithText(model, modelName);
      pausefor(1);
      selectOptionWithText(dataset, datasetName);
      // Run the prediction
      click(id("btnPredictionModelDesktop"));
      pausefor(100);
    //  waitUntilElementInvisible(css(".spinner-border"));
      // Verify the result
      return DriverUtil.getDriver().findElement(By.id("results-tbl")).isDisplayed();
      //return findElement(id("results-tbl")).isDisplayed();
   }

   public boolean verifyPXSInference(String datasetName) {
      click(usecase_select);
      click(PXS);
      click(dataset);
      By datasetSelect = id("selectDataset");
      selectOptionWithText(datasetSelect, datasetName);
      click(dataset);
      click(id("btnPredictionModelDesktop"));
      waitUntilElementInvisible(css(".spinner-border"));
      pausefor(2);
      return findElement(id("results-tbl")).isDisplayed();
   }
}
