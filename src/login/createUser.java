package login;

import java.util.Scanner;

public class createUser {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String Username;
		String Password;
		System.out.print("Create Username: ");
		Username = scan.next();
		
		Msg message = new Msg();
		DBM database = new DBM();
		while(database.contains(Username)) {
			message.add("Username not available");
			System.out.println("Username not available, please enter a different username:");
			Username = scan.next();	
		}
		System.out.print("Password: ");
		Password = scan.next();
		database.createUser(Username, Password);
		
		scan.close();
	
	}
}
