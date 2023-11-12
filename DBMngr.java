package Permit;

import java.util.ArrayList;

public class DBMngr {
    ArrayList<Permit> permits;

    public DBMngr() {
        permits = new ArrayList<>();
    }

    public Permit getPermit(String[] permitInfo) {
        // code
        return null;
    }

    public void savePermit(Permit p) {
        permits.add(p);
    }

    public void printDBDetails() {
        int permitNum = 1;

        for (Permit p : permits) {
            System.out.println("Permit " + permitNum);
            System.out.println(p.printDetails());
            permitNum++;
        }
    }
}
