package AccountMngmt;

import java.util.Scanner;

// UC05 - Add Permit
// Ayobami Adereti

public class AddPermit {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Get permit info
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

        AccountController controller = new AccountController(user);
        System.out.println(controller.addPermit(permitNumber, licensePlate, effectiveDate, expirationDate).print());
    }
}
