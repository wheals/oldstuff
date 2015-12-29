import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Shmuale Mark
 *
 * Implements ClientInterface.
 */
public class Client implements ClientInterface
{
    Socket clientSocket;
    Scanner in;
    PrintWriter out;

    /**
     * Creates a new client and sockets it into an available server
     * @param hostname the hostname to look for a server on
     * @param port the port to look for a server on
     */
    public Client(String hostname, int port)
    {
        try
        {
            clientSocket = new Socket(hostname, port);
            in = new Scanner(clientSocket.getInputStream());
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkCandidates(ArrayList<Integer> candidatePrimes)
    {
        for (int i : candidatePrimes)
            sendMessage(Integer.toString(i)+ " ");
        
        String rec = receiveString();
        Scanner sc = new Scanner(rec);
        while (sc.hasNextInt())
        {
            int num = sc.nextInt();
            int prime = sc.nextInt();
            if (prime == 1)
                PrimeFinderManager.primes.add(num);
        }
        sc.close();
        return true;
    }

    @Override
    public void sendMessage(String msg)
    {
        out.println(msg);
    }

    @Override
    public String receiveString()
    {
        return in.nextLine();
    }

    @Override
    public boolean closeClient()
    {
        try
        {
            in.close();
            out.close();
            clientSocket.close();
            return true;
        } catch (IOException e)
        {
            return false;
        }
    }

    /**
     * Does this have an open socket?
     * @return true if there's no open socket, false if there is. 
     */
    public boolean isSocketClosed()
    {
        return clientSocket == null || clientSocket.isClosed();
    }

}
