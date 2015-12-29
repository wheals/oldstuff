
/**
 * @author Shmuale Mark
 * 
 * Implements the ServiceOrderInterface interface.
 */
public class ServiceOrder implements ServiceOrderInterface
{
    private int id;
    private String oil_change, safety_check, emissions_check;
    private Vehicle vehicle;
    
    /**
     * Make a new service order.
     * 
     * @param orderNum The ID of this order
     * @param vehicle The Vehicle being serviced
     * @param oil_change_ if "yes", change the oil
     * @param safety_check_ if "yes", check for safety
     * @param emissions_check_ if "yes", check the emissions
     */
    public ServiceOrder(int orderNum, Vehicle vehicle_, String oil_change_, String safety_check_, String emissions_check_)
    {
        id = orderNum;
        vehicle = vehicle_;
        oil_change = oil_change_;
        safety_check = safety_check_;
        emissions_check = emissions_check_;
    }
                        
    public ServiceOrder(int orderNum, String owner, String make, String model, String oil_change_, String safety_check_, String emissions_check_)
    {
        this(orderNum, new Vehicle(owner, make, model), oil_change_, safety_check_, emissions_check_);
    }
    
    public ServiceOrder(ServiceOrder otherOrder)
    {
        this(otherOrder.id, otherOrder.vehicle,
             otherOrder.oil_change, otherOrder.safety_check, otherOrder.emissions_check);
    }
    
    /**
     * Returns the order number of the ServiceOrder
     * @return the order number of the ServiceOrder
     */
    @Override
    public int getOrderNum()
    {
        return id;
    }

    /**
     * Is an oil change requested? 
     * @return "yes" if so, otherwise "no"
     */
    @Override
    public String getOil()
    {
        return oil_change;
    }

    /**
     * Is a safety check requested? 
     * @return "yes" if so, otherwise "no"
     */
    @Override
    public String getSafety()
    {
        return safety_check;
    }

    /**
     * Is an emissions test requested? 
     * @return "yes" if so, otherwise "no"
     */
    @Override
    public String getEmissions()
    {
        return emissions_check;
    }

    /**
     * Returns reference to Vehicle object
     * @return reference to Vehicle object of ServiceOrder
     */
    @Override
    public Vehicle getVehicle()
    {
        return vehicle;
    }
    /**
     * Is this object equal? Always false for non-ServiceOrders.
     * @return whether o has the same id as this.
     */
    @Override
    public boolean equals(Object o)
    {
        return o instanceof ServiceOrder && ((ServiceOrder)o).id == id;
    }
}
