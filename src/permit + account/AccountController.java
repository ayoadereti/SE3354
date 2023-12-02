package AccountMngmt;

import AccountMngmt.entity.Permit;
import AccountMngmt.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

// Ayobami Adereti

// Attributes + methods for adding permits + managing user accounts 
// Database management executed with MySQL, Jakarta Persistence, and Hibernate 
public class AccountController {
    private String user;

    public AccountController() {
    }

    public AccountController(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Msg createAccount(String firstName, String lastName, String email, String password) {
        // Initialize a Configuration obj to specify Hibernate properties and mapping information 
        // Initalize a blank Msg obj to record method execution 
        Msg msg = new Msg();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(User.class);

        // Create a SessionFactory obj and initialize a Session obj to access database CRUD operations
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Instantiate a Transaction obj 
        session.beginTransaction();

        // Check database for existing User obj with email 
        User u = session.get(User.class, email);

        // Load an error message if email is already found in database 
        if (u != null) {
            msg.add("Username not available. Enter a different username");
        }
        else {
            u = new User(firstName, lastName, email, password);
            boolean valid = true;

            // Load an error message is user info contains invalid input 
            if (!u.validateFirstName(firstName)) {
                msg.add("Invalid first name");
                valid = false;
            }
            if (!u.validateLastName(lastName)) {
                msg.add("Invalid last name");
                valid = false;
            }
            if (!u.validateEmail(email)) {
                msg.add("Invalid email/username");
                valid = false;
            }
            if (!u.validatePassword(password)) {
                msg.add("Invalid password");
                valid = false;
            }

            // Load a confirmation message if User is sucessfully created 
            if (valid) {
                msg.add("Account successfully created");

                session.persist(u);
                session.getTransaction().commit();
                session.close();
            }
        }
        return msg;
    }

    public Msg addPermit(String permitNumber, String licensePlate, String effectiveDate, String expirationDate) {
        // Initialize a Configuration obj to specify Hibernate properties and mapping information 
        // Initalize a blank Msg obj to record method execution
        Msg msg = new Msg();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Permit.class);
        configuration.addAnnotatedClass(User.class);

        // Create a SessionFactory obj and initialize a Session obj to access database CRUD operations
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Fetch existing Transaction obj 
        session.beginTransaction();

        // Check database for existing permit with permitNumber
        Permit p = session.get(Permit.class, permitNumber);

        // Initialize new Permit obj if existing permit is not found 
        if (p == null) {
            p = new Permit(permitNumber, licensePlate, effectiveDate, expirationDate);
            boolean valid = true;

            // Load an error message if permit info contains invalid input 
            if (!p.validateLicensePlate(licensePlate)) {
                msg.add("Invalid plate number\nPermit could not be added\n");
                valid = false;
            }
            if (!p.validatePermitNumber(permitNumber)) {
                msg.add("Invalid permit number\nPermit could not be added\n");
                valid = false;
            }
            if (!p.validateEffectiveDate(effectiveDate, expirationDate)) {
                msg.add("Invalid effective date\nPermit could not be added\n");
                valid = false;
            }
            if (!p.validateExpirationDate(expirationDate)) {
                msg.add("Invalid expiration date\nPermit could not be added\n");
                valid = false;
            }

            // Update User and Permit objs respectively if permit info is valid
            // Load confirmation message 
            if (valid) {
                p.addUser();
                session.persist(p);
                session.getTransaction().commit();

                User u = (User) session.get(User.class, getUser());
                Transaction transaction = session.beginTransaction();
                
                u.setPermit(permitNumber);
                session.merge(u);
                transaction.commit();
                msg.add("Permit successfully added");
            }
        }
        else {
            // Update existing Permit obj if associated with less than 2 users, update User obj 
            // Load confirmation message 
            if (p.getUsers() < 2) {
                p.setPlateNumber(licensePlate);
                p.addUser();
                session.merge(p);
                session.getTransaction().commit();
                
                User u = (User) session.get(User.class, getUser());
                Transaction transaction = session.beginTransaction();

                u.setPermit(permitNumber);
                session.merge(u);
                transaction.commit();
                msg.add("Permit successfully added");
            }
            // Load error message if Permit obj has max Users (2)
            else {
                msg.add("Permit already associated with maximum (2) users. Enter a different permit");
                System.out.println("Plates: \n" + p.getPlateNumbers());
                System.out.println("Users: " + p.getUsers());
            }
        }
        session.close();
        return msg;
    }
}
