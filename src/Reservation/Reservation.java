package Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime stopTime;
    private String parkingLot;
    private int parkingSpot;

    // Constructors, getters, and setters
    public Reservation(LocalDate date, LocalTime startTime, LocalTime stopTime, String parkingLot, int parkingSpot) {
        validateDate(date);
        validateTime(startTime, stopTime);
        validateParkingLot(parkingLot);
        validateParkingSpot(parkingLot, parkingSpot);
        
        this.date = date;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.parkingLot = parkingLot;
        this.parkingSpot = parkingSpot;
    }
    
    private void validateDate(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            Msg.error("Date must be after the current date.");
            throw new IllegalArgumentException("Date must be after the current date.");
        }
    }
    
    private void validateTime(LocalTime startTime, LocalTime stopTime) {
        if (!startTime.isBefore(stopTime)) {
            Msg.error("Start time must be before stop time.");
            throw new IllegalArgumentException("Start time must be before stop time.");
        }
        if (startTime.plusHours(1).isAfter(stopTime)) {
            Msg.error("Minimum reservation time is one hour.");
            throw new IllegalArgumentException("Minimum reservation time is one hour.");
        }
        if (startTime.isBefore(LocalTime.of(9, 0)) || stopTime.isAfter(LocalTime.of(17, 0))) {
            Msg.error("Reservation must be between 9 AM and 5 PM.");
            throw new IllegalArgumentException("Reservation must be between 9 AM and 5 PM.");
        }
    }
    
    private void validateParkingLot(String parkingLot) {
        if (!(parkingLot.startsWith("PS") && parkingLot.length() == 3 && Character.isDigit(parkingLot.charAt(2))
              || (parkingLot.startsWith("Lot") && parkingLot.length() == 4 && Character.isLetter(parkingLot.charAt(3))))) {
            Msg.error("Invalid parking lot input.");
            throw new IllegalArgumentException("Invalid parking lot input.");
        }
    }
    
    private void validateParkingSpot(String parkingLot, int parkingSpot) {
        int maxSpots = parkingLot.startsWith("PS") ? 200 : 100;
        if (parkingSpot < 1 || parkingSpot > maxSpots) {
            Msg.error("Invalid parking spot number.");
            throw new IllegalArgumentException("Invalid parking spot number.");
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalTime stopTime) {
        this.stopTime = stopTime;
    }

    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public int getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(int parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}