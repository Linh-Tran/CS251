/**
 * This file tests your BankAccount implementation to make sure it
 * throws an exception when it should.
 */
public class BankingTest {
    /**
     * Print account number and balance for a given bank account.
     * @param b The account of interest. 
     */
    private static void printBalance(BankAccount b) {
        System.out.println("Account " + b.getAccountNumber() + " balance = $" + b.getBalance());
    }

    /**
     * Takes the initial deposit amount from command line and plays with a bank account.
     */
    public static void main(String[] args) {

        double depositAmount = 50.00;
        if(args.length > 0) {
            try {
                depositAmount = Double.parseDouble(args[0]);
            } catch (NumberFormatException ex) {
                // argument wasn't valid number, so stick with original value.
            }
        }

        BankAccount b1 = new BankAccount(101);
        
        // Put some money in there!
        b1.deposit(depositAmount);
        printBalance(b1);

        // Make a second account
        double testAmount = 100;
        BankAccount b2 = new BankAccount(42);
        b2.deposit(testAmount);

        try {
            // Late night informercial is selling widgets for 3 easy
            // payments of $19.95, what a deal! Let's take out money to buy one.
            double amount = 19.95;
            for(int i = 1; i<=3; ++i) {
                System.out.println("Withdraw payment " + i + " of $" + amount);
                b1.withdraw(amount);
                printBalance(b1);
            }
            System.out.println("Finished payments");
            printBalance(b1);
        } catch (InsufficientFundsException ex) {
            System.out.println("Sorry, but you are short $" + ex.getAmount());
            ex.printStackTrace();
        } finally {
            System.out.println("Done banking for now");
            printBalance(b1);
        }

        // Make sure messing with b1 didn't change b2 balance.
        // This shouldn't happen if you implemented BankAccount properly.
        if(b2.getBalance() != testAmount) {
            System.out.println("Unexpected change in second account!");
            printBalance(b2);
        }
    }
}
            