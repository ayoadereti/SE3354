package login;

import java.util.*;
import java.io.*;
import java.time.*;
import java.text.*;

public class DBM {
	boolean contains = false;
	ArrayList<User> users = new ArrayList<User>();
		
	public DBM() {
		/*
		 * This is so that we can have data on startup
		 * the gerneral format is this
		 * 
		 * Username Password ParkingSpotNumber Starttime endtime
		 * 
		 * the actual database is just an arraylist of Users each of which can have their own reservation object
		 */
		File Database = new File("Database");
		String Username = "";
		String Password = "";
		int ParkingSpotNumber = -1;
		Instant StartTime = null;
		Instant EndTime = null;;
		String pattern = "MM/dd/yy/kk/mm";
		DateFormat df = new SimpleDateFormat(pattern);
		
		
		try {
			Scanner in = new Scanner(Database);
			
			while(in.hasNext()) {
				Username = in.next();
				Password = in.next();
				ParkingSpotNumber = in.nextInt();
				if(ParkingSpotNumber == 0) {
					User user = new User(Username, Password);
					users.add(user);
					break;
				}
				try {
					StartTime = df.parse(in.next()).toInstant();
					EndTime = df.parse(in.next()).toInstant();
					
				}
				catch (ParseException e) {
				}
				Reservation res = new Reservation(ParkingSpotNumber, StartTime, EndTime);
				User user = new User(Username, Password, res);
				users.add(user);
			}
			in.close();
		} 
		catch (FileNotFoundException e) {
			System.out.print("HI");
		}
		
	}

	public boolean contains(String Username) {
		contains = false;
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsername().equals(Username)) {
				contains = true;
			}
		}
		return contains;
	}
	public User getuser(String Username) {
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsername().equals(Username)) {
				return users.get(i);
			}
		}
		return null;
	}
	public void print() {
		User user = users.get(0);
		System.out.println(user.getUsername() + " " + user.getPassword() + " "  + user.getReservation().starttime + " " + user.getReservation().endtime );
	}
	
}
