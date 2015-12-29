import java.io.File;
import java.util.ArrayList;

public interface PrimeFinderManagerInterface {
	
	/**
	 * a sentinel that signals the server that the client is finished with its input.  
	 * This allows the server to close gracefully when the application cleans up.
	 * The user needs to enter this integer as the last entry in the list of 
	 * candidate prime numbers.
	 */
	public static final int CLIENT_INPUT_DONE = -999;
	
	/**
	 * the runPrimeFinder method parses the input string, expecting integers separated
	 * by single spaces, parses each separated string into an integer, adds them to
	 * an ArrayList of integers, and calls the Client constructor with that list
	 * @param strNumbers - a string that expects to be parsed into integers 
	 * each separated by single spaces
	 * @throws NumberFormatException if any of the character strings cannot be
	 * parsed to an integer after parsing them by " "
	 */
	public void runPrimeFinder(String strNumbers) throws NumberFormatException;
	
	/**
	 * the writePrimes method writes the list of primes found to a text file
	 * @param outFile an instance of File to which the list of primes will be written
	 */
	public void writePrimes(File outFile);
	
	/**
	 * getPrimeList() retrieves the list of primes as computed by PrimeThread
	 * @return an ArrayList of Integers, each of which is a prime number
	 */
	public ArrayList<Integer> getPrimeList();

	/**
	 * the runPrimeFinder method parses the input string, expecting integers separated
	 * by single spaces, parses each separated string into an integer, adds them to
	 * an ArrayList of integers, and calls the Client constructor with that list.
	 * This version of runPrimeFinder also takes the hostname and port as parameters
	 * to allow flexibility in connections.
	 * @throws NumberFormatException if any of the character strings cannot be
	 * parsed to an integer after parsing them by " "
	 * @param hostname
	 * @param port
	 * @param strNumbers - a string that expects to be parsed into integers 
	 *                     each separated by single spaces
	 * @throws NumberFormatException
	 */
	public void runPrimeFinder(String hostname, int port, String strNumbers) throws NumberFormatException;
	

}
