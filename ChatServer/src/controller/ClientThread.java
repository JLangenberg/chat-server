package controller;

import java.util.ArrayList;

import view.UI;

public class ClientThread extends Thread {

	private ClientConnection clientConnection = null;
	private ArrayList<ClientThread> clientThreads = null;
	private String userName = null;
	private String messagePrefix = null;

	// Construct the thread and receive the connection
	public ClientThread(ClientConnection clientConnection, 
						ArrayList<ClientThread> clientThreads, String userName) {
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
		ui.successfullConnectionNotification();

		// Loop until the client sends the "bye" request.
		while (true) {

			// Read all requests
			String request = this.clientConnection.readInputStream();
			// Check if the connection should be ended. If yes, break and close.
			if (request.equals("bye") || request.equals("ERROR")) {
				break;
			}

			for (int i = 0; i < clientThreads.size(); i++) {
				this.clientThreads.get(i).sendMessage(messagePrefix + request);
			}

			// TODO: remove the object from array list if it is supposed to end
		}

		// Close all connections to the client after the user decided to end the
		// process.
		clientConnection.close();
	}

	public void sendMessage(String message) {
		this.clientConnection.writeOutputStream(message);
	}
}
