package AccountMngmt;

import AccountMngmt.entity.Permit;
import AccountMngmt.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
        Msg msg = new Msg();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(User.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        User u = new User(firstName, lastName, email, password);
        boolean valid = true;

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
        if (valid) {
            msg.add("Account successfully created");

            session.persist(u);
            session.getTransaction().commit();
            session.close();
        }

        return msg;
    }

    public Msg addPermit(String permitNumber, String licensePlate, String effectiveDate, String expirationDate) {
        Msg msg = new Msg();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Permit.class);
        configuration.addAnnotatedClass(User.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Permit p = session.get(Permit.class, permitNumber);

        if (p == null) {
            p = new Permit(permitNumber, licensePlate, effectiveDate, expirationDate);
            boolean valid = true;

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
            if (valid) {
                p.addUser();
                session.persist(p);
                session.getTransaction().commit();

                // String user = "alm230045@utdallas.edu";
                User u = (User) session.get(User.class, getUser());

                Transaction transaction = session.beginTransaction();

                u.setPermit(permitNumber);
                session.merge(u);
                transaction.commit();

                System.out.println("User Permit: " + u.getPermit());
                msg.add("Permit successfully added");
            }
        }
        else {
            if (p.getUsers() < 2) {
                p.setPlateNumber(licensePlate);
                p.addUser();
                session.merge(p);
                session.getTransaction().commit();

                User u = (User) session.get(User.class, getUser());
                u.setPermit(permitNumber);

                Transaction transaction = session.beginTransaction();

                session.merge(u);
                transaction.commit();
                msg.add("Permit successfully added");
                System.out.println("User Permit: " + u.getPermit());
            }
            else {
                msg.add("Permit already associated with maximum (2) users");
                System.out.println("Plates: \n" + p.getPlateNumbers());
                System.out.println("Users: " + p.getUsers());
            }
        }

        session.close();

        return msg;
    }
}
