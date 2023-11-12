package Permit;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Permit {
    String[] plateNumbers = new String[2];
    String permitNumber;
    String effectiveDate;
    String expirationDate;
    int users;

    public Permit(String licensePlate, String permitNumber, String effectiveDate, String expirationDate) {
        plateNumbers[0] = licensePlate;
        this.permitNumber = permitNumber;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
        users++;
    }

    public void setPlateNumber(String licensePlate) {
        for (String p : plateNumbers) {
            if (p.isBlank()) {
                if (validateLicensePlate(licensePlate)) {
                    p = licensePlate;
                    break;
                }

            }
        }
    }
    public String getPlateNumbers() {
        String plates = "";

        for (String p : plateNumbers) {
            if (p != null) {
                plates = plates + p +"\n";
            }
        }

        return plates;
    }
    public boolean validateLicensePlate(String licensePlate) {
        if (licensePlate.length() >= 6 && licensePlate.length() <= 8) {
            for (char c : licensePlate.toCharArray()) {
                if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }


    public void setPermitNumber(String permitNumber) {
        if (validatePermitNumber(permitNumber)) {
            this.permitNumber = permitNumber;
        }
        else {
            System.out.println("Invalid permit number");
        }

    }
    public String getPermitNumber() {
        return permitNumber;
    }

    public boolean validatePermitNumber(String permitNumber) {
        for (char c : permitNumber.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /*
    public void setEffectiveDate(String effectiveDate) {
      if (validateEffectiveDate((effectiveDate))) {
            this.effectiveDate = effectiveDate;
        }
        else {
            System.out.println("Invalid effective date");
        }
    }

    public boolean validateEffectiveDate(String effectiveDate) {}
    */

    public String getEffectiveDate() {
        return effectiveDate;
    }

    /*
    public void setExpirationDate(String expirationDate) {}

    public void validateExpirationDate(String expirationDate) {}

     */

    public String getExpirationDate() {
        return expirationDate;
    }

    public int getUsers() {
        return users;
    }

    public void addUser(String licensePlate) {
        plateNumbers[1] = licensePlate;
        users++;
    }

    public String printDetails() {
        String details = "";
        details = details + "Plate Number(s): " + getPlateNumbers();
        details = details + "Permit.Permit Number : " + getPermitNumber() + "\n";
        details = details + "Effective Date: " + getEffectiveDate() + "\n";
        details = details + "Expiration Date: " + getExpirationDate() + "\n";

        return details;
    }
}