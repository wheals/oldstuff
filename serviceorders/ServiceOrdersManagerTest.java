import static org.junit.Assert.*;

import java.util.Scanner;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ServiceOrdersManagerTest {
	private ServiceOrder order1, order2, order3, order4, order5, order6, order7, order8, order9;
	private ServiceOrdersManager orders;
	private final int DISPLAY_TYPE = 2;  //choose 0 to test JTable, 1 for JList, 2 for both
	
	@Before
	public void setUp() throws Exception {
		order1 = new ServiceOrder(1, "Smith, Joe","Honda","Accord","yes","no","yes");
		order2 = new ServiceOrder(2, "Jones, Erica","Ford","Focus","yes","yes","no");
		order3 = new ServiceOrder(3, "Wilson, George","Chevrolet","Volt","no","no","yes");
		order4 = new ServiceOrder(4, "Williams, June","Nissan","Sentra","yes","yes","no");
		order5 = new ServiceOrder(5, "Majors, James","Honda","Civic","yes","no","no");
		order6 = new ServiceOrder(6, "Clancy, Robert","Volvo","S40","no","yes","yes");
		order7 = new ServiceOrder(7, "Johnson, Danny","Buick","Regal","yes","no","yes");
		order8 = new ServiceOrder(8, "Patterson, Jimmy","Chevrolet","Malibu","no","yes","yes");
		order9 = new ServiceOrder(9, "Matson, Randall","Hyundai","Sonata","yes","no","no");
		
		orders = new ServiceOrdersManager();
		orders.startService(order1);
		orders.startService(order2);
		orders.startService(order3);
	}

	@After
	public void tearDown() throws Exception {
		order1=order2=order3=order4=order5=order6=order7=order8=order9=null;
		orders=null;
	}

	@Test
	public void testStartServiceIntStringStringStringStringStringString() {
		boolean raised = false;
		String[][] table;
		Vector<String> vList;
		if (DISPLAY_TYPE==0 || DISPLAY_TYPE==2) {
			//verify initial contents of orders in order by owner
			table = orders.listByKeyTable(2);
			assertEquals("Jones, Erica", table[0][0]);
			assertEquals("Smith, Joe", table[1][0]);
			assertEquals("Wilson, George", table[2][0]);
		}
		if (DISPLAY_TYPE==1 || DISPLAY_TYPE==2) {
			
			//verify initial contents of orders in order by owner
			vList = orders.listByKeyVector(2);
			assertEquals("Jones, Erica Ford Focus 2 yes yes no", vList.get(0));
			assertEquals("Smith, Joe Honda Accord 1 yes no yes", vList.get(1));
			assertEquals("Wilson, George Chevrolet Volt 3 no no yes", vList.get(2));
		  }
		try{
			//add service order
			orders.startService(5,"Lambert, Leon","Dodge", "Dart", "no", "yes", "yes");
		}
		catch (Exception e)
		{
			fail("This should not have raised an exception");
		}
		if (DISPLAY_TYPE==0 || DISPLAY_TYPE==2) {
			//verify modified contents of orders in order by owner
			table = orders.listByKeyTable(2);
			assertEquals("Jones, Erica", table[0][0]);
			assertEquals("Lambert, Leon", table[1][0]);
			assertEquals("Smith, Joe", table[2][0]);
			assertEquals("Wilson, George", table[3][0]);
		}
		if (DISPLAY_TYPE==1 || DISPLAY_TYPE==2) {
			//verify modified contents of orders in order by owner
			vList = orders.listByKeyVector(2);
			assertEquals("Jones, Erica Ford Focus 2 yes yes no", vList.get(0));
			assertEquals("Lambert, Leon Dodge Dart 5 no yes yes", vList.get(1));
			assertEquals("Smith, Joe Honda Accord 1 yes no yes", vList.get(2));
			assertEquals("Wilson, George Chevrolet Volt 3 no no yes", vList.get(3));
		}

		//Add an order with an order # already in use, should raise an exception
		try{
			//add service order
			orders.startService(3,"Lambert, Leon","Dodge", "Dakota", "no", "yes", "yes");
		}
		catch (Exception e)
		{
			raised = true;
		}
		if(!raised)
			fail("This should have raised an exception");
	}

	@SuppressWarnings("unused")
	@Test
	public void testStartServiceServiceOrder() {
		boolean raised = false;
		String[][] table;
		Vector<String> vList;
		
		  if (DISPLAY_TYPE==0 || DISPLAY_TYPE==2) {
			//verify initial contents of orders in order by owner
			table = orders.listByKeyTable(2);
			assertEquals("Jones, Erica", table[0][0]);
			assertEquals("Smith, Joe", table[1][0]);
			assertEquals("Wilson, George", table[2][0]);
		  }
		  if (DISPLAY_TYPE==1 || DISPLAY_TYPE==2) {
				
			//verify initial contents of orders in order by owner
			vList = orders.listByKeyVector(2);
			assertEquals("Jones, Erica Ford Focus 2 yes yes no", vList.get(0));
			assertEquals("Smith, Joe Honda Accord 1 yes no yes", vList.get(1));
			assertEquals("Wilson, George Chevrolet Volt 3 no no yes", vList.get(2));
		  }
		  try{
			//add service order
			orders.startService(order4);
			}
		  catch (Exception e)
			{
				fail("This should not have raised an exception");
			}
		  if (DISPLAY_TYPE==0 || DISPLAY_TYPE==2) {
			  //verify modified contents of orders in order by owner
			  table = orders.listByKeyTable(2);
			  assertEquals("Jones, Erica", table[0][0]);
			  assertEquals("Smith, Joe", table[1][0]);
			  assertEquals("Williams, June", table[2][0]);
			  assertEquals("Wilson, George", table[3][0]);
		  }
		  if (DISPLAY_TYPE==1 || DISPLAY_TYPE==2) {
			//verify modified contents of orders in order by owner
			vList = orders.listByKeyVector(2);
			assertEquals("Jones, Erica Ford Focus 2 yes yes no", vList.get(0));
			assertEquals("Smith, Joe Honda Accord 1 yes no yes", vList.get(1));
			assertEquals("Williams, June Nissan Sentra 4 yes yes no", vList.get(2));
			assertEquals("Wilson, George Chevrolet Volt 3 no no yes", vList.get(3));
		  }
		//Add an order with an order # already in use, should raise an exception
		try{
			//add service order
			orders.startService(order2);
		}
		catch (Exception e)
		{
			raised = true;
		}
		if(!raised)
			fail("This should have raised an exception");
	}
	
	@Test
	public void testStartServiceOrderSTUDENT() {
		orders = new ServiceOrdersManager();
		try
        {
            orders.startService(order4);
            orders.startService(order5);
            orders.startService(order6);
        } catch (ServiceException e)
        {
            fail("should have been no exception");
        }
        try
        {
            orders.startService(order4);
            fail("should have been an exception");
        } catch (ServiceException e)
        {
        }
	}

	
	@SuppressWarnings("unused")
	@Test
	public void testFinishService() {
		boolean raised = false;
		String[][] table;
		Vector<String> vList;
		if (DISPLAY_TYPE==0 || DISPLAY_TYPE==2) {
			//verify initial contents of orders in order by owner
			table = orders.listByKeyTable(2);
			assertEquals("Jones, Erica", table[0][0]);
			assertEquals("Smith, Joe", table[1][0]);
			assertEquals("Wilson, George", table[2][0]);
		}
	    if (DISPLAY_TYPE==1 || DISPLAY_TYPE==2) {
			//verify initial contents of orders in order by owner
			vList = orders.listByKeyVector(2);
			assertEquals("Jones, Erica Ford Focus 2 yes yes no", vList.get(0));
			assertEquals("Smith, Joe Honda Accord 1 yes no yes", vList.get(1));
			assertEquals("Wilson, George Chevrolet Volt 3 no no yes", vList.get(2));
	    }
	    
		try{
			//delete service order
			orders.finishService(1);
		}
		catch (Exception e)
		{
			fail("This should not have raised an exception");
		}
		if (DISPLAY_TYPE==0 || DISPLAY_TYPE==2) {
			//verify modified contents of orders in order by owner
			table = orders.listByKeyTable(2);
			assertEquals("Jones, Erica", table[0][0]);
			assertEquals("Wilson, George", table[1][0]);
		}
		if (DISPLAY_TYPE==1 || DISPLAY_TYPE==2) {
			//verify modified contents of orders in order by owner
			vList = orders.listByKeyVector(2);
			assertEquals("Jones, Erica Ford Focus 2 yes yes no", vList.get(0));
			assertEquals("Wilson, George Chevrolet Volt 3 no no yes", vList.get(1));
		}

		//try to delete an order with invalid order #, should raise an exception
		try{
			//add service order
			orders.finishService(5);
		}
		catch (Exception e)
		{
			raised = true;
		}
		if(!raised)
			fail("This should have raised an exception");
	  }

	
	@Test
	public void testFinishServiceSTUDENT() {
	    orders = new ServiceOrdersManager();
        try
        {
            orders.startService(order7);
            orders.startService(order8);
            orders.startService(order9);
        } catch (ServiceException e)
        {
            fail("should have been no exception");
        }
        try
        {
            orders.finishService(7);
            orders.finishService(8);
        } catch (ServiceException e)
        {
            fail("should have been no exception");
        }
        try
        {
            orders.finishService(7);
            orders.finishService(10);
            fail("should have been an exception");
        } catch (ServiceException e)
        {
        }
	}

	@SuppressWarnings("unused")
	@Test
	public void testListByKeyTable() {
		if (DISPLAY_TYPE==0 || DISPLAY_TYPE==2) {
		// test list by order #
			String[][] table = orders.listByKeyTable(1);
			assertEquals("Smith, Joe", table[0][1]);
			assertEquals("Jones, Erica", table[1][1]);
			assertEquals("Wilson, George", table[2][1]);
			// check other values
			assertEquals("Honda", table[0][2]);
			assertEquals("Focus", table[1][3]);
			assertEquals("yes", table[2][6]);
		
		// test list by owner
			table = orders.listByKeyTable(2);
			assertEquals("Jones, Erica", table[0][0]);
			assertEquals("Smith, Joe", table[1][0]);
			assertEquals("Wilson, George", table[2][0]);
			// check other values
			assertEquals("Ford", table[0][2]);
			assertEquals("Accord", table[1][3]);
			assertEquals("no", table[2][5]);
			
		// test list by Make
			table = orders.listByKeyTable(3);
			assertEquals("Smith, Joe", table[2][2]);
			assertEquals("Jones, Erica", table[1][2]);
			assertEquals("Wilson, George", table[0][2]);
			// check other values
			assertEquals("Chevrolet", table[0][0]);
			assertEquals("2", table[1][3]);
			assertEquals("no", table[2][5]);
		}
		else
		{
			System.out.println("listByKeyTable bypassed");
			assertTrue(true);
		}


	}

	@SuppressWarnings("unused")
	@Test
	public void testListByKeyVector() {
		if (DISPLAY_TYPE==1 || DISPLAY_TYPE==2) {
			// test list by order #
			Vector<String> table = orders.listByKeyVector(1);
			assertEquals("1 Smith, Joe Honda Accord yes no yes", table.get(0));
			assertEquals("2 Jones, Erica Ford Focus yes yes no", table.get(1));
			assertEquals("3 Wilson, George Chevrolet Volt no no yes", table.get(2));
		
		// test list by owner
			table = orders.listByKeyVector(2);
			assertEquals("Jones, Erica Ford Focus 2 yes yes no", table.get(0));
			assertEquals("Smith, Joe Honda Accord 1 yes no yes", table.get(1));
			assertEquals("Wilson, George Chevrolet Volt 3 no no yes", table.get(2));
			
		// test list by vehicle
			table = orders.listByKeyVector(3);
			assertEquals("Chevrolet Volt Wilson, George 3 no no yes", table.get(0));
			assertEquals("Ford Focus Jones, Erica 2 yes yes no", table.get(1));
			assertEquals("Honda Accord Smith, Joe 1 yes no yes", table.get(2));
			
		}
		else
		{
			System.out.println("listByKeyVector bypassed");
			assert(true);
		}
	}

	@Test
	public void testFindOrderNum() {
		assertTrue(orders.findOrderNum(1)!= null);
		ServiceOrder s1 = orders.findOrderNum(1);
		assertEquals(s1.getVehicle().getModel(),"Accord");
		assertTrue(orders.findOrderNum(5)==null);
	}

	@Test
	public void testToString() {
		String output = orders.toString();
		Scanner scan = new Scanner(output);
		assertEquals("1", scan.next());
		scan.nextLine(); // get newline
		assertEquals("Smith, Joe", scan.nextLine());
		assertEquals("Honda", scan.nextLine());
		scan.nextLine(); // Accord
		scan.nextLine(); // yes no yes
		assertEquals("2", scan.next());
		scan.nextLine(); // get newline
		assertEquals("Jones, Erica", scan.nextLine());
		scan.nextLine(); // Ford
		assertEquals("Focus", scan.nextLine());
		scan.nextLine(); // yes yes no
		assertEquals("3", scan.next());
		scan.nextLine(); // get newline
		assertEquals("Wilson, George", scan.nextLine());
		scan.nextLine(); // Chevrolet
		scan.nextLine(); // Volt
		assertEquals("no no yes", scan.nextLine());
		
		
	}

}
