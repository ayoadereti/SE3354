package Reservation;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// UC10 - Make Reservation
// Gabe Butuc

public class NewReservation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Msg.prompt("Enter reservation date (yyyy-MM-dd)");
        String dateInput = scanner.nextLine();

        Msg.prompt("Enter start time (HH:mm)");
        String startTimeInput = scanner.nextLine();

        Msg.prompt("Enter stop time (HH:mm)");
        String stopTimeInput = scanner.nextLine();

        Msg.prompt("Enter parking lot (PS1-PS3 or LotA-LotE)");
        String parkingLotInput = scanner.nextLine();

        Msg.prompt("Enter parking spot number");
        int parkingSpotInput = scanner.nextInt();

        try {
            LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalTime startTime = LocalTime.parse(startTimeInput, DateTimeFormatter.ISO_LOCAL_TIME);
            LocalTime stopTime = LocalTime.parse(stopTimeInput, DateTimeFormatter.ISO_LOCAL_TIME);

            Reservation reservation = new Reservation(date, startTime, stopTime, parkingLotInput, parkingSpotInput);
            Msg.display("Reservation created successfully!");
        } catch (DateTimeParseException e) {
            Msg.error("Invalid date or time format.");
        } catch (IllegalArgumentException e) {
            Msg.error(e.getMessage());
        }

        scanner.close();
    }
}
