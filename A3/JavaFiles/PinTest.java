import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/** 
 * <h1> PinTest </h1>
 * File name: PinTest.java
 * @author Leila Erbay
 * <p> Purpose: create the test cases for Pin.java and test the public methods, confirmOldPin, setNewPin</p> 
 * <p> Side-effects: Print statements to signal that the result was false </p> 
 * <p> 	            which is expected to occur because not all test cases will be true </p> 
 * <p> Special notes: use of ExpectedException - prevents false responses since they are expected to create the exception </p> 
 * <p> 				testPrivateMethods - test created in Pin.java </p> 
 * */
public class PinTest {

	Pin userPin = new Pin();
	
	/**
	 * @author Leila Erbay
	 * <p> Purpose: test a method from Pin.java - confirmOldPin</p> 
	 * <p> Side-effects: if the pins are not equivalent,  statement is printed noting that the value is false </p> 
	 * <p> Special notes: true occurs only if the enteredPin = {'0','0'} </p> 
	 * <p> 				false occurs for every other kind of char array </p> 
	 */
	@Test 
    public void testConfirmOldPin(){
        for (int loopCtr = 0; loopCtr < 6; loopCtr++){
            for (int opt = 1; opt <3; opt ++){
                char [] enteredPin = CharArrayGenerator.randomCharArrayGenerator(loopCtr, opt);	//random pin is created

                if (Arrays.equals (enteredPin,(new char[] {'0','0'}))){
                    assertTrue(userPin.confirmOldPin(enteredPin));
                }
                else{ 
                	assertFalse(userPin.confirmOldPin(enteredPin));		//testing if both pins are equivalent	
                }
            }
        }
    }
	
	/**
	 * @author Leila Erbay
	 * <p> Purpose: test a method from Pin.java - setNewPin </p> 
	 * <p> 			creates a random pin and tries to set random pin as the new pin </p> 
	 * <p> Side-effects: will notify if the expected exception was not properly thrown </p> 
	 * <p> Special notes: note the use of Rule : ExpectedException </p> 
	 * <p> 				it's expected that any time an erroneous pin is tested, the exception will be thrown </p> 
	 */
	@Rule 
	public ExpectedException thrown = ExpectedException.none();
    @Test	
    public void testSetNewPin() throws Exception{
    	
        for (int loopCtr =0; loopCtr < 6; loopCtr++){
            for (int opt = 1; opt <3; opt ++){
                char [] enteredPin = CharArrayGenerator.randomCharArrayGenerator(loopCtr, opt);
                
                thrown.expect(InvalidPinException.class);		//expected exception = InvalidPinException
                thrown.expectMessage("Pin that's been entered is invalid. It does not pass the conditions of a new pin."); //expected message from InvalidPinException being thrown
                
                userPin.setNewPin(enteredPin);		
                
                assertArrayEquals(enteredPin, userPin.setNewPin(enteredPin));	//test of setNewPin
                fail("InvalidePinException not thrown but should have been");	//if exception is not thrown - something went wrong
              
            }
        }
    }

    
    /**
     * @author Leila Erbay
     * <p> Purpose: test all the private methods from within Pin.java </p> 
     * <p>  Side-effects: Print statements of the pins and the result of the test </p> 
     * <p> Special notes: calls tester which is the method that actually tests the private methods </p> 
     */
    @Test 
    public void testPrivateMethods() {
        userPin.tester();			//call to test private methdos
    }

}

