package view;

import java.util.Scanner;

/**
 * Used to print messages to the console and get user input
 * 
 * @author Julius Langenberg
 *
 */
public class UI {

	/**
	 * Prints a server start notification to the console
	 */
	public void startNotification() {
		System.out.println("Server started. Waiting for requests.");
	}

	/**
	 * Prints an incoming request notification to the console
	 * 
	 * @param request The request to be printed
	 */
	public void requestNotification(String request) {
		System.out.println(request);
	}

	/**
	 * Notifies the user that there has been a successful connection to the console
	 */
	public void successfullConnectionNotification(String userName) {
		System.out.println("Successfully connected to client " + userName + "!");
	}

	/**
	 * Notifies the user that a client has disconnected
	 * 
	 * @param userName The name of the disconnecting user
	 */
	public void disconnectionNotification(String userName) {
		System.out.println(userName + " disconnected.");
	}

	// TODO: Could add port specific method and check for conditions (input)

	/**
	 * Gets a number from user input
	 * 
	 * @return The integer the user entered
	 */
	public Integer getNumber() {

		// The scanner used to read console input
		Scanner scanner = new Scanner(System.in);

		// Tell the user that they should enter a port
		System.out.println("Please enter a port");

		// Get the user input
		String port = scanner.nextLine();

		// Return the user input to the controller
		return Integer.parseInt(port);
	}
}
