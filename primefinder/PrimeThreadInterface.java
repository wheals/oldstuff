/**
 * the PrimeThread class is a thread (also implements Runnable) that determines if an integer 
 * is a prime number.   Since computing whether a number is a prime can be an expensive 
 * operation for large numbers, each candidate integer is tested for primality in its
 * own thread on a server.
 * @author ralexander
 *
 */
public interface PrimeThreadInterface {
	
	/**
	 * the isPrime method checks an integer for primality.  Its results are valid
	 * only for integers greater than or equal to 2. Non-integers, 1, 0, and negative numbers 
	 * are not eligible to be prime numbers, by definition of primality. 
	 * There are numerous algorithms to determine if a number is a prime; see the
	 * site http://en.wikipedia.org/wiki/Primality_test. 
	 * @param number an integer greater than 1 that is to be checked for primality
	 * @return true if number is a prime number, false if it is a composite number
	 */
	public boolean isPrime(int number);

}
