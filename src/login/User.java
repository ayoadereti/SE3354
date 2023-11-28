package login;

public class User {
	private String Username;
	private String Password;
	private Reservation reservation;
	private String Permission;
	User(String UN, String PW, String Perm){
		Username = UN;
		Password = PW;
		Permission = Perm;
	}
	User(String UN, String PW, Reservation RES, String Perm){
		Username = UN;
		Password = PW;
		reservation = RES;
		Permission = Perm;
	}
	
	public boolean checkPassword(String input) {
		if(input.equals(Password)) {
			return true;
		}
		return false;
		
	}
	public String getUsername() {
		return Username;
	}
	public String getPassword() {
		return Password;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setPermission(String perm) {
		Permission = perm;
	}
	public String getPermission() {
		return Permission;
	}

}
