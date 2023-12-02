package AccountMngmt.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermitTest {
    AccountMngmt.entity.Permit p;

    @BeforeEach
    void setUp() {
        p = new AccountMngmt.entity.Permit();
    }

    @AfterEach
    void tearDown() {
        p.clearPermit();
    }

    @Test
    void allValid() {
        p.setPlateNumber("FNT 5284");
        p.setPermitNumber("23APT02130");
        p.setEffectiveDate("08-23-2023", "12-04-2023");
        p.setExpirationDate("12-04-2023");

        assertTrue(p.validateLicensePlate("FNT 5284"));
        assertTrue(p.validatePermitNumber("23APT02130"));
        assertTrue(p.validateEffectiveDate("08-23-2023","12-04-2023"));
        assertTrue(p.validateExpirationDate("12-04-2023"));
        assertTrue(p.getUsers() < 2);
    }

    @Test
    void invalidPlateNumber() {
        p.setPlateNumber("!403ADF");
        p.setPermitNumber("23APT02130");
        p.setEffectiveDate("08-23-2023", "12-04-2023");
        p.setExpirationDate("12-04-2023");

        assertFalse(p.validateLicensePlate("!403ADF"));
        assertTrue(p.validatePermitNumber("23APT02130"));
        assertTrue(p.validateEffectiveDate("08-23-2023", "12-04-2023"));
        assertTrue(p.validateExpirationDate("12-04-2023"));
        assertTrue(p.getUsers() < 2);
    }

    @Test
    void invalidPermitNumber() {
        p.setPlateNumber("FNT 5284");
        p.setPermitNumber("#245APT%$");
        p.setEffectiveDate("08-23-2023", "12-04-2023");
        p.setExpirationDate("12-04-2023");

        assertTrue(p.validateLicensePlate("FNT 5284"));
        assertFalse(p.validatePermitNumber("#245APT%$"));
        assertTrue(p.validateEffectiveDate("08-23-2023", "12-04-2023"));
        assertTrue(p.validateExpirationDate("12-04-2023"));
        assertTrue(p.getUsers() < 2);
    }

    @Test
    void invalidEffectiveDate() {
        p.setPlateNumber("FNT 5284");
        p.setPermitNumber("23APT02130");
        p.setEffectiveDate("08-23-2023", "01-23-2023");
        p.setExpirationDate("01-23-2023");

        assertTrue(p.validateLicensePlate("FNT 5284"));
        assertTrue(p.validatePermitNumber("23APT02130"));
        assertFalse(p.validateEffectiveDate("08-23-2024", "12-23-2023"));
        assertTrue(p.validateExpirationDate("12-23-2023"));
        assertTrue(p.getUsers() < 2);
    }

    @Test
    void invalidExpirationDate() {
        p.setPlateNumber("FNT 5284");
        p.setPermitNumber("23APT02130");
        p.setEffectiveDate("08-23-2023", "12-01-2023");
        p.setExpirationDate("12-01-2023");

        assertTrue(p.validateLicensePlate("FNT 5284"));
        assertTrue(p.validatePermitNumber("23APT02130"));
        assertTrue(p.validateEffectiveDate("08-23-2023", "12-01-2023"));
        assertFalse(p.validateExpirationDate("12-01-2023"));
        assertTrue(p.getUsers() < 2);

    }
}