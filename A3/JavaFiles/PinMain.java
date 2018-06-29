import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <h1> PinMain </h1>
 * File name: PinMain.java
 * @author Leila Erbya
 * <p> Purpose: UI for Pin.java - allows user to create a pin, confirm old pin, and set a new pin </p> 
 * <p> Side-effects: print statements </p> 
 * <p>           exceptions caught </p> 
 * <p> Special notes: Exceptions thrown by Pin.java will be caught by PinMain.java </p> 
 */
public class PinMain{

    /**
     * @author Leila Erbay
     * <p> Purpose: UI for creating a pin, confirming old pin and setting new pin </p> 
     * @param args  not used by program
     * <p> Side-effects: print statements - welcomes user, notifies if entered pin is valid/invalid, if pin is updated </p> 
     * <p> Special notes: try-catch-finally used for exceptions </p> 
     */
    public static void main(String [] args){
        Pin userPin = new Pin();                //initialized userPin to 00            
        Scanner sc = new Scanner(System.in);    //reads in user input - pin values          
        String userInput;
        char [] enteredPin;

        System.out.println("welcome to pin update!");
            
        //continuously ask user for his old pin until the user enters his old pin correctly
        boolean flag = false;
        do {
            try{
                System.out.print("Please input your old pin:  ");       
                userInput = sc.nextLine();
                enteredPin = userInput.toCharArray();
                        
                if (!userPin.confirmOldPin(enteredPin)){
                    System.out.println("That is not your old pin.");
                }    
                else flag = true;
            }
            catch(Exception e){
                System.out.println("Error occurred trying to confirm your oldPin");
                continue;
            }
        } while(!flag);
        
        System.out.println("Old pin confirmed.\n");

        //continuously ask user for a new pin until the user enters a new pin that is valid and updates the pin
        flag = false;
        do{
            try{
                System.out.print("Please input your new pin:  ");
                userInput = sc.nextLine();
                enteredPin = userInput.toCharArray();
                
                userPin.setNewPin(enteredPin);
                
                System.out.println("\nNew pin confirmed.");
                System.out.println("Your pin has been updated to  "+ userInput + ".");
                flag = true; 
            }
            catch(Exception e){
                System.out.println("Error occurred trying to set your new pin:\t" + e.getMessage());
                continue;
            }
        } while(!flag);
    
        sc.close(); 

        
        System.out.println("\nThank you and goodbye");
            
    }
}