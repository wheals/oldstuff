import java.util.Vector;
import java.util.LinkedList;

/**
 * @author Shmuale Mark
 *
 *         Implements ServiceOrderManagerInterface.
 */
public class ServiceOrdersManager implements ServiceOrdersManagerInterface
{
    @SuppressWarnings("serial")
    private LinkedList<ServiceOrder> ownerSortedList = new LinkedList<ServiceOrder>()
    {
        /**
         * Adds a new ServiceOrder at the correct spot, sorting by the owner's
         * name.
         * @param order a new order to add
         */
        @Override
        public boolean add(ServiceOrder order)
        {
            int i = 0;
            for (; i < size(); ++i)
                if (get(i).getVehicle().getOwner()
                        .compareTo(order.getVehicle().getOwner()) >= 0)
                {
                    break;
                }
            add(i, order);
            return true;
        }
    };

    @SuppressWarnings("serial")
    private LinkedList<ServiceOrder> makemodelSortedList = new LinkedList<ServiceOrder>()
    {
        /**
         * Adds a new ServiceOrder at the correct spot, with make as the primary
         * sort key and model as the secondary one.
         * @param order a new order to add
         */
        @Override
        public boolean add(ServiceOrder order)
        {
            int i = 0;
            for (; i < size(); ++i)
                if (get(i).getVehicle().getMake()
                        .compareTo(order.getVehicle().getMake()) >= 0)
                {
                    break;
                }
            for (; i < size(); ++i)
                if (get(i).getVehicle().getModel()
                        .compareTo(order.getVehicle().getModel()) >= 0
                    || get(i).getVehicle().getMake()
                        .compareTo(order.getVehicle().getMake()) != 0)
                {
                    break;
                }
            add(i, order);
            return true;
        }
    };

    @SuppressWarnings("serial")
    private LinkedList<ServiceOrder> idSortedList = new LinkedList<ServiceOrder>()
    {
        /**
         * Adds a new ServiceOrder at the correct spot, with make as the primary
         * sort key and model as the secondary one.
         * @param order a new order to add
         */
        @Override
        public boolean add(ServiceOrder order)
        {
            int i = 0;
            for (; i < size(); ++i)
                if (get(i).getOrderNum() >= order.getOrderNum())
                    break;
            add(i, order);
            return true;
        }
    };

    /**
     * @see ServiceOrdersManagerInterface#listByKeyTable(int)
     */
    @Override
    public String[][] listByKeyTable(int key)
    {
        final int NUM_COLUMNS = 7;
        LinkedList<ServiceOrder> list = idSortedList;
        if (key == 2)
            list = ownerSortedList;
        if (key == 3)
            list = makemodelSortedList;
        int num_orders = list.size();
        String ret[][] = new String[num_orders][NUM_COLUMNS];
        for (int i = 0; i < num_orders; ++i)
        {
            if (key == 1)
            {
                ret[i][0] = Integer.toString(list.get(i).getOrderNum());
                ret[i][1] = list.get(i).getVehicle().getOwner();
                ret[i][2] = list.get(i).getVehicle().getMake();
                ret[i][3] = list.get(i).getVehicle().getModel();
            }
            else if (key == 2)
            {
                ret[i][0] = list.get(i).getVehicle().getOwner();
                ret[i][1] = Integer.toString(list.get(i).getOrderNum());
                ret[i][2] = list.get(i).getVehicle().getMake();
                ret[i][3] = list.get(i).getVehicle().getModel();
            }
            else if (key == 3)
            {
                ret[i][0] = list.get(i).getVehicle().getMake();
                ret[i][1] = list.get(i).getVehicle().getModel();
                ret[i][2] = list.get(i).getVehicle().getOwner();
                ret[i][3] = Integer.toString(list.get(i).getOrderNum());
            }
            ret[i][4] = list.get(i).getOil();
            ret[i][5] = list.get(i).getSafety();
            ret[i][6] = list.get(i).getEmissions();
        }
        return ret;
    }

    /**
     * @see ServiceOrdersManagerInterface#listByKeyVector(int)
     */
    @Override
    public Vector<String> listByKeyVector(int key)
    {
        LinkedList<ServiceOrder> list = idSortedList;
        if (key == 2)
            list = ownerSortedList;
        if (key == 3)
            list = makemodelSortedList;
        Vector<String> vec = new Vector<String>(list.size());
        for (int i = 0; i < list.size(); ++i)
        {
            String ret = "";
            if (key == 1)
            {
                ret += Integer.toString(list.get(i).getOrderNum()) + " ";
                ret += list.get(i).getVehicle().getOwner() + " ";
                ret += list.get(i).getVehicle().getMake() + " ";
                ret += list.get(i).getVehicle().getModel() + " ";
            }
            else if (key == 2)
            {
                ret += list.get(i).getVehicle().getOwner() + " ";
                ret += list.get(i).getVehicle().getMake() + " ";
                ret += list.get(i).getVehicle().getModel() + " ";
                ret += Integer.toString(list.get(i).getOrderNum()) + " ";
            }
            else if (key == 3)
            {
                ret += list.get(i).getVehicle().getMake() + " ";
                ret += list.get(i).getVehicle().getModel() + " ";
                ret += list.get(i).getVehicle().getOwner() + " ";
                ret += Integer.toString(list.get(i).getOrderNum()) + " ";
            }
            ret += list.get(i).getOil() + " ";
            ret += list.get(i).getSafety() + " ";
            ret += list.get(i).getEmissions();
            vec.add(ret);
        }
        return vec;
    }

    /**
     * @see ServiceOrdersManagerInterface#findOrderNum(int)
     */
    @Override
    public ServiceOrder findOrderNum(int orderNum)
    {
        for (ServiceOrder order : idSortedList)
            if (order.getOrderNum() == orderNum)
                return order;
        return null;
    }

    /**
     * @see ServiceOrdersManagerInterface#startService(int, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public boolean startService(int orderNum, String owner, String make,
            String model, String oilChange, String safetyCheck,
            String emissionsCheck) throws ServiceException
    {
        return startService(new ServiceOrder(orderNum, owner, make, model, oilChange, safetyCheck, emissionsCheck));
    }

    /**
     * @see ServiceOrdersManagerInterface#startService(ServiceOrder)
     */
    @Override
    public boolean startService(ServiceOrder order) throws ServiceException
    {
        // They all have the same info, so it doesn't really matter which
        for (ServiceOrder tmp : idSortedList)
            if (tmp.getOrderNum() == order.getOrderNum())
                throw new ServiceException(Integer.toString(order.getOrderNum()));
        idSortedList.add(order); 
        ownerSortedList.add(order);
        makemodelSortedList.add(order);
        return true;
    }

    /**
     * @see ServiceOrdersManagerInterface#finishService(int)
     */
    @Override
    public boolean finishService(int orderNum) throws ServiceException
    {
        //relies on ServiceOrder.equals() only checking the id
        ServiceOrder dummy = new ServiceOrder(orderNum, "", "", "", "", "", "");
        boolean all_had = ownerSortedList.remove(dummy)
                          && makemodelSortedList.remove(dummy)
                          && idSortedList.remove(dummy);
        if (!all_had)
            throw new ServiceException("Order with specified # does not exist");
        return true;
    }
    
    /**
     * @see ServiceOrdersManagerInterface#toString()
     */
    @Override
    public String toString()
    {
        String ret = "";
        for (ServiceOrder order : idSortedList)
        {
            ret += order.getOrderNum() + "\n";
            ret += order.getVehicle().getOwner() + "\n";
            ret += order.getVehicle().getMake() + "\n";
            ret += order.getVehicle().getModel() + "\n";
            ret += order.getOil() + " " + order.getSafety() + " " + order.getEmissions();
            ret += "\n";
        }
        return ret;
    }
}
