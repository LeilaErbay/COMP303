import java.util.Arrays;
/**
 * <h2>InvalidPinException </h2>
 * @author Leila Erbay
 * <p> Purpose: Custom made exception to specify that an entered pin did not meet conditions </p> 
 * @param String msg - will be used to notify the user of the error they have caused
 * <p> Side-effects: When Exception is thrown it will print a message by constructor of super class </p> 
 * <p> Special notes: --- </p> 
 */
class InvalidPinException extends Exception {
    InvalidPinException(String msg){
        super(msg);
    }
}


/**
 * <h1> Pin </h1>
 * File name: Pin.java
 * @author Leila Erbay
 * <p> Purpose: Initialize new pin; determine if user enters its correct old pin; </p> 
 * <p>          update old pin with new pin entered by the user; </p> 
 * <p> Side-effects: Exceptions thrown and caught, Print Statements </p> 
 * <p> Special Notes: Contains special tester for private methods </p> 
 */
public class Pin{
    
    //userPin - global variable that will be altered in following functions
    private char [] userPin;
  

    /**
     * @author Leila Erbay
     * <p> Purpose: each time new pin is created, intial pin is set to 00 </p> 
     * <p> Side-effects: sets user's initial pin to 00 </p> 
     * <p> Special notes: global variable - userPin - is altered </p> 
     */
    public Pin() {
        this.userPin = new char[] {'0','0'};
    }


    /**
     * @author Leila Erbay
     * <p> Purpose:compare userPin to the pin entered by the user to confirm the user's previous pin </p> 
     * @param newPin entered pin that will be compared to userPin
     * @return boolean comparison value
     * <p> Side-effects: --- </p> 
     * <p> Special notes: uses checkValue to compare two pins </p> 
     */
    public boolean confirmOldPin(char [] newPin) {
        boolean result = false;
        try{
            if (newPin.length != 2) return result;              //if entered pin is not a length of 2, already false
            else if (checkValue(newPin)) result = true;         //use checkValue to compare the values of the 2 pins
            else result = false;
        } 
        catch(Exception e){
            System.out.println("Exception occurred: " + e.getMessage());
            result = false;
        }
        finally{
            return result;
        }
    }
    
    
    /**
     * @author Leila Erbay
     * <p> Purpose:  set user pin to the new pin entered by user </p> 
     * @param enteredPin pin entered by user
     * @return char[] a clone of the object
     * <p> Side-effect:  update global variable userPin to newPin (newest pin entered by user) if it passes check </p> 
     * @throws InvalidPinException  if user doesn't enter a valid pin
     * <p> Special notes: uses private method validateNewPin to ensure that the pin entered by the user meets necessary conditions </p> 
     */
    public char[] setNewPin(char [] enteredPin) throws InvalidPinException{
        if (!validateNewPin(enteredPin)){
           throw new InvalidPinException("Pin that's been entered is invalid. It does not pass the conditions of a new pin.");
        }
        else this.userPin = enteredPin;
        return this.userPin.clone();
    }


    
    /**
     * @author Leila Erbay
     * <p> Purpose: to check the value of the newPin to the currentPin </p> 
     * @param newPin pin entered by user
     * @return boolean comparison value
     * <p> Side-effect: --- </p> 
     * <p> Special notes: ArrayIndexOutOfBoundsException may occur if the two pins are not the same length </p> 
     */
    private boolean checkValue(char [] newPin){
        boolean result = false;
        try{
            //check if new pin entered by user is valid based on length
            
             result = Arrays.equals(newPin,this.userPin ); 		//tests if 2 char arrays are equal
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Exception occurred when comparing pins " + e.getMessage());
            result = false;
        }
        finally{
            return result;
        }
    }


    /**
     * @author Leila Erbay
     * <p> Purpose: Check user enters a valid new pin and update the user's pin once valid pin is determined </p> 
     * @param newPin pin entered by user
     * @return boolean comparison value
     * <p> Side-effect: --- </p> 
     * <p> Special notes: this private method is used in setPin function to make sure user input meets conditions of a valid pin </p> 
     */
    private boolean validateNewPin(char [] newPin){
        boolean newPinConfirmed = false;
        try{   

            //check if pin entered by user is a number and not a character
            for (int i = 0; i < newPin.length; i++){
                if (newPin[i] < 48 || newPin[i] > 57){
                    return newPinConfirmed;
                }
            }
         
            if (newPin.length != 2) newPinConfirmed = false;
            //if checkValue = true, then the two pins are the same, which is an error
            else if (checkValue(newPin)) newPinConfirmed = false;
   
            //new pin passed every test thus newPinConfirmed set to true
            else newPinConfirmed = true;

        }  catch(Exception e){
            System.out.println(e.getMessage());
            newPinConfirmed = false;
        }

        finally{
            return newPinConfirmed;
        }
    }


    /**
     * @author Leila Erbay
     * <p> Purpose: depending on the values of the params, return a boolean value of from testing one of the private validation methods </p> 
     * @param int loopCtr required param for a CharArrayGenerator method
     * @param int option required param for CharArrayGenerator method
     * @param int method selects which method to be tested
     * @return boolean - value from testing a particular method
     * <p> Side-effect: --- </p> 
     * <p> Special notes: exception may occur </p> 
     */
    private boolean testPrivateMethods(int loopCtr, int option, int method){
        boolean result = false;
        char [] testArray = CharArrayGenerator.randomCharArrayGenerator(loopCtr, option);
        switch(method){
            case 0: result = (checkValue(testArray));           //test method: checkValue
                                    break;
            case 1: result = (validateNewPin(testArray));       //test method: validateNewPin
                                    break;
        }
        System.out.print("original pin: " + Arrays.toString(this.userPin)+ "  enteredPin: " + Arrays.toString(testArray) );
        return result;
    }

    /**
     * @author Leila Erbay
     * <p> Purpose: Specifically test validation methods against pin 00 </p> 
     * @param int method - selects which method to test with 00
     * @return boolean - value of comparison between pins
     * <p> Side-effects: --- </p> 
     * <p> Special notes: --- </p> 
     */
    private boolean testOriginalPin(int method){
        char [] origPin = {'0','0'};
        System.out.print("original pin: " + Arrays.toString(this.userPin)+ "  enteredPin: " + Arrays.toString(origPin) );
        if (method == 0) return checkValue(origPin);
        else if (method == 1) return validateNewPin(origPin);    
        else return false;
    }

    /**
     * @author Leila Erbay
     * <p> Purpose: test all private methods with random pins as well as test all methods with pin 00 </p> 
     * <p> Side-effect: Prints success and failed based on test of private methods </p> 
     * <p> Special notes: result of testing each method with 00 comes after testing random pins </p> 
     */
    public void tester() {
        for (int m = 0; m < 2; m++) {
            for (int arrLen = 0; arrLen < 5; arrLen++){
                for (int opt = 1; opt < 3; opt++){
                    try{
                        if (m == 0){
                            if (testPrivateMethods(arrLen, opt, 0)) System.out.println("\tcheckValue: true ");
                            else System.out.println("\tcheckValue: false - pins are not the same ");
                        }
                        else if (m ==1 ) {
                           if (testPrivateMethods(arrLen, opt, 1)) System.out.println("\tvalidateNewPin: true ");
                            else System.out.println("\tvalidateNewPin: false - new pin does not pass check ");
                        }
                    }
                    catch(Exception e){     //anything unexpected is a fail
                        System.out.println("Test failed **********");
                        continue;
                    } 
                }
            }
        }

        //test for on original pin value
        for (int test = 0; test < 2; test++){
            try{
                if (test == 0){         //testing isValidPin on 00
                    if (testOriginalPin(test)) System.out.println("\tcheckValue: true ");
                    else System.out.println("checkValue: false  - pins are not the same value ");
                }
                else{           //tesing validateNewPin on 00
                    if (testOriginalPin(test)) System.out.println("\tvalidateNewPin: true");
                    else System.out.println("\tvalidateNewPin: false - new pin does not pass check");
                }
            }
            catch(Exception e){ //anything unexpected is a fail
                System.out.println("Test failed **********");
                continue;
            }
			
        }
    }
    
}




