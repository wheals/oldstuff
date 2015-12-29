

/**
 * Interface for Class which contains all the information for a Service Order
 * 
 * Used for Assignment 2 - Fall 2013
 * 
 * @author Professor Myers
 * 
 */
public interface ServiceOrderInterface
{

   
   /**
    * Constructors
    * 
    * public ServiceOrder(int orderNum, Dog dog, String shampoo, String cut, String nailTrim)
    * public ServiceOrder(int orderNum, String owner, String name, String breed, String shampoo, String cut, String nailTrim)
    * public ServiceOrder(ServiceOrder otherDog)
   */
   
	/**
	 * Returns reference to Dog object
	 * @return reference to Dog object of Service Order
	 */
   public Vehicle getVehicle();
   
   /**
    * Returns the order number of the Service Order
    * @return the order number of the Service Order
    */
   public int getOrderNum();
  
   /**
    * Returns if shampoo service is requested 
    * @return yes or no if shampoo service is requested
    */
   public String getOil();
   
   /**
    * Returns if cut service is requested
    * @return yes or no if cut service is requested
    */
   public String getSafety();
   
   /**
    * Returns if nail trim is requested
    * @return yes or no if nail trim is requested
    */
   public String getEmissions();
}