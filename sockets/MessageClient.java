import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MessageClient
{
    public static void main(String args[])
    {
        try
        {
            Scanner scan = new Scanner(System.in);
            Socket client = new Socket("localhost", 4608);
            Scanner in = new Scanner(client.getInputStream());
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            while (true)
            {
                String input = scan.nextLine();
                out.println(input);
                if (input.equals("QUIT"))
                    break;
                System.out.println(in.nextLine());
            }
            
            out.close();
            in.close();
            client.close();
            scan.close();
        } catch (UnknownHostException e)
        {
            System.err.println("No such host");
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
