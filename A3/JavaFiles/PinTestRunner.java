import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * <h1> PinTestRunner </h1>
 * File name: PinTestRunner.java
 * @author Leila Erbay
 * <p>Purpose: Single run test of all the methods from Pin.java </p> 
 * <p>			returns the final result of all the tests </p> 
 * <p>Side-effects: Prints true or false depending on the values of the tests </p> 
 * <p> Special note: --- </p> 
 *
 */
public class PinTestRunner{

	/**
	 * @author Leila Erbay
	 * <p> Purpose: run all tests for Pin.java </p> 
	 * @param args
	 * <p> Side-effects:Prints true or false depending on the values of the tests </p> 
	 * <p> Special note: --- </p> 
	 */
    public static void main(String [] args){

      Result result = JUnitCore.runClasses(PinTest.class);      //runs tests from PinTest
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
         
      }
		
      System.out.println("final result: \t" + result.wasSuccessful());
  	
    }
}
