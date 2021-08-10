package pages.ailab;

import org.openqa.selenium.By;
import utility.PageActions;

public class DefinePage extends PageActions {

   // ========= Elements ========= //
   private final By define_tabe = xpath("//span[text()='Define']");
   private final By loading_logo = id("loaderspinner");
   private final By searchInput = id("searchInput");
   private final By first_use_case_col = xpath("(//table[@id='defineusecase']/tbody/tr/td[6])[1]");


   // ========= User Actions ========= //
   public void open(){
      click(define_tabe);
      waitUntilElementInvisible(loading_logo);
   }

   public void freeTextSeach(String query) {
      write(searchInput, query);
   }

   public String getFirstRowUseCaseColText(){
     return findElement(first_use_case_col).getText();
   }

}
