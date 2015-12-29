import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * @author Shmuale Mark
 * A panel for the interface for the auto shop app.
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel
{
    private JTextField orderNum = new JTextField(20);
    private JTextField orderOwner = new JTextField(20);
    private JTextField orderMake = new JTextField(20);
    private JTextField orderModel = new JTextField(20);

    private JCheckBox orderOilChange = new JCheckBox("Oil Change");
    private JCheckBox orderSafetyCheck = new JCheckBox("Safety Check");
    private JCheckBox orderEmissionsCheck = new JCheckBox("Emissions Check");
    
    private JRadioButton sortOwner = new JRadioButton("Owner", true);
    private JRadioButton sortNum = new JRadioButton("Order #");
    private JRadioButton sortMakeModel = new JRadioButton("Make/Model");
    
    private JList<String> orderList = new JList<String>();

    private ServiceOrdersManager manager = new ServiceOrdersManager();

    public MainPanel()
    {
        // Two columns
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel topRow = new JPanel();
        JPanel bottomRow = new JPanel();
        
        // First component (0, 0): information
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 1));
        infoPanel.add(new JLabel("Cars R Us Auto Shop"));
        infoPanel.add(new JLabel("5100 Manakee Street"));
        infoPanel.add(new JLabel("Rockville, MD 20850"));
           
        // Second component (1, 0): Action buttons
        JPanel actionPanel = new JPanel();
        JButton startButton = new JButton("Start Service");
        startButton.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent e) { startService(); } });
        actionPanel.add(startButton);
        JButton finishButton = new JButton("Finish Service");
        finishButton.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent e) { finishService(); } });
        actionPanel.add(finishButton);
        JButton readFileButton = new JButton("Read File");
        readFileButton.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent e) { readFile(); } });
        actionPanel.add(readFileButton);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent e) { System.exit(0); } });
        actionPanel.add(exitButton);
        
        topRow.add(infoPanel);
        topRow.add(actionPanel);
        add(topRow);
        
        // Third component (0, 1): display sorting radio buttons + order list
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        // First subcomponent: sorting radio buttons
        JPanel sortPanel = new JPanel();
        sortPanel.setBorder(BorderFactory.createTitledBorder("Sort display by:"));
        ButtonGroup sortGroup = new ButtonGroup();
        sortOwner.addActionListener(new updateOrders()); sortGroup.add(sortOwner); sortPanel.add(sortOwner);
        sortNum.addActionListener(new updateOrders()); sortGroup.add(sortNum); sortPanel.add(sortNum);
        sortMakeModel.addActionListener(new updateOrders()); sortGroup.add(sortMakeModel); sortPanel.add(sortMakeModel);
        sortPanel.setPreferredSize(new Dimension(100, 10));
        displayPanel.add(sortPanel);
        // Second subcomponent: the list
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderList.setVisibleRowCount(-1);
        orderList.setBackground(Color.WHITE);
        orderList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        updateOrderList();
        JScrollPane listScroller = new JScrollPane(orderList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listScroller.setPreferredSize(new Dimension(100, 200));
        listScroller.setBorder(BorderFactory.createTitledBorder("Service Order List"));
        displayPanel.add(listScroller);
        displayPanel.setPreferredSize(new Dimension(300, 300));

        // Fourth component (1, 1): Service order details location
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new GridLayout(0, 1));
        orderPanel.setBorder(BorderFactory.createTitledBorder("Service Order Details"));
        // First subcomponent: id field
        JPanel orderNumPanel = new JPanel();
        orderNumPanel.setBorder(BorderFactory.createTitledBorder("Order #"));
        orderNumPanel.add(orderNum);
        orderPanel.add(orderNumPanel);
        // Second subcomponent: owner field
        JPanel orderOwnerPanel = new JPanel();
        orderOwnerPanel.setBorder(BorderFactory.createTitledBorder("Owner"));
        orderOwnerPanel.add(orderOwner);
        orderPanel.add(orderOwnerPanel);
        // Third subcomponent: make field
        JPanel orderMakePanel = new JPanel();
        orderMakePanel.setBorder(BorderFactory.createTitledBorder("Make"));
        orderMakePanel.add(orderMake);
        orderPanel.add(orderMakePanel);
        // Fourth subcomponent: model field
        JPanel orderModelPanel = new JPanel();
        orderModelPanel.setBorder(BorderFactory.createTitledBorder("Model"));
        orderModelPanel.add(orderModel);
        orderPanel.add(orderModelPanel);
        // Fifth subcomponent: panel with check boxes
        JPanel checkPanel = new JPanel();
        checkPanel.setBorder(BorderFactory.createTitledBorder("Service Options"));
        checkPanel.add(orderOilChange);
        checkPanel.add(orderSafetyCheck);
        checkPanel.add(orderEmissionsCheck);
        orderPanel.add(checkPanel);

        bottomRow.add(displayPanel);
        bottomRow.add(orderPanel);
        add(bottomRow);
    }

    /**
     * Start a service order according to the data in the text fields. 
     */
    private void startService()
    {
        try
        {
            manager.startService(Integer.parseInt(orderNum.getText()), 
                                 orderOwner.getText(),
                                 orderMake.getText(),
                                 orderModel.getText(),
                                 orderOilChange.isSelected() ? "yes" : "no",
                                 orderSafetyCheck.isSelected() ? "yes" : "no",
                                 orderEmissionsCheck.isSelected() ? "yes" : "no");
        } catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(getParent(),
                    "\"" + orderNum.getText() + "\"" + " is not a valid order number.",
                    "Invalid Order #",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException e)
        {
            JOptionPane.showMessageDialog(getParent(),
                    "Order number " + orderNum.getText() + " is already in use.",
                    "Invalid Order #",
                    JOptionPane.ERROR_MESSAGE);
        }
        orderNum.setText(null);
        orderOwner.setText(null);
        orderMake.setText(null);
        orderModel.setText(null);
        updateOrderList();
    }

    /**
     * Finish a service order with the indicated order number.
     */
    private void finishService()
    {
        try
        {
            manager.finishService(Integer.parseInt(orderNum.getText()));
        } catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(getParent(),
                    "\"" + orderNum.getText() + "\" is not a valid order number.",
                    "Invalid Order #",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException e)
        {
            JOptionPane.showMessageDialog(getParent(),
                    "No order with number \"" + orderNum.getText() + "\" exists.",
                    "Invalid Order #",
                    JOptionPane.ERROR_MESSAGE);
        }
        orderNum.setText(null);
        updateOrderList();
    }

    /**
     * 
     */
    protected void readFile()
    {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File f = fc.getSelectedFile();
            Scanner sc = null;
            try
            {
                sc = new Scanner(f);
                while (sc.hasNext())
                {
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine());
                    }
                    catch (NumberFormatException e)
                    {
                        continue;
                    }
                    String owner = sc.nextLine();
                    String make = sc.nextLine();
                    String model = sc.nextLine();
                    String oil = sc.next();
                    String safety = sc.next();
                    String emissions = sc.next();
                    manager.startService(id, owner, make, model, oil, safety, emissions);         
                }
                JOptionPane.showMessageDialog(null, "Service orders added successfully!");
            } catch (FileNotFoundException e1)
            {
                JOptionPane.showMessageDialog(null, "Error opening file.", "Bad File", JOptionPane.ERROR_MESSAGE);
            } catch (ServiceException e)
            {
                JOptionPane.showMessageDialog(null, "\"" + e.getMessage() + "\" is already in use as an order number.", "Invalid Order #", JOptionPane.ERROR_MESSAGE);
            } finally 
            {
                if (sc != null)
                    sc.close();
            }
        }
        updateOrderList();
    }

    /**
     * Update the list of orders, according to the selected radio button.
     */
    private void updateOrderList()
    {
        if (sortOwner.isSelected())
            orderList.setListData(manager.listByKeyVector(2));
        else if (sortMakeModel.isSelected())
            orderList.setListData(manager.listByKeyVector(3));
        else if (sortNum.isSelected())
            orderList.setListData(manager.listByKeyVector(1));
    }
    
    private class updateOrders implements ActionListener
    {
        /**
         * When clicked, update the list of orders.
         */
        public void actionPerformed(ActionEvent e)
        { updateOrderList(); }
    }
}
