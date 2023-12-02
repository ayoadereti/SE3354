package AccountMngmt;

import java.util.Scanner;

// UC01 - Create Account
// Ayobami Adereti

public class CreateAccount {
    public static void main(String[] args) {
        boolean repeat = true;
        Scanner scnr = new Scanner(System.in);

        while (repeat) {
            // Get account info from user input
            System.out.println("First Name: ");
            String firstName = scnr.next();

            System.out.println("Last Name: ");
            String lastName = scnr.next();

            System.out.println("UTD email (username): ");
            String email = scnr.next();

            System.out.println("Password: ");
            String password = scnr.next();

            // Initialize an Account Controller obj and call the createAccount method
            AccountController controller = new AccountController();
            String message = controller.createAccount(firstName, lastName, email, password).print();
            System.out.println("\n" + message);

            // Prompt user to try again if account info is invalid 
            if (!message.contains("Invalid")) {
                repeat = false;
            }
        }
    }
}
