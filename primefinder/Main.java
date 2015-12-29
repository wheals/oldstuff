import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Shmuale Mark
 * 
 *         A GUI wrapping a PrimeFinderManager and a Server.
 */
@SuppressWarnings("serial")
public class Main extends JFrame
{
    private static final int PORT = 4609;

    Server server = null;
    PrimeFinderManager mgr;

    public Main()
    {
        super();
        mgr = new PrimeFinderManager();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // top third: input area
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory
                .createTitledBorder("Enter Numbers to Check for Primality:"));
        JTextField primeField = new JTextField();
        primeField.setPreferredSize(new Dimension(500, 25));
        topPanel.add(primeField);
        JButton checkButton = new JButton("Check for Primality");
        checkButton.setEnabled(false);
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    PrimeFinderManager.primes.clear();
                    mgr.runPrimeFinder("localhost", PORT, primeField.getText());
                } catch (NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(null,
                            "Input must be in the form of space-separated integers.",
                            "Invalid Input Format", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        topPanel.add(checkButton);
        mainPanel.add(topPanel);

        // middle third: prime display
        JPanel middlePanel = new JPanel();
        JLabel primeList = new JLabel();
        middlePanel.add(primeList);
        middlePanel.setPreferredSize(new Dimension(0, 100));
        mainPanel.add(middlePanel);

        // bottom third: buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEtchedBorder());

        JButton startButton = new JButton("Start Server");
        JButton displayButton = new JButton("Display Primes");
        JButton writeButton = new JButton("Write Primes");
        JButton exitButton = new JButton("Exit");

        displayButton.setEnabled(false);
        writeButton.setEnabled(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                server = new Server(PORT);
                new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
                        server.startServer(PrimeFinderManager.CLIENT_INPUT_DONE);
                    }
                }).start();
                displayButton.setEnabled(true);
                writeButton.setEnabled(true);
                checkButton.setEnabled(true);
                startButton.setEnabled(false);
            }
        });
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                primeList.setText("Primes Found: " + mgr.getPrimeList().toString());
            }
        });
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser cf = new JFileChooser();
                cf.setDialogTitle("Choose prime output file");
                cf.showOpenDialog(null);
                File out = cf.getSelectedFile();
                mgr.writePrimes(out);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (server != null)
                    server.stopServer();
                if (mgr.client != null)
                    mgr.client.closeClient();
                System.exit(0);
            }
        });
        bottomPanel.add(startButton);
        bottomPanel.add(displayButton);
        bottomPanel.add(writeButton);
        bottomPanel.add(exitButton);
        mainPanel.add(bottomPanel);

        add(mainPanel);

        pack();
    }

    public static void main(String[] args)
    {
        Main main = new Main();
        main.setVisible(true);
    }

}
