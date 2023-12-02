package AccountMngmt.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// UC01 - Create Account
// Ayobami Adereti

class UserTest {

    User u;

    @BeforeEach
    void setUp() {
        u = new User();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void allValid() {
        u.setFirstName("Ayobami");
        u.setLastName("Adereti");
        u.setEmail("ara190013@utdallas.edu");
        u.setPassword("ohYeah!");
        u.setPermit("testPermit");

        assertTrue(u.validateFirstName("Ayobami"));
        assertTrue(u.validateLastName("Adereti"));
        assertTrue(u.validateEmail("ara190013@utdallas.edu"));
        assertTrue(u.validatePassword("ohYeah!"));
        assertTrue(u.validatePermit("testPermit"));
    }

    @Test
    void invalidName() {
        u.setFirstName("Ayobam!");
        u.setLastName("Aderet i");
        u.setEmail("ara190013@utdallas.edu");
        u.setPassword("ohYeah!");
        u.setPermit("testPermit");

        assertFalse(u.validateFirstName("Ayobam!"));
        assertFalse(u.validateLastName("Aderet i"));
        assertTrue(u.validateEmail("ara190013@utdallas.edu"));
        assertTrue(u.validatePassword("ohYeah!"));
        assertTrue(u.validatePermit("testPermit"));
    }

    @Test
    void invalidEmail() {
        u.setFirstName("Ayobami");
        u.setLastName("Adereti");
        u.setEmail("ara19o013@utdallas.edu");
        u.setPassword("ohYeah!");
        u.setPermit("testPermit");

        assertTrue(u.validateFirstName("Ayobami"));
        assertTrue(u.validateLastName("Adereti"));
        assertFalse(u.validateEmail("ara19o013@utdallas.edu"));
        assertTrue(u.validatePassword("ohYeah!"));
        assertTrue(u.validatePermit("testPermit"));
    }

    @Test
    void invalidPassword() {
        u.setFirstName("Ayobami");
        u.setLastName("Adereti");
        u.setEmail("ara190013@utdallas.edu");
        u.setPassword("ohNo");
        u.setPermit("testPermit");

        assertTrue(u.validateFirstName("Ayobami"));
        assertTrue(u.validateLastName("Adereti"));
        assertTrue(u.validateEmail("ara190013@utdallas.edu"));
        assertFalse(u.validatePassword("ohNo"));
        assertTrue(u.validatePermit("testPermit"));
    }

    @Test
    void invalidPermit() {
        u.setFirstName("Ayobami");
        u.setLastName("Adereti");
        u.setEmail("ara190013@utdallas.edu");
        u.setPassword("ohYeah!");
        u.setPermit("bad Permit");

        assertTrue(u.validateFirstName("Ayobami"));
        assertTrue(u.validateLastName("Adereti"));
        assertTrue(u.validateEmail("ara190013@utdallas.edu"));
        assertTrue(u.validatePassword("ohYeah!"));
        assertFalse(u.validatePermit("bad Permit"));
    }



}
