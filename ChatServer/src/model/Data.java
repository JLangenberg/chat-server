package model;

/**
 * Stores the port of the server
 * 
 * @author Julius Langenberg, AH811
 *
 */
public class Data {
	// The port
	private int port;

	/**
	 * Sets the port to 'portIn'
	 * 
	 * @param portIn The port that should be used
	 */
	public Data(int portIn) {
		this.port = portIn;
	}

	// Getters and Setters \\

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
