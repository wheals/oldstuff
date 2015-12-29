/**
 * @author Shmuale Mark Implementation of PrimeThreadInterface, that is also a
 *         Thread.
 */
public class PrimeThread extends Thread implements PrimeThreadInterface
{
    public int num;
    private boolean prime;

    private boolean doneExecuting = false;

    public PrimeThread(int number)
    {
        num = number;
        prime = true;
    }

    /**
     * Check if this number is prime
     * 
     * @param number
     *            must be the same as this.num
     * @return true if number is a prime number
     */
    @Override
    public boolean isPrime(int number)
    {
        // Keep waiting until run() finishes what it's doing
        while (!doneExecuting)
            try
            {
                Thread.sleep(10);
            } catch (InterruptedException e)
            {
                return false;
            }

        assert (number == num);
        return prime;

    }

    /**
     * Start this thread looking for numbers that are factors of num.
     */
    @Override
    public void run()
    {
        for (int i = 2; i <= Math.sqrt(num); i++)
            if (num % i == 0)
            {
                prime = false;
                break;
            }
        doneExecuting = true;
    }

}
