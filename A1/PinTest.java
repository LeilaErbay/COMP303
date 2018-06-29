import java.io.*;
import java.util.*;

//File Name: PinTest.java
//Developers: Leila Erbay
//Purpose: Exhausting tests for each method of the Pin.java file
//Input: None
//Output: Valid , Invalid to determine which values are valid and invalid for the Pin.java methods
public class PinTest {

    //Name: main
    //Developer: Leila Erbay
    //Purpose: display the results of testing each function from the Pin.java file
    //Input: String [] args - not used
    //Output: None
    //Side-effects: 
    //              prints a single statement to notify the developer which function is being tested
    //              prints list of arguments and results from each test accordingly
    //Special Notes: 
    //              testAllFunctions inputs:
    //              1)which function to be tested   2)length of longest pin   3)what kind of pin to be tested

    public static void main(String [] args){
        
       //same tests are used to test Pin constructor and confirmOldPin
        for (int i = 0; i <= 1; i++){
            if (i == 0) {
                System.out.println("Test case for Pin constructor - original pin was 00");
            }
            else{
                System.out.println("Test case for method: confirmOldPin - previous pin was 00");
            }
            testAllFunctions(0, 7, 1);
            testAllFunctions(0, 7, 2);               
            testAllFunctions(0,1, 0);
            System.out.println("");
        }

        //tests used to test setPin 
        System.out.println("Test case for method: setPin - previous pin was 00");
        testAllFunctions(1, 7, 1);
        testAllFunctions(1, 7, 2);
        testAllFunctions(1,1, 0);
        System.out.println("");
        
    }


    //Name: testAllFunctions
    //Developer: Leila Erbay
    //Purpose: based on the parameters, test a specific function from the Pin.java file 
    //      int caseOption - determines which function from Pin.java is tested
    //      int loopLength - determines the length of the for loop (ie how many pin values are to be tested)
    //      int stringOption - determines the value of the string (ie either one randomly generated or the pin 00)
    //Outputs: None
    //Side - effects: print statements telling the user that either the pin entered was a correct or an incorrect pin
    //Special Notes: 
    //          if a result is printed invalid, it signals that the input pin did not pass the function's conditions
    //          if a result is printed valid, it signals that the input pin did pass the function's conditions

    public static void testAllFunctions(int caseOption, int loopLength, int stringOption) {
        Pin testPin;
        String randomPin;
        boolean result;

        result = false;
        testPin = new Pin();

        
        for (int i = 1; i <= loopLength; i++){
            if(stringOption != 0) {                         //randomPin - the pin to be tested
                randomPin = randomStringGenerator(i, stringOption);
            }
            else{
                randomPin = "00";
            }
                switch(caseOption){                    //determines which function from Pin.java is tested
                    case 0: result = testPin.confirmOldPin(randomPin);
                            break;
                    case 1: result = testPin.setPin(randomPin);
                            break;        
                }       
                if (result){        //if attempt pin was successfull - valid
                    System.out.println("Argument: " + randomPin + "     " + "Result: valid   - this is a valid pin.");
                }
                else{               //if attempt pin was unsuccessful - invalid
                    System.out.println("Argument: " + randomPin + "     " + "Result: invalid" );
                }
        }
    }


    //Name: randomStringGenerator
    //Developer: Leila Erbay
    //Purpose: create strings randomly consisting of either of any ascii value or just ascii number values 
    //Input: 
    //      int strLength - desired string length 
    //      int option - option for which kind of array someone would like (either random ascii or only number ascii)
    //Output: string that as a random combination of ascii characters
    //Side-effect: None
    //Special notes: This is a testing tool

    public static String randomStringGenerator (int strLength, int option){
        StringBuilder randomStr;
        char [] charArray;
        int randomIndex;
        Random random; 
        
        charArray = new char [0];
        randomStr = new StringBuilder(); 
        random = new Random();

            //if input = option 1: create an array of all possible ascii values
            if (option == 1){
                charArray = randomCharArray(1);     
            }
            //if input = option 2: create an array of only ascii numbers
            if (option == 2){   
                charArray = randomCharArray(2);     
            }
            for (int i = 0; i < strLength; i++){
                randomIndex = random.nextInt(charArray.length);     
                randomStr.append(charArray[randomIndex]);           
        }

        //string will be made up randomly assembled with any ascii char or only ascii numbers
        return randomStr.toString();
    }


    //Name: randomCharArray
    //Developer: Leila Erbay
    //Purpose: Testing tool - Generate a char array of all possible ascii values or one of just numbers
    //Input:  Int option -  either all possible ascii values or only ascii numbers will fill the output array
    //Output:  char array of either all possible ascii values or char array of ascii numbers
    //Side-effects: None
    //Special notes: This is a testing tool

    public static char[] randomCharArray(int option) {
        char[] possibleChars;
        
        //if option equals 1 then all possible ascii values will fill the output array
        if (option == 1){
            possibleChars = new char [255];
            for (int i =0; i < possibleChars.length; i++) {
                possibleChars[i] = (char) i;
            }
        }
        //if input != 1 then only ascii numbers will fill the output array 
        else{ 
            possibleChars = new char [10];
            for (int j = 0; j < possibleChars.length; j++ ){
                possibleChars[j] = (char) (j+48);
            }
        }
        return possibleChars;
    }

}
