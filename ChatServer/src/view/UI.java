package view;

/**
 * Used to print messages to the console
 * 
 * @author Julius Langenberg, AH811
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
	public void successfullConnectionNotification() {
		System.out.println("Successfully connected to client!");
	}
}
