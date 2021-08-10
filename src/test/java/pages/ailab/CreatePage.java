package pages.ailab;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utility.PageActions;

public class CreatePage extends PageActions {


   public void selectModel() {
      // Select Breast Density Use Case
      click(css("#useCasesSelect"));
      click(css("select#useCasesSelect > option[value='16']"));

      // Model Category
      click(css("select#modelCategory"));
      click(css("select#modelCategory > option[value='shared']"));

      // Select Model
      click(css("select#sharedModel"));
      click(css("select#sharedModel > option[value='[object Object]']"));
   }


   public void configureModel() {
      // Sampling Method
      click(css("select#equalsampling"));
      click(css("[value='Equal Class Ratios']"));

      // Loss Function
      click(css("select#lossfunction"));
      click(css("select#lossfunction > option[value='categorical-crossentropy']"));

      // Early Stopping
      click(css("select#earlystopping"));
      click(css("select#earlystopping > option[value='True']"));
   }

   public void configureTraining(String dataset) {
      // Prepared Dataset
      moveToVewBotton();
      click(css("select#preparedDataset"));
      WebElement element = findElement(xpath("//select[@id='preparedDataset']"));
      Select options = new Select(element);
      options.selectByVisibleText(dataset);
      // By bd_dataset = xpath("//select[@id='preparedDataset']//option[text()='" + dataset + "']");
      //click(bd_dataset);
      pausefor(1);
      click(css("select#augmentation"));
      click(css("[value='Random flips\\/rotations']"));
   }

   // delete this method
   public void configureTraining() {
      // Prepared Dataset
      moveToVewBotton();
      click(css("select#preparedDataset"));
      By bd_dataset = xpath("//select[@id='preparedDataset']//option[text()=]");
      click(bd_dataset);

   }
}
