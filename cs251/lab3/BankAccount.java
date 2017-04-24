/**
 * @author Linh Tran
 * CS251.004 
 * February 20, 2014
 * BankAccount class allow money to be deposited and withdrawed from 
 * a bank account. The class identifies a bank account from another 
 * by the accountNumber that is passed into the constructor. The 
 * class complains when user over withdraw money by throwing a 
 * InsufficientFundsException. 
 */

public class BankAccount  {
 
	private int accountNumber = 0;
	private double currentBalance=0;

    /**
    * The is a BankAccount constructor that takes a accountNumber 
    * and stores it into the member variable accountNumber of this
    * BankAccount class.
    * @param takes in an int accountNumber
    */
	public BankAccount (int accountNumber){
     
        this.accountNumber = accountNumber;
	}

    /**
    * This method gets the accountNumber pass into BankAccount
    * @return returns int accountNumber.
    */
    public int getAccountNumber (){

    	return accountNumber;
    }

    /**
    * This method gets the current bank account balance 
    * @return returns double currentBalance.
    */
    public double getBalance (){

    	return currentBalance;

    }

    /**
    * This method adds deposit to the current balance of
    * the bank account if the customer deposits a negative
    * amount then the currentBalance won't change and will
    * print a message saying that user cannot deposit negative
    * amounts into his account.
    * @param takes a double depositAmount.
    */
    public void deposit ( double depositAmount){
        if ( depositAmount <0){
            System.out.println ("You cannot deposit" + depositAmount +
            "a negative amount of money into your account." +"\n"+
            "Please try again.");
        }

        else {currentBalance += depositAmount;
        }
    }


    /**
    * This method substracts money from the current balance of
    * the bank account. If the customer widthdraws a negative
    * amount then the currentBalance won't change and will
    * print a message saying that user cannot withdraw negative
    * amounts into his account. Also if user over withdraw money it
    * will throw a InsufficientFunds Exception and will leave the currentBalance
    * unchanged.
    * @param takes a double widthdraw.
    */
    public void withdraw ( double withdraw ) throws InsufficientFundsException{
        
        if ( withdrawAmount <0){
            System.out.println ("You cannot withdraw" + withdrawAmount +
            "a negative amount of money from your account." +"\n"+
            "Please try again.");
        }
        currentBalance -= withdrawAmount;

    	if (currentBalance < 0){
            currentBalance += withdrawAmount;
            overWithdrawCharge (double currentBalance);
    		throw new InsufficientFundsException (currentBalance - withdrawAmount);           	
        }       
    }
}