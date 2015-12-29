import java.util.Vector;

/**
 * This class that holds all the service order objects in the ordered linked-lists that 
 * keep the objects ordered based on a particular key. This class adds to the data structures 
 * (Start Service) and deletes from the data structures (Finish Service).  
 * It also returns the contents of the ordered linked lists.  
 * 
 * Used for Assignment 2 Spring 2015
 * 
 * @author original version by Professor Karchner
 *
 */
public interface ServiceOrdersManagerInterface{
	
	/**
	 * Creates a Service Order object and adds it to the ordered linked lists
	 * @param orderNum Service Order Number
	 * @param owner name of owner of car to be serviced
	 * @param make make of car to be serviced
	 * @param model model of car to be serviced
	 * @param year year of car to be serviced
	 * @return true if the startService was successful
	 * @throws ServiceOrderInUseException if Service Order Number is already in use
	 */
	public boolean startService(int orderNum, String owner, String make, String model, String oilChange, String safetyCheck, String emissionsCheck) throws ServiceException;
	
	
	/**
	 * Adds a Service Order to the ordered linked lists.  This is for testing purposes.  
	 * Not intended to be used by the user
	 * @param order a Service Order to be added to the ordered linked lists.
	 * @return true if the startService was successful
	 * @throws ServiceOrderInUseException if Service Order Number is already in use
	 */
	public boolean startService(ServiceOrder order) throws ServiceException;
	
	/**
	 * Removes the Service Order from the ordered linked lists
	 * @param orderNum Service Order Number
	 * @return true if the finishService is successful
	 * @throws ServiceOrderNotFoundException if the Service Order Number is not found
	 */
	
	public boolean finishService(int orderNum) throws ServiceException;

	/**
	 * Returns a two dimensional array of Strings to populate a JTable
	 * @param key, how service orders are to be ordered 1 = service order number, 2 = owner name (last, first), 
	 * 3 = model
	 * @return a two dimensional array of Strings to populate a JTable
	 * order for key 1 = order num, owner, make, model, oilChange, safetyCheck, emissionsCheck
	 * order for key 2 = owner, order num, make, model, oilChange, safetyCheck, emissionsCheck
	 * order for key 3 = model, owner, make, order num, oilChange, safetyCheck, emissionsCheck
	 */
	public String[][] listByKeyTable(int key);
	
	/**
	 * Returns a Vector of Strings to populate a JList
	 * @param key how service orders are to be ordered 1 = service order number, 2 = owner name (last, first), 
	 * 3 = model
	 * @return a Vector of Strings to populate a JList
	 * order for key 1 = order num, owner, make, model, oilChange, safetyCheck, emissionsCheck
	 * order for key 2 = owner, order num, make, model, oilChange, safetyCheck, emissionsCheck
	 * order for key 3 = model, owner, make, order num, oilChange, safetyCheck, emissionsCheck
	 */
	public Vector<String> listByKeyVector(int key);
	
	/**
	 * Returns the reference to the order number if found or null if not found
	 * @param orderNum the order number to look for
	 * @return the reference to the service order object or null if not found
	 */
	
	public ServiceOrder findOrderNum(int orderNum);

	/**
	 * Returns a string with all the current Service Orders in the following format:
	 * 
	 *  Line	Information
	 *  1		Order #  (integer)
	 *  2		Owner (last name, first name) (String)
	 *  3		make (String)
	 *  4		model (String)
	 *  5		oilChange (String) either "yes" or "no"
	 *  6		safetyCheck (String) either "yes" or "no"
	 *  7		emissionsCheck (String) either "yes" or "no"
	 *  ** No blank lines between Service Orders

	 * @return a String that can be used with a PrintWriter object to write to a file.
	 */
	public String toString();

}
