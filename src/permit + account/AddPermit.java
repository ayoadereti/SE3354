package AccountMngmt;

import java.util.Scanner;

// UC05 - Add Permit
// Ayobami Adereti

public class AddPermit {
    public static void main(String[] args) {
        boolean repeat = true;
        Scanner scnr = new Scanner(System.in);

        while (repeat) {
            // Get permit info from user input 
            System.out.println("License Plate: ");
            String licensePlate = scnr.next();
            System.out.println();

            System.out.println("Permit Number: ");
            String permitNumber = scnr.next();
            System.out.println();

            System.out.println("Effective Date: ");
            String effectiveDate = scnr.next();
            System.out.println();

            System.out.println("Expiration Date: ");
            String expirationDate = scnr.next();
            System.out.println();

            System.out.println("User: ");
            String user = scnr.next();
            System.out.println();

            // Initialize an AccountController obj and call the addPermit method 
            AccountController controller = new AccountController(user);
            String message = controller.addPermit(permitNumber, licensePlate, effectiveDate, expirationDate).print();
            System.out.println("\n" + message);

            // Prompt user to try again if permit info contains invalid input
            if (!message.contains("Invalid") && !message.contains("already")) {
                repeat = false;
            }
        }
    }
}
