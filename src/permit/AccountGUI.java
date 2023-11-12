package Permit;

import java.util.*;

public class AccountGUI {
    public static void main(String[] args) {
        AccountController controller = new AccountController(new User("Test", "Permit.User"));
        Scanner scnr = new Scanner(System.in);

        // Get permit info and store in a string array
        System.out.println("License Plate: ");
        String licensePlate = scnr.next();
        System.out.println();

        System.out.println("Permit.Permit Number: ");
        String permitNumber = scnr.next();
        System.out.println();

        System.out.println("Effective Date: ");
        String effectiveDate = scnr.next();
        System.out.println();

        System.out.println("Expiration Date: ");
        String expirationDate = scnr.next();
        System.out.println();

        String[] permitInfo = {licensePlate, permitNumber, effectiveDate, expirationDate};

        // Initiate addPermit request
        Msg attempt = controller.addPermit(permitInfo);
        System.out.println(attempt.print());
    }

}
