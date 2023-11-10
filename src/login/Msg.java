package login;

public class Msg {
	String output = null;
	public void add(String input) {
		output = input;
	}
	public void print() {
		System.out.println(output);
	}

}
