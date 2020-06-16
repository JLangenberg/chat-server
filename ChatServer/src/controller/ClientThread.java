package controller;

import java.util.ArrayList;

import view.UI;

/**
 * Handles everything related to the client. Reads the input, and sends the
 * message to the other clients
 * 
 * @author Julius Langenberg
 *
 */
public class ClientThread extends Thread {

	// The clientConnection of this object
	private ClientConnection clientConnection = null;
	// All of the connected clients
	private ArrayList<ClientThread> clientThreads = null;
	// The userName of this client
	private String userName = null;
	// The prefix supposed to be added to all messages that use the prefix
	private String messagePrefix = null;

	/**
	 * Construct the thread and receive the connection
	 * 
	 * @param clientConnection This connection
	 * @param clientThreads    All of the connected clientThreads
	 * @param userName         The userName of this instance
	 */
	public ClientThread(ClientConnection clientConnection, ArrayList<ClientThread> clientThreads, String userName) {
		this.clientConnection = clientConnection;
		this.clientThreads = clientThreads;
		this.userName = userName;
		this.messagePrefix = this.userName + " said : ";
	}

	public void run() {
		// Construct UI and Utility objects to be able to use their methods.
		// User interaction, console in- output
		UI ui = new UI();
		// Diverse methods for differing tasks
		Utility util = new Utility();

		// Inform the user that a client successfully connected.
		ui.successfullConnectionNotification(this.userName);

		// Inform the other clients a client just connected.
		broadcastMessageNoPrefix(this.userName + " just joined. Say hello!");

		// Loop until the client sends the "bye" request.
		while (true) {

			// Read all requests
			String request = this.clientConnection.readInputStream();

			// Check if the connection should be ended. If yes, break and close.
			if (request.equals("bye") || request.equals("ERROR")) {
				// Tell all clients a client has disconnected (if possible)
				broadcastMessageNoPrefix(this.userName + " just disconnected!");
				// Tell the server user who disconnected
				ui.disconnectionNotification(this.userName);
				break;
			}

			// Send all clients the sent message
			this.broadcastMessage(request);

			// Check if the request is something the server can answer to
			if (util.determineAnswer(request) != "") {
				// If yes, answer accordingly to all.
				for (int i = 0; i < clientThreads.size(); i++) {
					this.clientThreads.get(i).sendMessage("Server: " + util.determineAnswer(request));
				}
			}
			// TODO: remove the object from array list if it is supposed to end
		}

		// Close all connections to the client after the user decided to end the
		// process.
		clientConnection.close();
	}

	/**
	 * Send a message to the client connected to this thread
	 * 
	 * @param message The message to send
	 */
	public void sendMessage(String message) {
		this.clientConnection.writeOutputStream(message);
	}

	/**
	 * Broadcast a message to all connected clients. Adds a prefix
	 * 
	 * @param request the message to be send
	 */
	public void broadcastMessage(String message) {
		for (int i = 0; i < clientThreads.size(); i++) {
			this.clientThreads.get(i).sendMessage(messagePrefix + message);
		}
	}

	/**
	 * Broadcast a message to all connected clients, don't add a prefix.
	 * 
	 * @param message
	 */
	public void broadcastMessageNoPrefix(String message) {
		for (int i = 0; i < clientThreads.size(); i++) {
			this.clientThreads.get(i).sendMessage(message);
		}
	}
}
