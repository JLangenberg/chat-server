package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import model.Data;
import view.UI;

/**
 * The main class that controls the program
 * 
 * @author Julius Langenberg, AH811
 * 
 */
public class ControllerServer {
	
	ArrayList<ClientThread> clientThreads = new ArrayList<>();

	public static void main(String[] args) {

		// Create a new instance of controller to use its methods.
		ControllerServer controller = new ControllerServer();

		// Create a data object that stores relevant information
		Data data = new Data(1234);

		// Create a UI object that gives feedback to the user
		UI ui = new UI();

		// Fill the server socket and start the server
		ServerSocket serverSocket = controller.startServer(data);

		// Inform the user that the program is started
		ui.startNotification();

		// Start the request loop to get and react to requests
		controller.getRequestLoop(serverSocket, ui, controller);

		// Shut the server down.
		controller.closeServer(serverSocket);
	}

	/**
	 * The method that continuously gets and reacts to client requests.
	 * 
	 * @param clientConnection The connection to the client
	 * @param ui               The UI objects used to interact with the console
	 * @param controller       The controller object used to access methods from the
	 *                         controller.
	 */
	private void getRequestLoop(ServerSocket serverSocket, UI ui, ControllerServer controller) {
		// Loop until 'clientLimit' connections have been made. That is the artificial limit.
		int clientLimit = 5;
		String[] userNames = {"Jeff", "Birdman", "Spiderman", "Coolman", "Batman", "Last Man"};
		
		for (int i = 0; i < clientLimit; i++) {
			
			// Establish a client connection
			ClientConnection clientConnection = new ClientConnection(serverSocket);
			
			// Embed the new connection in a thread
			clientThreads.add(new ClientThread(clientConnection, clientThreads, userNames[i]));
			
			// Start the thread to communicate with the client.
			clientThreads.get(clientThreads.size()-1).start();
		}
	}

	/**
	 * Closes the server socket stream
	 * 
	 * @param serverSocket The server socket that should be closed
	 */
	private void closeServer(ServerSocket serverSocket) {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts the server
	 * 
	 * @param data The object containing the required information to start the
	 *             server
	 * @return An initialized serverSocket
	 */
	private ServerSocket startServer(Data data) {
		// Create an empty socket
		ServerSocket serverSocket = null;
		try {
			// Fill it.
			serverSocket = new ServerSocket(data.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Return it.
		return serverSocket;
	}
}
