import java.util.*;
import java.io.*;

//File name: Customer.java
//Developer: Leila Erbay
//Purpose: contains properties of a customer 
//Inputs: ---
//Outputs: ---
//Side-effects: private ArrayLists savings, checkings are global and may be updated with following methods
public class Customer {
    private String name;
    private String customerId;
    private ArrayList<Savings> savings = new ArrayList <Savings>();
    private ArrayList<Checkings> checkings = new ArrayList <Checkings>(); 
    private int sAcctNo;
    private int cAcctNo;


    //Name: Customer
    //Developer: Leila Erbay
    //Purpose: constructor for Customer object
    //Inputs: String name, String customerId
    //Outputs: ---
    //Side-effects: sets global vars name, customerId to appropriate values
    //Special notes:---
    public Customer(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }


    //Name: createAccount 
    //Developer: Leila Erbay
    //Purpose: Allow customer to set an account based on account type
    //Inputs: char accountType, double balance, double discountPercent
    //Outputs: int account number
    //Side-effects: global counters sAcctNo (savings account IDs) and cAcctNo (checkings accounts IDs) 
    //              will incrememnt every time a respective account is made
    //Special notes: ---
    public int createAccount(char accountType, double balance, double discountPercent)  { 
        int returnAcctNo = 0;
        try{
            if (accountType == 's') {                               //if savings account is made, counter for savings accounts will incrememnt
                Savings s = new Savings(customerId, balance, discountPercent);
                savings.add(sAcctNo, s);
                returnAcctNo = sAcctNo;
                sAcctNo++;

            }
            else if (accountType == 'c'){                           //if checkings account is made, counter for checkings accounts will increment
                Checkings c = new Checkings(customerId, balance, discountPercent);
                checkings.add(cAcctNo,c);
                returnAcctNo = cAcctNo;
                cAcctNo++;
            }
        }
        catch(Exception e){
            System.out.println("Error occurred when trying to create an account.");
        }
        return returnAcctNo;
    }    

    //Name:getBalance
    //Developer: Leila Erbay
    //Purpose: allow customer to get a balance from a certain account
    //Inputs:   char accountType, int accountNo
    //Outputs: double returnBalance - current balance
    //Side-effects: ---
    //Special notes: calls actionForAccounts to actually determine the return balance
    //              third input in method call: 0 is the option for getting the current balance
    public double getBalance(char accountType, int accountNo){
        double returnBalance = -1.0;
        returnBalance = actionForAccounts(accountType, accountNo, 0, 0.0);
        return returnBalance;
    }
    
    //Name: makeDeposit
    //Developer: Leila Erbay
    //Purpose: allow customer to make a deposit in one of their accounts
    //Inputs: char accountType, int accountNo, double depositAmt
    //Outputs: double returnBalance - updated balance
    //Side-effects: ---
    //Special notes: calls actionForAccounts to actually determine the return balance
    //              third input in method call: 1 is the option for depositing money into an account
    public double makeDeposit(char accountType, int accountNo, double depositAmt){
        double returnBalance = -1.0;
        returnBalance = actionForAccounts(accountType, accountNo, 1, depositAmt);
        return returnBalance;
     
    }
    
    //Name: makeWithdrawal
    //Developer: Leila Erbay
    //Purpose: allow customer to withdraw from an account
    //Inputs: char accountType, int accountNo, double withdrawalAmt
    //Outputs: oduble returnBalance - updated balance
    //Side-effects:
    //Special notes:calls actionForAccounts to actually determine the return balance
    //              third input in method call: 2 is the option for withdrawing money from an account
    public double makeWithdrawal(char accountType, int accountNo, double withdrawalAmt){
        double returnBalance = -1.0;
        returnBalance = actionForAccounts(accountType, accountNo, 2, withdrawalAmt);
        return returnBalance;
    }
    
    //Name: actionForAccounts
    //Developer: Leila Erbay
    //Purpose: condense all the actions for an account to return the updated balance - getting balance, making a deposit, making a withdrawal
    //Inputs: char accountType, int accountNo, int option, double amount
    //Outputs: double  returnBalance - current or updated balance
    //Side-effects: depening on accountType, either a checkings or savings account will be altered if the customer wants to deposit or withdraw money
    //              exceptions are thrown if accountTypes are invalid, if an account doesn't exist
    //Special notes: ---
    private double actionForAccounts(char accountType, int accountNo, int option, double amount){
        double returnBalance = -1.0;
        try{
        if (accountType == 's') {                   //if accountType = savings then determine action based on option
            Savings s = savings.get(accountNo);
            switch (option){
                case 0: returnBalance = s.getBalance();
                        break;
                case 1: returnBalance = s.makeDepositSavings(amount);
                        break;
                case 2: returnBalance = s.makeWithdrawalSavings(amount);
                        break;
            }
        }
        else if (accountType == 'c') {              //if accountType = checkings then determine action based on option
            Checkings c = checkings.get(accountNo);
            switch (option){
                case 0: returnBalance = c.getBalance();
                        break;
                case 1: returnBalance = c.makeDeposit(amount);
                        break;
                case 2: returnBalance = c.makeWithdrawalCheckings(amount);
                        break;
            }
        }
        else {
            throw new InvalidInputException ("Account type must be either savings or checkings");
        }
        }catch (IndexOutOfBoundsException e){
            System.out.println("You are trying to access an account of type: " + accountType + "\nThe account you are trying to access \t " + accountNo + "\tdoes not exist");
        }
        catch(InvalidInputException e){
            System.out.println("Error occurred from account type.");
        }
        catch(Exception e){
            System.out.println("An error occurred: \t" + e.getMessage() );
        }
        finally {
            return returnBalance;
        }
    }

   
    //Name: makeTransfer
    //Developer: Leila Erbay
    //Purpose: allow customer to transfer between two bank accounts
    //Inputs: char fromAcctType, char toAcctType, int fromAcctNo, int toAcctNo, double transferAmt
    //Outputs: double returnBalances[] - updated Balance of account that has transferred money out and account that is receiving money
    //Side-effects: exceptions are caught if accounts do not exist
    //Special notes: call to makeTransferHelper to compare the from and to accounts
    public double[] makeTransfer(char fromAcctType, char toAcctType, int fromAcctNo, int toAcctNo, double transferAmt){
        double returnBalances[] = new double[2];
        try{
            if (makeTransferHelper(fromAcctType, toAcctType) == 0 ){        //fromAcct = s; toAcct = s
                Savings fromAcct = savings.get(fromAcctNo);
                Savings toAcct = savings.get(toAcctNo);
                returnBalances = fromAcct.makeTransfer(toAcct, transferAmt);
            }
            else if (makeTransferHelper(fromAcctType, toAcctType) == 1 ){       //fromAcct = s; toAcct = c
                Savings fromAcct = savings.get(fromAcctNo);
                Checkings toAcct = checkings.get(toAcctNo);
                returnBalances = fromAcct.makeTransfer(toAcct, transferAmt);
            }
            else if (makeTransferHelper(fromAcctType, toAcctType) == 2 ){       //fromAcct = c; toAcct = s
                Checkings fromAcct = checkings.get(fromAcctNo);
                Savings toAcct = savings.get(toAcctNo);
                returnBalances = fromAcct.makeTransfer(toAcct, transferAmt);
            }
            else if (makeTransferHelper(fromAcctType, toAcctType) == 3 ){       //fromAcct = c; toAcct = c
                Checkings fromAcct = checkings.get(fromAcctNo);
                Checkings toAcct = checkings.get(toAcctNo);
                returnBalances = fromAcct.makeTransfer(toAcct, transferAmt);
            }
        }catch (IndexOutOfBoundsException exc) {
            System.out.println("Initial account or receiving account may not exist. Make sure you have valid account numbers.");
        }catch (InvalidInputException e) {
            System.out.println("Error occurrred trying to access an account");
        }
        finally{
            return returnBalances;
        }
    }


    //Name: makeTransferHelper
    //Developer: Leila Erbay
    //Purpose: make the comparisons between two accounts clear and return a signal from each different comparison
    //Inputs: char fromAcctType, char toAcctType
    //Outputs:int signal from the comparisons
    //Side-effects: ---
    //Special notes: ---
    public int makeTransferHelper (char fromAcctType, char toAcctType) throws InvalidInputException{
        if (fromAcctType == 's' && toAcctType == 's') return 0;
        else if (fromAcctType == 's' && toAcctType == 'c') return 1;
        else if (fromAcctType == 'c' && toAcctType == 's') return 2;
        else if (fromAcctType == 'c' && toAcctType == 'c') return 3;
        else throw new InvalidInputException("Account you're transferring from must be either savings or checkings. \n Account you're tranfserring to must either be savings or checkings.");
    }
}


//Name: InvaliInputException
//Developer: Leila Erbay
//Purpose: exception if user enters invalid owner  ID
//Inputs: String msg - the error message sent to the user
//Outputs: ---
//Side-effects: print statement of error message
class InvalidInputException extends Exception{
    InvalidInputException(String msg){
        System.out.println(msg);
    }

}
