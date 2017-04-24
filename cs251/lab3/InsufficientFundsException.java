/**
 * @author Linh Tran
 * CS251.004 
 * February 20, 2014
 * This InsufficientFundsException class extends Exception for
 * the use in BankAccount class to throw exeception. Class contains
 * a constructor that takes in the amount that the user over withdrawed 
 * called "shortFall" and a method to get the "shortFall".
 */

public class InsufficientFundsException extends Exception {

    private double shortFall = 0;

    /**
    * The is a InsufficientFundsException constructor that takes the double 
    * shortFall and stores it into the member variable shortFall of this
    * InufficientFundsException class.
    * @param takes in an double shortFall
    */
	public InsufficientFundsException (double shortFall){

        this.shortFall = shortFall;
	}

	/**
    * This method gets the shortFall amount 
    * @return returns double currentBalance.
    */
    public double getAmount (){
        return shortFall;
    }
}