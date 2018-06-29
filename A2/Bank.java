import java.io.*;
import java.util.*;

//File Name: Bank.java
//Developer: Leila Erbay
//Purpose: UI for creating, navigating around one's bank account(s)
//Input: (Using Scanner) User input determining the course of action
//Output: Updating a customer's bank account(s) 
public class Bank {

    private static final Map<String,Customer> customersMap = new HashMap<String, Customer> ();          //will be used throughout the program
    static long counter = 0;

    //Name: main
    //Developer: Leila Erbay
    //Purpose: Allows user to create bank accounts, displays a menu for the Customer(s)
    //Inputs: String[] args - not used
    //Outputs: ---
    //Side-effects: Print Statements - signaling the user what has been updating 
    //Special Notes: try - catch - finally to check any extra exceptions
    public static void main(String [] args){
        Scanner scanner;
        String nextInput;
        scanner = new Scanner(System.in);
        try{
       
            System.out.println("Welcome to Banking 303. The best way to keep track of your customers and their assets.\n");
            System.out.println("Please select an option from the following menu.");
            menu();
            nextInput = scanner.nextLine();

            //LOOP: continuously ask user for input until valid input is entered
            while (!nextInput.equals("a") && !nextInput.equals("b") && !nextInput.equals("c") && 
                    !nextInput.equals("d") && !nextInput.equals("e") && !nextInput.equals("f") && 
                    !nextInput.equals("g")  ){
                System.out.println("Please select an option from the menu.\n");
                menu();    
                nextInput = scanner.nextLine();
            }
    
          //LOOP: allow user to select an option until they press g -- quit
            while(!nextInput.equals("g")){
                switch(nextInput){
                    case "a":   createCustomer();
                                counter++;
                                break;
                    case "b":   menuOption("createAccount");
                                break;
                    case "c":   menuOption("getBalance");
                                break;
                    case "d":   menuOption("deposit");
                                break;
                    case "e":   menuOption("withdraw");
                                break;
                    case "f":   makeTransfer();
                                break;
                }
                System.out.println("\nSelect another option from the main menu.");
                menu();
                nextInput = scanner.nextLine();
            }
        }
        catch(InputMismatchException e){
            System.out.println("An error occurred while accepting your input");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            System.out.println("\nWe hope you enjoyed your experience with Banking 303. Good bye :)");
            scanner.close();
        }
    }


    //Name: menu
    //Developer: Leila Erbay
    //Purpose: Concise place of the menu options that will be printed
    //Inputs: ---
    //Outputs: ---
    //Side-effects: Print statements of the menu options
    //Special notes: ---
    public static void menu(){
        System.out.println("(a) \t Create a customer");
        System.out.println("(b) \t Create a bank account");
        System.out.println("(c) \t Get Balance");
        System.out.println("(d) \t Deposit");
        System.out.println("(e) \t Withdraw");
        System.out.println("(f) \t Transfer");
        System.out.println("(g) \t Quit");
    }


    //Name: createCustomer
    //Developer: Leila Erbay
    //Purpose: be used by the banker to create a customer
    //Input: ---
    //Output: ---
    //Side-effects: HashMap customerMap, will have added a new customer by the end of the method
    //Special notes: try catch for the scanner in case any incorrect 
    //               the customer id will consist of their name and unique number
    public static void createCustomer(){
        Scanner scanner;
        String name, customerId;
        Customer newCustomer;
        String[] splitStr;
 
        customerId = "";
        scanner = new Scanner(System.in);

        System.out.println("Please enter the name you want your customer to be identified as");
        name = scanner.nextLine();  
         
        splitStr = name.split("\\s+");              //contains the different segments of the name separated by white space
        for (int i =0; i < splitStr.length; i++){   
            customerId += splitStr[i];              //customerId will be concatenated segments of the users name 
        }

        customerId = customerId + counter;          //to ensure customerId is unique, counter will incrememnt and be concatenated to end of the customer ID
        newCustomer = new Customer(name, customerId);
        customersMap.put(customerId, newCustomer);
        System.out.println("\n Your customer ID is:\t" + customerId+ "\n");
    }        


    //Name: menuOption
    //Developer: Leila Erbay
    //Purpose: depending on the input option, method will execute required tasks (create account, get balance, make deposit, make withdrawal)
    //Inputs: String option - selects what task will be executed
    //Outputs: ---
    //Side-effects: if input is "createAccount" a new account will be added to the databases holding a customer's accounts
    //Special notes: ---
    public static void menuOption(String option){
        Customer customer;
        String customerId;
        char accountType;
        int accountNo;
        double currentBalance, depositAmt, withdrawalAmt, balanceInput, percentageInput;

        customerId = getCustomerId();          
        if(customerId.equals("q")) return;  //if customer doesn't have an ID yet, then he can exit to the main menu
         
        customer = customersMap.get(customerId);        //retrieve customer from hashMap
        accountType = getAccountType();
        
        if (option.equals("createAccount")){            //segment for CREATING an ACCOUNT - either savings or checkings
            balanceInput = setAmount("balance");                
            percentageInput = setAmount("discount");
            accountNo = customer.createAccount(accountType, balanceInput, percentageInput);
            System.out.println("\nYour account type is:\t" + accountType + "\nYour account number is:\t" + accountNo);
            System.out.println("Your account balance is:\t" + balanceInput + "\nYour account percentage discount is:\t" + (percentageInput*100)+"%");
            return;
        }
        
        accountNo = getAccountNumber(accountType, customer);
        if (accountNo == -1) return;               //if accountNo = -1 then user wants to exit from current menu and return to main menu

        currentBalance = -1.0;
        switch(option){                   
            case "getBalance":  currentBalance = customer.getBalance(accountType, accountNo);               //fgetting current balance - returns current balance
                                break;
            case "deposit":     depositAmt = setAmount("deposit");
                                currentBalance = customer.makeDeposit(accountType,accountNo, depositAmt );      //depositing money - returns an updated balance
                                break;
            case "withdraw":    withdrawalAmt = setAmount("withdraw");
                                currentBalance = customer.makeWithdrawal(accountType,accountNo, withdrawalAmt);     //withdrawing money - returns an updated balance 
                                break;
        }
        System.out.println("Your account balance is: " + currentBalance);

    }


    //Name: makeTransfer
    //Developer: Leila Erbay
    //Purpose:allow user to make a transfer from one account to another
    //Inputs: ---
    //Outputs: ---
    //Side-effects: the account transferring money will a decrease in their balance 
    //              the account receiving money will have their balance increase
    //Special notes: displays balance of both accounts (transferring and receiving)
    public static void makeTransfer(){
        Customer customer;
        String customerId;
        char fromAccountType, toAccountType;
        int fromAccountNo, toAccountNo;
        double transferAmt;
        double[] currentBalances;

        customerId = getCustomerId();
        if(customerId.equals("q")) return;              //allows user to exit to main menu if customerID doesn't exit 
        customer = customersMap.get(customerId);

        System.out.println("We need the account type of the account you will be transferring from");            
        fromAccountType = getAccountType();                                                             //account type of transferring account

        System.out.println("We need the account number of the account you will be transferring from"); //acount number of transferring account 
        fromAccountNo = getAccountNumber(fromAccountType, customer);                                    
        if (fromAccountNo == -1) return;                            //break out of method if customer wants to quit

        System.out.println("We need the account type of the account you will be transferring to");
        toAccountType = getAccountType();                                                               //account type of receiving account

        System.out.println("We need the account number of will be transferring to");                    //account number of receiving account
        toAccountNo = getAccountNumber(toAccountType, customer);
        if (toAccountNo == -1) return;                              //break out of method if customer wants to quit
        
        transferAmt = setAmount("transfer");
        currentBalances = customer.makeTransfer(fromAccountType, toAccountType, fromAccountNo, toAccountNo, transferAmt);       //sets receiving and transferring balances

        System.out.println("Transfer amount of:\t " + transferAmt );                           
        System.out.println("Your account:\t" + fromAccountType + "\taccount number:\t" + fromAccountNo + "\tand its current balance is:\t" +currentBalances[0]);
        System.out.println("Your account:\t" + toAccountType + "\taccount number:\t" + toAccountNo + "\tand its current balance is:\t" +currentBalances[1]);

    }


    //Name: getCustomerId()
    //Developer: Leila Erbay
    //Purpose: helper method to easily determine if the customer ID exists in the hashMap
    //Inputs: ---
    //Outputs: String - customer's ID
    //Side-effects: Print statements
    //Special notes: allows user to quit if they realize they don't have an ID
    //               uses containsKey of HashMap to determine if customerID exists
    public static String getCustomerId(){
        Scanner sc;
        String customerId;

        customerId = "";
        sc = new Scanner(System.in);

        System.out.println("Please enter your customer ID: ");
        customerId = sc.nextLine();
        
        //get the customer ID or allow user to quit
        while(!customersMap.containsKey(customerId)){
            System.out.println("We can not find that key, please enter your customer Id. If you don't have a customer ID yet, please enter q for quit to go back to the main menu ");
            customerId = sc.nextLine();
            if (customerId.equalsIgnoreCase("q")){
                return "q";
            }
        }
        return customerId;
    }


    //Name: getAccountType
    //Developer: Leila Erbay
    //Purpose: determine the customer's account type - savings or checkings
    //Inputs: ---
    //Outputs: char - customer's account type
    //Side-effects: Print statements
    //Special notes: allows user to enter s,S,savings,Savings,c,C,checkings,Checkings, etc. for the accountType
    public static char getAccountType(){
        Scanner sc;
        String accountType;
        char accountTypeChar;

        accountType = "";
        accountTypeChar = '0';
        sc = new Scanner(System.in);

        System.out.println("Please enter the account type you'd like to access");
        accountType = sc.nextLine();

        //LOOP: wait until user enters s, savings, c, checkings or something of that form. Directs user how to enter after 1 error.
        while(!accountType.equalsIgnoreCase("s") && !accountType.equalsIgnoreCase("c") &&
              !accountType.equalsIgnoreCase("savings") && !accountType.equalsIgnoreCase("checkings")){
            System.out.println("If you want to a savings account, please enter either s or savings.\nIf you want to a checkings account, please enter either  c or checkings");
            accountType = sc.nextLine();  
        }
        if (accountType.equalsIgnoreCase("s") || accountType.equalsIgnoreCase("savings")){          //assigns char to correct type - savings
            accountTypeChar = 's';
        }
        else if (accountType.equalsIgnoreCase("c") || accountType.equalsIgnoreCase("checkings")){   //assigns char to correct type - checkings
            accountTypeChar = 'c';
        }
        return accountTypeChar;
    }


    //Name: getAccountNumber
    //Developer: Leila Erbay
    //Purpose: helper method to easily get the account number for a specific customer
    //Inputs: char accountType, Customer customer
    //Outputs: int - customer's account number
    //Side-effects: call to getBalance of customer class, print statements
    //Special notes: user can enter q to quit or their correct account no. If they enter anything else, they will return to main menu.
    public static int getAccountNumber(char accountType, Customer customer){
        int accountNo;
        accountNo = 0;
        try{
            Scanner sc;
            String nextInput;
            sc = new Scanner(System.in);

            System.out.println("Please enter your account number");
            accountNo = sc.nextInt();

            //LOOP: asks user for account number and checks to see if it is correct
            while(customer.getBalance(accountType, accountNo) == -1.0){
                System.out.println("If you do not have an account of type:\t" + accountType + "\nPress q to go back to the root menu  or enter a valid account number." );
                nextInput= sc.next();
                if (nextInput.equals("q")) {
                    accountNo = -1;
                    return accountNo;
                }
                else accountNo = Integer.parseInt(nextInput);
            }
        }
        catch(Exception e){                     //if anything that is not "q" or a number is entered then they will be taken back to the main menu
            System.out.println("Invalid input " + e.getMessage());
            accountNo = -1;
        }
        finally{
            return accountNo;
        }
    }


    //Name: setAmount
    //Developer: Leila Erbay
    //Purpose: setAmount
    //Inputs: string option
    //Outputs: double - amount 
    //Side-effects: print statements
    //Special notes: uses scanner to read in an amount for the discount percentage, deposit amount,
    //               withdrawal amount, transfer amount, or setting the intial balance
    public static double setAmount(String option){
        Scanner sc;
        double amount;
        amount = -1.0;
        sc = new Scanner(System.in);

        //using scanner, will not set discount until an integer or decimal is entered.
        if (option.equals("discount")) {
            System.out.println("Please enter the percent discount you want to set for your account.");
            amount = sc.nextDouble();
            while(amount < 0.00 || amount > 100.0){                     //condition: discount percentage can not be negative and can not be greater than 100
                System.out.println("Please enter a percentage amount that is a positive value and a values less than 100");
                amount = sc.nextDouble();
            }
            amount = amount /100;
        }

        //using scanner, will not set discount until an integer or decimal is entered.
        else if (option.equals("deposit") || option.equals("withdraw") || option.equals("transfer") || option.equals("balance")) {
            if (option.equals("balance")) {
                System.out.println("Please enter the initial balance you want placed in your account.");
            }
            else {
                System.out.println("Please enter the amount you want to " + option + ".");
            }
            amount = sc.nextDouble();
            while(amount < 0.0){                                       //condition: dollar amount can not negative
                System.out.println("Please enter a balance amount that is a positive value");
                amount = sc.nextDouble();
            }
        }
        return amount;
    }

}