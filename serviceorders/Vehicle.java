
/**
 * @author Shmuale Mark
 * 
 * A bundle of data representing a vehicle.
 */
public class Vehicle
{
    private String owner, make, model;
    /**
     * Makes a new vehicle with the given information.
     * 
     * @param owner_ The name of the owner.
     * @param make_ The make of the vehicle.
     * @param model_ The model of the vehicle.
     */
    public Vehicle(String owner_, String make_, String model_)
    {
        owner = owner_; make = make_; model = model_;
    }
    
    /**
     * @return this vehicle's model
     */
    public String getModel()
    {
        return model;
    }

    /**
     * @return this vehicle's make
     */
    public String getMake()
    {
        return make;
    }

    /**
     * @return this vehicle's owner's name
     */
    public String getOwner()
    {
        return owner;
    }
}
