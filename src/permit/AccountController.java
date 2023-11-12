package Permit;

public class AccountController {
    User u;

    public AccountController(User u) {
        this.u = u;
    }
    public Msg addPermit(String[] permitInfo) {
        // create a blank msg
        Msg msg = new Msg();

        // create an instance of Permit.DBMngr
        DBMngr db = new DBMngr();

        // check for an existing permit using permit info
        Permit p = db.getPermit(permitInfo);

        // if found
        if (p != null) {
            // if associated with less than 2 users
            if (p.getUsers() < 2) {
                // update permit with user's license plate
                p.addUser(permitInfo[0]);
                // add the permit to the user object
                u.addPermit(p);
                msg.add("Permit.Permit successfully added");
            }
            else {
                msg.add("Permit.Permit already associated with maximum users");
            }
        }
        // create a new Permit.Permit object with permit info
        else {
            p = new Permit(permitInfo[0], permitInfo[1], permitInfo[2], permitInfo[3]);
            db.savePermit(p);
            msg.add("Permit.Permit successfully added");
            db.printDBDetails();
        }

        return msg;
    }
}
