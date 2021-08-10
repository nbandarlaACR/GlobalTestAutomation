package pages.ailab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utility.PageActions;

import java.util.List;


public class AnnotatePage extends PageActions {
   //--- Elements ---//
   private By annotate_tab = css("[data-plugin] .has-sub:nth-of-type(5) .site-menu-title");

   //--- breast density ---//
   private By usecase_dropdown = id("useCases");
   private By breast_density_option = xpath("//option[text()='Breast Density']");
   private By PXS_Option = xpath("//option[text()='Pulmonary X-Ray Severity Score']");

   private By dataset_dropdown = id("dataset");
   private By breast_density_dataset = xpath("//option[text()='BD Invalid 1']");
   private By mRALE_dataset = xpath("//option[text()='mRALE_Dataset']");

   //----- Annotated Sections ----//
   private By annoated_tab = xpath("//div[@id='divFilterTab']/div[@class='tabs']/div[2]/div/div[@id='filterSelection']");
   // private By not_annotated_tab = xpath("//div[@id='divFilterTab']/div[@class='tabs']/div[1]/div/div[@id='filterSelection']");
   private By not_annotated_tab = css(".tabs > div:nth-of-type(1)");
   private By annoated_current_tab = css(".currentTab");

   //----- Breaset Density Options ----//
   private By option_1 = css("[data-option='1']");
   private By option_2 = css("[data-option='2']");
   private By option_3 = css("[data-option='3']");
   private By option_4 = css("[data-option='4']");
   private By next_button = xpath("/html//button[@id='resNextStudy']");
   private By save_continue_button = css(".SaveContinuebtn");
   private By prev_button = xpath("/html//button[@id='prevStudy']");
   private By skip_button = css("button#resSkipStudy");

   // Not Annotated Dropdown
   private By skipped_count = css(".custom-dropdown div:nth-of-type(2) span");

   //----- PXS Annotations ------//
   private By right_lung_extent = xpath("//div[contains(text(),' Right Lung (extent')]");
   private By right_lung_density = xpath("//div[contains(text(),' Right Lung (density')]");
   private By left_lung_extent = xpath("//div[contains(text(),' Left Lung (extent')]");
   private By left_lung_density = xpath("//div[contains(text(),' Left Lung (density')]");


   //--- Methods ---//
   public void open() {
      click(annotate_tab);
   }

   public void selectBreastDensity() {
      click(usecase_dropdown);
      click(breast_density_option);
      click(dataset_dropdown);
      click(breast_density_dataset);
   }

   public void goToAnnotatedSection() {
      click(annoated_tab);
      click(annoated_current_tab);
   }

   public void clickNext() {
      click(next_button);
   }

   public void clickSkip() {
      click(skip_button);
      pausefor(2);
   }

   public void clickNotAnnotatedDropdown() {
      clickRightSide(not_annotated_tab);
      pausefor(2);
   }

   public void annotateBreastDensityDataset() {
      click(css(".custom-dropdown > div:nth-of-type(1) > div:nth-of-type(2)"));
      pausefor(2);
      //click(css("[data-option='2']"));
      click(css(".div_options_withthumbnail_parent > div:nth-of-type(2)"));
      pausefor(2);
      click(save_continue_button);
      pausefor(8);
   }

   public void annotatePXSDataset() {
      By select_category = xpath("//ul[@class='multi-class-annotation-selector-list']/li//select");
      List<WebElement> categorySelects = findElements(select_category);
      for(WebElement select : categorySelects ) {
         highlight(select);
         Select options = new Select(select);
         options.selectByValue("1");
         pausefor(1);
      }
      pausefor(1);
      click(save_continue_button);
      pausefor(1);
   }

   public String getSkippedCount() {
      return findPresentElement(skipped_count).getText();
   }

   public boolean validateAnnotationUpdates() {
      try {
         click(option_3);
         click(next_button);
         click(option_2);
         click(next_button);
         click(prev_button);
         click(option_4);
         click(next_button);
         click(option_1);
         click(next_button);

         // every operation was successful
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public boolean validateAnnoataion() {
     By locator = css(".div_options_withthumbnail_parent > div:nth-of-type(2)");
     WebElement chosen = findElement(locator);
     pausefor(5);
     return chosen.isDisplayed();
   }

   public void validatePXSAnnotation() {
      click(right_lung_extent);
      click(right_lung_density);
      click(left_lung_extent);
      click(left_lung_density);
   }

   public void selectPXS() {
      click(usecase_dropdown);
      click(PXS_Option);
      click(dataset_dropdown);
      click(mRALE_dataset);
   }

   public void selectPXS(String datasetName) {
      click(usecase_dropdown);
      click(PXS_Option);
      click(dataset_dropdown);
      pausefor(1);
      By datasetDropdown = id("dataset");
      selectOptionWithText(datasetDropdown, datasetName);
   }

   public void selectBreastDensity(String datasetName) {
      click(usecase_dropdown);
      click(breast_density_option);
      click(dataset_dropdown);
//      waitfor(1);
//      selectOptionWithText(dataset_dropdown, datasetName);
      WebElement element = findElement(css("select#dataset"));
      Select options = new Select(element);
      for (WebElement elem : options.getOptions()) {
         if (elem.getText().equals(datasetName)) {
            highlight(elem);
            pausefor(1);
            elem.click();
            break;
         }
      }//end::for
   }
}
