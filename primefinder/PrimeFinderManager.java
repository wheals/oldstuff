import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Shmuale Mark
 *
 * Implementation of PrimeFinderManagerInterface.
 */
public class PrimeFinderManager implements PrimeFinderManagerInterface
{
    public static ArrayList<Integer> primes = new ArrayList<Integer>();
    public Client client;

    public PrimeFinderManager()
    {
        // reset primes each time a new one is made
        primes = new ArrayList<Integer>();
    }
    
    @Override
    public void runPrimeFinder(String strNumbers) throws NumberFormatException
    {
        runPrimeFinder("localhost", 4609, strNumbers);
    }

    @Override
    public void writePrimes(File outFile)
    {
        try
        {
            PrintWriter out = new PrintWriter(outFile);
            for (int i : getPrimeList())
                out.println(i);
            out.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Integer> getPrimeList()
    {
        return primes;
    }

    @Override
    public void runPrimeFinder(String hostname, int port, String strNumbers)
            throws NumberFormatException
    {
        if (client != null && !client.isSocketClosed())
            client.closeClient();
        client = new Client(hostname, port);
        ArrayList<Integer> toCheck = new ArrayList<Integer>();
        Scanner sc = new Scanner(strNumbers);
        try
        {
            while (sc.hasNext())
            {
                toCheck.add(sc.nextInt());
            }
        } catch (Exception e)
        {
            throw new NumberFormatException("Input must be in the form of space-separated positive integers not greater than 2147483647.");
        } finally
        {
            sc.close();
        }
        if (toCheck.size() == 0)
            throw new NumberFormatException("No input specified.");
        if (toCheck.get(toCheck.size() - 1) != -999)
            throw new NumberFormatException("Input must end with -999.");
        client.checkCandidates(toCheck);
    }

}
