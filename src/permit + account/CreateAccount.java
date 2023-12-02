package AccountMngmt;

import java.util.Scanner;

// UC01 - Create Account
// Ayobami Adereti

public class CreateAccount {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("First Name: ");
        String firstName = scnr.next();

        System.out.println("Last Name: ");
        String lastName = scnr.next();

        System.out.println("UTD email (username): ");
        String email = scnr.next();

        System.out.println("Password: ");
        String password = scnr.next();

        AccountController controller = new AccountController();
        System.out.println(controller.createAccount(firstName, lastName, email, password).print());

    }
}
