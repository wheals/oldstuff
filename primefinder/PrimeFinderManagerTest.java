import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class PrimeFinderManagerTest {
	static final String HOSTNAME = "localhost";
	static final int PORT = 5550;
	Server server;
	PrimeFinderManager mgr;
	 
	@Test
	public void testRunPrimeFinder() {
		 mgr = new PrimeFinderManager();
		 server = new Server( PORT );		
		 try {
             Thread serverThread = new Thread(new Runnable() {
                 @Override
                 public void run() {
                 	server.startServer(PrimeFinderManager.CLIENT_INPUT_DONE);
                 	}
                 });
             serverThread.start();
         } catch (Exception e1) {
             fail(e1.toString());
         }
			
		 //5850 5851 5857 5861 -999 candidate primes
		 mgr.runPrimeFinder(HOSTNAME, PORT, "7 9 5850 5851" + " -999");
		 ArrayList<Integer> primes = mgr.getPrimeList();

		 assertEquals(7,(int) primes.get(0));
		 assertEquals(5851,(int) primes.get(1));
		 
		 mgr.client.closeClient();	 
	     try {
	            server.clientSocket.close();
	        } catch (IOException e) {
	        	fail(e.toString());
	        }
	     mgr = null;
		 server.stopServer();
		 server=null;
		  
	}
	
	@Test
	public void testRunPrimeFinderSTUDENT() {
	    mgr = new PrimeFinderManager();
        server = new Server( PORT );       
        try {
            Thread serverThread = new Thread(new Runnable() {
                @Override
                public void run() {
                   server.startServer(PrimeFinderManager.CLIENT_INPUT_DONE);
                   }
                });
            serverThread.start();
        } catch (Exception e1) {
            fail(e1.toString());
        }
           
        mgr.runPrimeFinder(HOSTNAME, PORT, "2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19" + " -999");
        ArrayList<Integer> primes = mgr.getPrimeList();

        assertTrue(primes.contains(2));
        assertTrue(primes.contains(3));
        assertTrue(primes.contains(5));
        assertTrue(primes.contains(19));
        assertFalse(primes.contains(4));
        assertFalse(primes.contains(20));
        assertFalse(primes.contains(-999));
        
        mgr.client.closeClient();   
        try {
               server.clientSocket.close();
           } catch (IOException e) {
               fail(e.toString());
           }
        mgr = null;
        server.stopServer();
        server=null;
         
	}
	
	@Test
	public void testWritePrimes() {
		FileReader file = null;
		mgr = new PrimeFinderManager();	 
		
		PrimeFinderManager.primes.add(3);
		PrimeFinderManager.primes.add(5);
		PrimeFinderManager.primes.add(7);
		
		mgr.writePrimes(new File("outTest.txt"));
		try {
			file = new FileReader("outTest.txt");
		} catch (FileNotFoundException e) {
			fail(e.toString());
		}
		Scanner reader = new Scanner(file);

		if (reader.hasNext()) assertEquals(3,reader.nextInt());
		if (reader.hasNext()) assertEquals(5,reader.nextInt());
		if (reader.hasNext()) assertEquals(7,reader.nextInt());
		if (reader.hasNext()) fail("too many elements written to Primes file");
		reader.close();

	}
	
	@Test
	public void testWritePrimesSTUDENT() {
	    FileReader file = null;
        mgr = new PrimeFinderManager();  
        
        PrimeFinderManager.primes.add(5561);
        PrimeFinderManager.primes.add(4);
        PrimeFinderManager.primes.add(19);
        
        mgr.writePrimes(new File("outTest.txt"));
        try {
            file = new FileReader("outTest.txt");
        } catch (FileNotFoundException e) {
            fail(e.toString());
        }
        Scanner reader = new Scanner(file);

        if (reader.hasNext()) assertEquals(5561,reader.nextInt());
        if (reader.hasNext()) assertEquals(4,reader.nextInt());
        if (reader.hasNext()) assertEquals(19,reader.nextInt());
        if (reader.hasNext()) fail("too many elements written to Primes file");
        reader.close();
	}
	
}
