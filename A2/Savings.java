//File Name: Savings
//Developer: Leila Erbay
//Purpose: child of BankAccount
//Inputs: depends on method
//Outputs: depends on method
//Side-effects: ---
//Special notes: Savings uses getBalance, transfer from Super 
public class Savings extends BankAccount{
    

    //Name: Savings
    //Developer: Leila Erbay
    //Purpose: instantiation of a Savings account
    //Inputs: int ownerId, double balance, char accountType
    //Outputs: ---
    //Side-effects: ---
    public Savings(String ownerId, double balance,  double discountPercent) throws InvalidAmountException{
            super(ownerId, balance, 's', discountPercent);
    }

    //Name: makeDeposit
    //Developer: Leila Erbay
    //Purpose: allow customer to make a deposit into an account
    //Inputs: double despositAmt, double discountPercent
    //Outputs: ---
    //Side-effects: balance is updated by desposit
    public double makeDepositSavings(double depositAmt){
        double award;
        award = (1+ this.discountPercent);
        this.balance = makeDeposit(depositAmt + award);
        return this.balance;
    }

    //Name: makeWithdrawal
    //Developer: Leila Erbay
    //Purpose: allow customer to withdraw an amount
    //Inputs: double withdrawalAmt, double discountPercent
    //Outputs: balance
    //Side-effects: ---
    public double makeWithdrawalSavings(double withdrawalAmt){
        if(withdrawalAmt < 1000.0) {
            System.out.println("Withdrawals must be greater than 1000");
        }
        else {
            this.balance = makeWithdrawal(withdrawalAmt);
        }
        return this.balance;
    
    }
}

    