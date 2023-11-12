package Permit;

public class Msg {
    String message;
    public Msg() {
        message = "";
    }
    public void add(String message) {
        this.message += message +"\n";
    }
    public String print() {
        return message;
    }
}
