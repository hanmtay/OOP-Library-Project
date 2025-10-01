package part02;

import java.util.Scanner;

import console.Console;

public class Menu02 {
	private String items[];
	private String title;
	private Scanner input;
	
	private Console con;
	
	
	public Menu02(String title, String data[], Console con) {
		this.title = title;
		this.items = data;
		this.input = new Scanner(System.in);
		this.con =  con;
	}
	
	private void display() {
		con.println(title);
		for(int count=0;count<title.length();count++) {
			con.print("+");
		}
		con.println();
		for(int option=1; option<=items.length; option++) {
			con.println(option + ". " + items[option-1] );
		}
		con.println();
	}
	
	public int getUserChoice() {
		display();
		con.print("Enter Selection: ");
		try {
			String text = con.readLn();
			text = text.trim();
			Scanner input = new Scanner(text);
			int value = input.nextInt();
			return value;
			
		} catch(Exception ex) {
			return 0;
		}
	}
}
