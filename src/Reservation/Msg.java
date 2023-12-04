package Reservation;

//Gabe Butuc

public class Msg {
    public static void display(String message) {
        System.out.println(message);
    }
    
    public static void error(String message) {
        System.err.println("Error: " + message);
    }
    
    public static void prompt(String message) {
        System.out.print(message + ": ");
    }
}