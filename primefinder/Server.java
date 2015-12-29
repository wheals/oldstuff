import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Shmuale Mark
 * 
 *         Implementation of ServerInterface.
 */
public class Server implements ServerInterface
{
    public Socket clientSocket = null;
    private ServerSocket serverSocket = null;

    /**
     * Set up this server.
     * 
     * @param port
     *            the port that it will be doing business on.
     */
    public Server(int port)
    {
        try
        {
            serverSocket = new ServerSocket(port);
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Shut down this server.
     * 
     * @return true if the server was successfully closed.
     */
    @Override
    public boolean stopServer()
    {
        try
        {
            serverSocket.close();
            clientSocket.close();
        } catch (IOException e)
        {
            return false;
        }
        return true;
    }

    /**
     * Open a new socket listening on this.port.
     * 
     * @param clientDoneSignal
     *            A sentinel value to indicate the client is done sending
     *            numbers.
     */
    @Override
    public void startServer(int clientDoneSignal)
    {
        try
        {
            while (true)
            {
                clientSocket = serverSocket.accept();

                Scanner in = new Scanner(clientSocket.getInputStream());
                ArrayList<PrimeThread> threads = new ArrayList<PrimeThread>();
                for (int newInt = in.nextInt(); newInt != clientDoneSignal; newInt = in
                        .nextInt())
                {
                    PrimeThread pth = new PrimeThread(newInt);
                    pth.start();
                    threads.add(pth);
                }

                String ret = "";
                for (PrimeThread thread : threads)
                {
                    int num = thread.num;
                    ret += num;
                    ret += " ";
                    ret += thread.isPrime(num) ? "1" : "0";
                    ret += " ";
                }

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(ret);

                in.close();
                out.close();
            }
        } catch (SocketException e)
        {
            // Don't do anything, this just means they hung up before we could.
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
