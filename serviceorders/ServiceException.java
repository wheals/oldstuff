
/**
 * @author Shmuale Mark
 *
 * An exception raised if no order with an id is present, or an order
 * with a duplicate id is created.
 */
@SuppressWarnings("serial")
public class ServiceException extends Exception
{
    public ServiceException(String string) { super(string); }
}
