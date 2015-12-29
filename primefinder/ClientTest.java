

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest {
	static final String TEST_HOSTNAME = "localhost";
	static final int TEST_PORT = 5555;
	
    @Test
    public void testClientConnect() {
        ServerSocket testServerSocket = null;
        
        Client client = null;
        try {
            testServerSocket = new ServerSocket(TEST_PORT);
            testServerSocket.setSoTimeout(100);
            client = new Client(TEST_HOSTNAME, TEST_PORT);
            testServerSocket.accept();
        } catch (IOException e) {
            fail(e.toString());
        }
 
        try {
            testServerSocket.close();
        } catch (IOException e) {
        	fail(e.toString());
        }
        client.closeClient();
    }

    @Test
    public void testClientSendMessage() {
        ServerSocket testServerSocket = null;
        Socket testSocketServerSide = null;
        Scanner testScanner;
        Client client = null;
        try {
            testServerSocket = new ServerSocket(TEST_PORT);
            testServerSocket.setSoTimeout(100);
            client = new Client(TEST_HOSTNAME, TEST_PORT);
            testSocketServerSide = testServerSocket.accept();
            testScanner = new Scanner(testSocketServerSide.getInputStream());
            int testInt = 111;
            client.sendMessage(testInt+"");
            String result = testScanner.nextLine();
            System.out.println(result);
            assertEquals(testInt+"", result);
        } catch (IOException e) {
            fail(e.toString());
        }
 
        try {
            testServerSocket.close();
        } catch (IOException e) {
        	fail(e.toString());
        }
        client.closeClient();
    }
    @Test
    public void testClientSendMessageSTUDENT() {
        ServerSocket testServerSocket = null;
        Socket testSocketServerSide = null;
        Scanner testScanner;
        Client client = null;
        try {
            testServerSocket = new ServerSocket(TEST_PORT);
            testServerSocket.setSoTimeout(100);
            client = new Client(TEST_HOSTNAME, TEST_PORT);
            testSocketServerSide = testServerSocket.accept();
            testScanner = new Scanner(testSocketServerSide.getInputStream());
            int testInt = 111, testInt2 = 40000;
            client.sendMessage(testInt+"    \n\n\n  "+testInt2+" \n");
            assertEquals(testInt, testScanner.nextInt());
            assertEquals(testInt2, testScanner.nextInt());
        } catch (IOException e) {
            fail(e.toString());
        }
 
        try {
            testServerSocket.close();
        } catch (IOException e) {
            fail(e.toString());
        }
        client.closeClient();
    }

 

    @Test
    public void testClientReceiveMessage() {
        ServerSocket testServerSocket = null;
        Socket testSocket = null;
        PrintWriter testWriter;
        Client client = null;
        try {
            testServerSocket = new ServerSocket(TEST_PORT);
            testServerSocket.setSoTimeout(100);
            client = new Client(TEST_HOSTNAME, TEST_PORT);
            testSocket = testServerSocket.accept();
            testWriter = new PrintWriter(
                    testSocket.getOutputStream(), true);
            int testInt = 777;
            testWriter.println(testInt);
            String result = client.receiveString();
            System.out.println(result);
            assertEquals(testInt+"", result);
        } catch (IOException e) {
            fail(e.toString());
        }
 
        try {
            testServerSocket.close();
        } catch (IOException e) {
        	fail(e.toString());
        }
        client.closeClient();
    }
    @Test
    public void testClientReceiveMessageSTUDENT() {
        ServerSocket testServerSocket = null;
        Socket testSocket = null;
        PrintWriter testWriter;
        Client client = null;
        try {
            testServerSocket = new ServerSocket(TEST_PORT);
            testServerSocket.setSoTimeout(100);
            client = new Client(TEST_HOSTNAME, TEST_PORT);
            testSocket = testServerSocket.accept();
            testWriter = new PrintWriter(
                    testSocket.getOutputStream(), true);
            int testInt = 0;
            testWriter.println(testInt);
            String result = client.receiveString();
            System.out.println(result);
            assertEquals(testInt+"", result);
            int testInt2 = 32;
            testWriter.println(testInt2);
            String result2 = client.receiveString();
            System.out.println(result2);
            assertEquals(testInt2+"", result2);
        } catch (IOException e) {
            fail(e.toString());
        }
 
        try {
            testServerSocket.close();
        } catch (IOException e) {
            fail(e.toString());
        }
        client.closeClient();
    }

	@Test
	public void testCloseClient() {
		System.out.println("testCloseClient()");
		ServerSocket testServerSocket = null;
		Client client = null;
		try {
			testServerSocket = new ServerSocket(TEST_PORT);
			testServerSocket.setSoTimeout(100);
			client = new Client(TEST_HOSTNAME, TEST_PORT);
			testServerSocket.accept();
			} catch (IOException e) 
			{
				fail(e.toString());
			}
		
		try {
			testServerSocket.close();
			} catch (IOException e) {
				fail(e.toString());
	        }
		client.closeClient();

		assertEquals(true, client.isSocketClosed());
	}

}
