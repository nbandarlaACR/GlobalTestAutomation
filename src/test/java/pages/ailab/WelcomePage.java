package pages.ailab;

import org.openqa.selenium.By;
import utility.PageActions;

public class WelcomePage extends PageActions {

   // ========= Elements ========= //
   private final By learn_label = xpath("//h1[text()='Learn']");
   private final By define_use_cases = xpath("//h1[text()='Define Use Cases']");
   private final By annoate = xpath("//h1[text()=' Annotate']");
   private final By create = xpath("//h1[text()=' Create']");
   private final By run = xpath("//h1[text()=' Run']");
   private final By evaluate = xpath("//h1[text()='Evaluate']");
   private final By publish = xpath("//h1[text()=' Publish']");
   private final By assess = xpath("//h1[text()='Assess']");
   private final By ai_community = xpath("//h1[text()=' AI Community']");
   private final By collaborate = xpath("//h1[text()='Collaborate']");


   // ========= User Actions ========= //
   public void verify_page_sections(){
      scrollToElement( findElement(learn_label) );
      findElement(define_use_cases);
      scrollToElement(findElement(annoate));
      findElement(create);
      scrollToElement(findElement(run));
      findElement(evaluate);
      scrollToElement(findElement(publish));
      findElement(assess);
      scrollToElement(findElement(ai_community));
      scrollToElement(findElement(collaborate));
   }
}
