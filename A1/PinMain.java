import java.io.*;
import java.util.Scanner;

//File Name: PinMain.java
//Developers: Leila Erbay
//Purpose: UI for Pin.java - allows user to create a pin, confirm old pin, and set a new pin
//Inputs: User input of old pin and new pin
//Outputs: Strings - telling user what is done incorrectly or correctly regarding his pin

public class PinMain{

    //Name: main
    //Developer: Leila Erbay
    //Purpose: UI for creating a pin, confirming old pin and setting new pin
    //Inputs: String [] args - not used
    //Outputs: None
    //Side-effects: print statements - welcomes user, notifies if entered pin is valid/invalid, if pin is updated 
    //Special Notes: None

    public static void main(String [] args){
        Pin userPin;                    
        Scanner sc;              
        String userInput;
        
        userPin = new Pin();                //initialized userPin to 00
        sc = new Scanner(System.in);         //reads in user input - pin values

        System.out.println("welcome to pin update!");

        //continuously ask user for his old pin until the user enters his old pin correctly
        do {
            System.out.print("Please input your old pin:  ");
            userInput = sc.nextLine();
            if (!userPin.confirmOldPin(userInput)){
                System.out.println("That is not your old pin.");
            }
        } while(!userPin.confirmOldPin(userInput));
        System.out.println("Old pin confirmed.");


        //continuously ask user for a new pin until the user enters a new pin that is valid and updates the pin
        do{
            System.out.print("Please input your new pin:  ");
            userInput = sc.nextLine();
            if (!userPin.setPin(userInput)){
                System.out.println("Invalid pin");
            }
            else{                                              //new pin is set if entered pin passes conditions
                System.out.println("New pin confirmed.");
                System.out.println("Your pin has been updated to  "+ userInput + ".");
                break;
            }
        } while(!userPin.setPin(userInput));
  
        sc.close();   
        
    }
}