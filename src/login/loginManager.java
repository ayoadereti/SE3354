package login;
import java.util.*;

public class loginManager{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String Username;
		String Password;
		boolean correctPW = false;
		System.out.print("Username: ");
		Username = scan.next();
		System.out.print("Password: ");
		Password = scan.next();
		Msg message = new Msg();
		DBM database = new DBM();
		if(database.contains(Username)) {
			User currUser = database.getuser(Username);
			correctPW = currUser.checkPassword(Password);
			if(correctPW) {
				message.add("login successful!");
			}
			else {
				message.add("incorrect password");
			}
		}
		else {
			message.add("Username not found");
		}
		scan.close();
	
	}
}