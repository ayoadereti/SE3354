package AccountMngmt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.lang.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Permit {
    @Id
    @Column
    private String permitNumber;

    @Column
    private String plateNumber1;

    @Column
    private String plateNumber2;

    @Column
    private String effectiveDate;

    @Column
    private String expirationDate;

    @Column
    private int users = 0;


    public Permit() {
    }

    public Permit(String permitNumber, String licensePlate, String effectiveDate, String expirationDate) {
        setPermitNumber(permitNumber);
        setPlateNumber(licensePlate);
        setEffectiveDate(effectiveDate, expirationDate);
        setExpirationDate(expirationDate);
    }

    public void clearPermit() {
        permitNumber = null;
        plateNumber1 = null;
        plateNumber2 = null;
        effectiveDate = null;
        expirationDate = null;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber) {
        if (validatePermitNumber(permitNumber)) {
            this.permitNumber = permitNumber;
        }
    }

    public boolean validatePermitNumber(String permitNumber) {
        if (permitNumber.length() == 10) {
            for (char c : permitNumber.toCharArray()) {
                if (!Character.isLetterOrDigit(c)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String getPlateNumbers() {
        String plates = "";

        if (plateNumber1 != null) {
                plates = plates + plateNumber1 +"\n";
        }
        if (plateNumber2 != null) {
            plates = plates + plateNumber2 +"\n";
        }

        return plates;
    }

    public void setPlateNumber(String licensePlate) {
        if (plateNumber1 == null) {
            if (validateLicensePlate(licensePlate)) {
                plateNumber1 = licensePlate;
            }
        }
        else if (plateNumber2 == null) {
            if (validateLicensePlate(licensePlate)) {
                plateNumber2 = licensePlate;
            }
        }
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
        return false;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String effectiveDate, String expirationDate) {
        if (validateEffectiveDate(effectiveDate, expirationDate)) {
            this.effectiveDate = effectiveDate;
        }
    }

    public boolean validateEffectiveDate(String effectiveDate, String expirationDate) {
        Date effDate;
        Date expDate;
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

        try {
            effDate = format.parse(effectiveDate);
            expDate = format.parse(expirationDate);
        } catch (ParseException e) {
            return false;
        }

        return effDate.before(expDate);
    }

    public void setExpirationDate(String expirationDate) {
        if (validateExpirationDate(expirationDate)) {
            this.expirationDate = expirationDate;
        }
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public boolean validateExpirationDate(String expirationDate) {
        Date expDate;
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

        try {
            expDate = format.parse(expirationDate);
        } catch (ParseException e) {
            return false;
        }

        return !expDate.before(today);
    }

    /*
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
     */

    public void setUsers(int users) {
        this.users = users;
    }

    public int getUsers() {
        return users;
    }

    public void addUser() {
        users += 1;
    }

    public String print() {
        String str = getPermitNumber() + "\n" + getPlateNumbers() + getEffectiveDate() + "\n" + getExpirationDate() + "\n" + getUsers();
        return str;
    }
}
