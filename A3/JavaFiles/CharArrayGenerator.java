import java.io.*;
import java.util.*;

/**
 * <h1> CharArrayGenerator </h1>
 * File name: CharArrayGenerator.java
 * @author Leila Erbay
 * <p> Purpose: allow user create character arrays randomly ordered with any character or just with Ascii numbers </p> 
 * <p> Side-effects: --- </p> 
 * <p> Special notes: two choices of character: random characters or random ascii numbers </p> 
 */
public class CharArrayGenerator{
   
    
    /**
     * @author Leila Erbay
     * <p> Purpose: Create char arrays either of any ascii value or just number values </p> 
     * @param strLength chooses the length of the char array to be produced
     * @param option  chooses what the contents of the char array will be
     * @return char[]  array of random ascii characters
     * <p> Side-effects: --- </p> 
     * <p> Special Notes: -- </p> 
     */
    public static char[] randomCharArrayGenerator (int strLength, int option){
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

       return randomStr.toString().toCharArray();       //returns a char array
         
    }

    /**
     * @author Leila Erbay
     * <p> Purpose: Generate a char array of all possible ascii values or one of just numbers </p> 
     * @param option  decides what possible selection of characters will be placed into the array
     * @return char[]  either an array of all possible ascii chars or only ascii numbers
     * <p> Side-effects: --- </p> 
     * <p> Special notes: --- </p> 
     */
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