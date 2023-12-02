package AccountMngmt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.regex.Pattern;

@Entity
public class User {
    @Column
    private String firstName;
    
    @Column
    private String lastName;
    
    @Id
    @Column(name="username")
    private String email;
    
    @Column
    private String password;
    
    @Column(name="permitNumber")
    String permit;

    // Reservation reservation;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (validateFirstName(firstName)) {
            this.firstName = firstName;
        }
    }

    public boolean validateFirstName(String firstName) {
        for (char c : firstName.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (validateLastName(lastName)) {
            this.lastName = lastName;
        }
    }

    public boolean validateLastName(String lastName) {
        for (char c : lastName.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validateEmail(email)) {
            this.email = email;
        }
    }

    public boolean validateEmail(String email) {
        String [] emailFrag = email.split("@");

        if (!emailFrag[1].equals("utdallas.edu")) {
            return false;
        }
        if (emailFrag[0].length() == 9) {
            String initials = emailFrag[0].substring(0, 3);
            String nums = emailFrag[0].substring(3);
            
            for (char c : initials.toCharArray()) {
                if (!Character.isLetter(c)) {
                    System.out.println("Contains a non-alphabetic char");
                    return false;
                }
            }

            try {
                int i = Integer.parseInt(nums);
            }
            catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (validatePassword(password)) {
            this.password = password;
        }
    }

    public boolean validatePassword(String password) {
        boolean valid = false;

        if (password.length() >= 6 && password.length() <= 8) {
            for (char c : password.toCharArray()) {
                if (!Character.isLetterOrDigit(c)) {
                    if (!Pattern.matches("\\p{Punct}", String.valueOf(c))) {
                        return false;
                    }
                    else {
                        valid = true;
                    }
                }
            }
        }
        return valid;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public boolean validatePermit(String permit) {
        if (permit.length() == 10) {
            for (char c : permit.toCharArray()) {
                if (!Character.isLetterOrDigit(c)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
