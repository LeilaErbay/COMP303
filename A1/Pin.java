//File name: Pin.java
//Developer(s): Leila Erbay
//Purpose:  Initialize new pin; 
//          determine if user enters its correct old pin;
//          update old pin with new pin entered by the user;
//Inputs: None
//Outputs: booleans determined based on if the user inputs his old pin correctly and validating his new pin

public class Pin{
    
    //userPin - global variable that will be altered in following functions
    private String userPin;
  
    //Name: Pin - constructor
    //Developer: Leila Erbay
    //Purpose: each time new pin is created, intial pin is set to 00
    //Inputs: None
    //Outputs: sets user's initial pin to 00 (global variable - userPin - is altered)
    //Side - effects: change of global var userPin
    //Special Notes: None

    public Pin() {
        this.userPin = "00";
    }


    //Name: setPin
    //Developer: Leila Erbay
    //Purpose: set user pin to the new pin entered by user
    //Inputs: String newPin - user's new pin
    //Output: Boolean - signals that the user's pin has been updated
    //Side - effect: update global variable userPin to newPin (newest pin entered by user)
    //Special Notes: uses private method validateNewPin to ensure that the pin entered by the user meets necessary conditions
    
    public boolean setPin(String newPin){
        if (validateNewPin(newPin)){
            this.userPin = newPin;
            return true;
        }
        else{
            return false;
        }
    }


    //Name: confirmOldPin
    //Developer: Leila Erbay
    //Purpose: compare userPin to the pin entered by the user to confirm the user's previous pin
    //Input: String newPin - string that will be compared to userPin
    //Output: boolean  - result of comparing userPin to input value
    //Side-effects: None
    //Special Notes: None
    
    public boolean confirmOldPin(String newPin ){
       if (!(newPin.equals(this.userPin))) {
           return false;
       }
       else{  
            return true;
       }
    }
    
    
    //Name: validateNewPin
    //Developer: Leila Erbay
    //Purpose: Check user enters a valid new pin and update the user's pin once valid pin is determined
    //Input: String newPin - user's attempt of an old 
    //Output: boolean newPinConfirmed - determining if the new pin is valid
    //Side - effect: none
    //Special Notes: this private method is used in setPin function to make sure user input meets conditions of a valid pin
    
    private boolean validateNewPin(String newPin){
        boolean newPinConfirmed;
        int newPinLen, newPinValue, oldPinValue;
     
        newPinLen = newPin.length();
        newPinConfirmed = false;

        //check if new pin entered by user is valid based on length
        if (newPinLen < 2 || newPinLen > 2) {  
            return newPinConfirmed;       
        }

        //check if pin entered by user is a number and not a character
        for (int i = 0; i < newPinLen; i++){
           if (newPin.charAt(i) < 48 || newPin.charAt(i) > 57){    
                return newPinConfirmed;  
            }
        }

        newPinValue = Integer.parseInt(newPin);
        oldPinValue = Integer.parseInt(this.userPin);

        //check that each digit of the new pin is not the same as the old pin
        if (newPinValue == oldPinValue){
            return newPinConfirmed;
        }        
        //new pin passed every test thus newPinConfirmed set to true
        else{
            newPinConfirmed = true;
        }
        return newPinConfirmed;
    }   
}