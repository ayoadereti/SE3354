package login;

public class User {
	private String Username;
	private String Password;
	private Reservation reservation;
	User(String UN, String PW){
		Username = UN;
		Password = PW;
	}
	User(String UN, String PW, Reservation RES){
		Username = UN;
		Password = PW;
		reservation = RES;
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

}
