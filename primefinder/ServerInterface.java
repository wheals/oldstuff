

/**
 * This interface specifies public methods for the Server class.  The server is responsible for 
 * accepting socket connections from a client, reading integers to check for primality,
 * and writing results to the client.
 * @author ralexander
 */
public interface ServerInterface {
	
	/**
	 * stopServer method exits the server instance
	 * @return true if server is set to null, false otherwise
	 */
	   public boolean stopServer();

	   /**
	    * the startServer method creates a new ServerSocket instance with the port supplied
	    * to the constructor and connects to a client socket created on the same machine ("localhost")
	    */
	   public void startServer(int clientDoneSignal);

}
