//File Name: Checkings
//Developer: Leila Erbay
//Purpose: allow customer account
//Inputs: depends on method
//Outputs: depends on method
//Side-effects: ---
//Special notes: Checkings directly uses getBalance, makeDeposit, transfer  from Super
public class Checkings extends BankAccount {

    //Name: Checkings constructor
    //Developer: Leila Erbay
    //Purpose: instantiation of a Checkigns account
    //Inputs: int ownerId, double balance, char accountType
    //Outputs: ---
    //Side-effects: calls super constructor
    public Checkings( String ownerId, double balance, double discountPercent) throws InvalidAmountException{
        super(ownerId, balance, 'c', discountPercent);
        
    }

    //Name: makeWithdrawal
    //Developer: Leila Erbay
    //Purpose: allow customer to make withdawal charges an amount
    //Inputs: double withdrawalAmt, double discountPercent
    //Outputs: this.balance - updated or not updated
    //side-effects: balance is updated if withdrawal amount 
    public double makeWithdrawalCheckings(double withdrawalAmt){
        double deduction;

        deduction =  1 - this.discountPercent;
        withdrawalAmt -=  deduction;
        this.balance = makeWithdrawal(withdrawalAmt);
        return this.balance;
    }

    
}