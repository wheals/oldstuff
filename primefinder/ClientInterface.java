

import java.util.ArrayList;

/**
 * Interface to the Client class; specifies the methods required to be implemented
 * @author ralexander
 *
 */
public interface ClientInterface {

	/**
	 * checkCandidates method uses the Client socket's i/o stream
	 *        to send candidate prime integers to the server and to receive responses
	 *        from the server as to whether or not each integer is a prime number
	 *        For each candidate sent to the server, the server responds with a string
	 *        of the form "n 1" if n is a prime, or "n 0" if it is not.  The method adds 
	 *        the primes to an ArrayList<Integer> that can be accessed for display
	 *        or writing to a file.
	 * @param candidatePrimes - a list of integers that may or may not be prime numbers.  
	 *        The list should end with a sentinal value that signals the end of the list 
	 *        for the server. By default this sentinal will be -999. 
	 * @return a boolean that indicates that the server has responded to all 
	 *         the candidates sent to it 
	 */
	public boolean checkCandidates(ArrayList<Integer> candidatePrimes); 
	
	/**
	 * sendMessage method uses the client's output stream to send an integer in the form 
	 *        of a string to the server.
	 * @param msg an integer represented as a String
	 */
	public void sendMessage(String msg);
		
	/**
	 * receiveString method uses the client's input stream connected to the server to
	 *        read responses from the server.  The the string of the form "n 1" 
	 *        if n is a prime, or "n 0" if it is not.
	 * @return the String read from the server
	 */
	public String receiveString();
		
	/**
	 * closeClient method closes the input and output streams of the client's socket and
	 *        then closes the client socket itself.  It catches a SocketException if the
	 *        socket is blocked in an I/O operation.
	 * @return true if the socket and i/o streams are successfully closed, false otherwise.
	 */
	public boolean closeClient();

	
	
}
