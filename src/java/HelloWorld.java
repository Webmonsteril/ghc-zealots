import java.util.*;

public class HelloWorld {
    
    public void runTests{
         Result result = JUnitCore.runClasses(TestJunit.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
    }

    public static void main(String[] args) {
        
      
        
        // dori - test - do you see
        //I am see - Alex
        
    }
}