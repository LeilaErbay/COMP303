import java.io.*;
import java.util.*;

public class StringGenerator{
   
    
    //Name: randomStringGenerator
    //Developer: Leila Erbay
    //Purpose: Create char arrays either of any ascii value or just number values
    //Input: desired string length; option for which kind of array someone would like
    //Output: string that as a random set of ascii characters
    //Side-effect: None
    public static String randomStringGenerator (int strLength, int option){
        StringBuilder randomStr;
        char [] charArray, numberArray;
        int randomIndex;
        Random random; 
        
        randomStr = new StringBuilder(); 
        random = new Random();
        
        if(option == 1){                                //if input = option 1: create a general string
            charArray = randomCharArray(1);             //create array for all Ascii values
            for (int i = 0; i < strLength; i++) {
                randomIndex = random.nextInt(charArray.length);
                randomStr.append(charArray[randomIndex]);  
            }
        }
        
        if (option == 2 ){                              //if input = option 2: create a general number string
            numberArray = randomCharArray(2);           //create array for numbers only
            for (int i = 0; i < strLength; i++) {
                randomIndex = random.nextInt(numberArray.length);
                randomStr.append(numberArray[randomIndex]);
            }
        }

       return randomStr.toString();
         
    }

    //Name: randomCharArray
    //Developer: Leila Erbay
    //Purpose: Generate a char array of all possible ascii values or one of just numbers
    //Input: Int option - option 1: all possible ascii values, any other value creates a char array of numbers
    //Output: char array of either all possible ascii values or char array of ascii numbers
    //Side-effects: None
    public static char[] randomCharArray(int option) {
        char[] possibleChars;
        
        if (option == 1){
            possibleChars = new char [255];
            for (int i =0; i < possibleChars.length; i++) {
                possibleChars[i] = (char) i;
            }
        }
        else{ 
            possibleChars = new char [10];
            for (int j = 0; j < possibleChars.length; j++ ){
                possibleChars[j] = (char) (j+48);
            }
        }

        return possibleChars;
    }

}