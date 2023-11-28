package login;
import java.sql.*;

public class Reservation {
	int parkingSpotNumber;
	Timestamp starttime;
	Timestamp endtime;
	
	public Reservation(int parkingno, Timestamp start, Timestamp end){
		parkingSpotNumber = parkingno;
		starttime = start;
		endtime = end;
	}

}
