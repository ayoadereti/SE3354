package login;
import java.time.*;

public class Reservation {
	int parkingSpotNumber;
	Instant starttime;
	Instant endtime;
	
	public Reservation(int parkingno, Instant start, Instant end){
		parkingSpotNumber = parkingno;
		starttime = start;
		endtime = end;
	}

}
