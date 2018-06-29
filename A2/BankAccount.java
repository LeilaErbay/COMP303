//File Name: BankAccount
//Developer: Leila Erbay
//Purpose: Contains methods for either savings or checkings account
//Inputs: various depending on method
//Ouputs: various depending on method
//Side-effects:
public class BankAccount{
    protected double balance;
    private String ownerId;
    private char accountType;
    protected double discountPercent;


    //Name: BankAccount
    //Developer: Leila Erbay
    //Purpose: Sets the ownerId, accountType, and balance for a certain account
    //Inputs: int ownerId, double balance, char accountType 
    //Outputs: ---
    //Side-effects: Exceptions are thrown if an invalid balance or ownerId is inserted
    public BankAccount(String ownerId, double balance, char accountType, double discountPercent) throws InvalidAmountException {
        if (balance < 0.0) throw new InvalidAmountException ("Balance can not be less than 0.00");
        if (discountPercent < 0.00) throw new InvalidAmountException("Discount percent can not be less than 0.00");
        if (1.00 < discountPercent) throw new InvalidAmountException("Discount percent can not be greater than 1.00");

        this.ownerId = ownerId;
        this.accountType = accountType;
        this.balance = balance;
        this.discountPercent = discountPercent;
    }

    //Name: getBalance
    //Developer: Leila Erbay
    //Purpose: allow owner to see his balance
    //Inputs: ---
    //Outputs: double representing the current balance
    //Side-effects: ---
    public double getBalance(){
        return this.balance;
    }

    //Name: makeDeposit
    //Developer: Leila Erbay
    //Purpose: allow user to make a deposit into one of his bank account
    //Input: deposit amount
    //Output: current balance
    //Side-effects: throws exceptions if deposit amount is less than 0
    public double makeDeposit(double depositAmt) {
        if (depositAmt < 0.0) {
            System.out.println("Deposit can't not be less than 0");
        }
        else {
            this.balance += depositAmt;
        }
        return this.balance;
    }

    //Name: makeTransfer
    //Developer: Leila Erbay
    //Purpose: Allow user to transfer between two accounts
    //Input: BankAccount receiver, double transferAmount
    //Output: double balances [] - balances of receiving and transferring account
    //Side-effects: exceptions are thrown for invalid owner ids and transfer amounts
    public double[] makeTransfer(BankAccount receiver, double transferAmt) {
        double [] balances = new double[2];
        if(this.ownerId != receiver.ownerId) {
            System.out.println("Your current owner ID and receiver account owner ID do not match");
        }
        else if(transferAmt < 0.0) {
            System.out.println("Transfer amount must be a positive value.");
        }
        else if(this.balance < transferAmt) {
            System.out.println("Transfer amount must be less than your current balance");
        }
       else{
            receiver.balance += transferAmt;
            this.balance -= transferAmt;
       }

        balances[0] = this.balance;
        balances[1] = receiver.balance;    
        return balances.clone();
    }
    

    //Name: makeWithdrawal
    //Developer: Leila Erbay
    //Purpose: allow customer to withdraw an amount
    //Inputs: double withdrawalAmt
    //Outputs: balance
    //Side-effects: ---
    public double makeWithdrawal(double withdrawalAmt){
        if (withdrawalAmt < 0) {
            System.out.println("Withdrawal amount must be a positive value");
        }
        else if (this.balance < withdrawalAmt) {
            System.out.println("Withdrawal amount must not be greater than your balance");
        }
        else {
            this.balance -= withdrawalAmt;
        }
        return this.balance;
    }
}   



//Name: InvalidAmountException
//Developer: Leila Erbay
//Purpose: exception if the amount entered by the user is illogical
//Inputs: String msg - error message for the user
//Outputs: ---
//Side-effects: Print statement of error message
class InvalidAmountException extends Exception {
    InvalidAmountException(String msg){
        System.out.println(msg);
    }
}




