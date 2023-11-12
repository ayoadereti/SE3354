package Permit;

public class User {
    String firstName;
    String lastName;
    String email;
    String password;
    Permit p;
    // ArrayList<Reservation> reservations;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addPermit(Permit p) {
        this.p = p;
    }
}
