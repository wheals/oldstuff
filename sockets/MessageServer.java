import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

class MessageServer
{
    /**
     * Process input from the client, and return when a QUIT signal is received.
     * 
     * @param socket
     *            the client being processed
     */
    private static void handleConnection(Socket client) throws IOException
    {
        Scanner in = new Scanner(client.getInputStream());
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        while (true)
        {
            Random rng = new Random();
            String input = in.nextLine();
            System.out.println("received input: " + input);
            if (input.equals("QUIT"))
                break;
            else if (input.equals("INTEGER"))
                out.println(rng.nextLong());
            else if (input.equals("REAL"))
                out.println(rng.nextDouble());
            else if (input.startsWith("SEED "))
            {
                try
                {
                    long seed = Long.parseLong(input.substring("SEED ".length()));
                    rng.setSeed(seed);
                    out.println("Set seed to " + seed + ".");
                } catch (NumberFormatException e)
                {
                    out.println("Malformed input; seed not set.");
                }
            }
            else
                out.println("Unrecognised request received.");
        }

        out.close();
        in.close();
    }

    public static void main(String[] args)
    {
        ServerSocket server = null;
        Socket client = null;
        try
        {
            server = new ServerSocket(4608);
            System.out.println("Waiting for clients to connect . . .");

            while (true)
            {
                client = server.accept();
                System.out.println("Connected to socket.");
                handleConnection(client);
                System.out.println("Disconnected from socket.");
                client.close();
            }
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
