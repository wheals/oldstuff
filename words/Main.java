import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * @author Shmuale Mark
 * 
 * Defines the GUI program and an entry point.
 */
@SuppressWarnings("serial")
public class Main extends JFrame
{
    Dictionary dict, greWords;
    File commonFile;
    JTextArea textArea;
    
    /**
     * Set up our main menu
     */
    private Main()
    {
        super();

        // First thing, get the dictionaries
        JFileChooser cf = new JFileChooser();
        cf.setDialogTitle("Choose Common word dictionary file");
        cf.showOpenDialog(null);
        commonFile = cf.getSelectedFile();
        dict = new Dictionary();
        dict.create(commonFile);
        
        cf.setDialogTitle("Choose GRE dictionary file");
        cf.showOpenDialog(null);
        greWords = new Dictionary();
        greWords.create(cf.getSelectedFile());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Spell and GRE checker");
        
        // Contents: a main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // Top row: the text area
        JPanel textPanel = new JPanel();
        textArea = new JTextArea();
        JScrollPane listScroller = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listScroller.setPreferredSize(new Dimension(400,300));
        textPanel.add(listScroller);
        textPanel.setBorder(BorderFactory.createTitledBorder("Text for Spell and GRE Checker"));
        mainPanel.add(textPanel);
        
        // Bottom row: the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        // Button 1: spellcheck
        JButton spellButton = new JButton("Spell Check");
        spellButton.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent e) { spellCheck(); } });
        buttonPanel.add(spellButton);
         // Button 2: GRE check
        JButton greButton = new JButton("GRE Word Check");
        greButton.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent e) { wordCheck(); } });
        buttonPanel.add(greButton);
        // Button 3: text file
        JButton fileButton = new JButton("Read Text File");
        fileButton.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent e) { readTextFile(); } });
        buttonPanel.add(fileButton);
        // Button 4: exit
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent e) { System.exit(0); } });
        buttonPanel.add(exitButton);
        mainPanel.add(buttonPanel);
        
        add(mainPanel);
        pack();
    }
    
    /**
     * 
     */
    protected void wordCheck()
    {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(textArea.getText().split("\\n")));
        DictionaryUtility util = new DictionaryUtility();
        list = util.checkGRE(list, greWords);
        if (list.isEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "All sentences contain a GRE word.",
                    "GRE Check",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            String message = "The following ";
            if (list.size() == 1)
                message += "sentence does";
            else
                message += "sentences do";
            message += " not contain a GRE word:\n\n";
            for (String str : list)
                message += str + '\n';
            JOptionPane.showMessageDialog(null,
                    message,
                    "GRE Check",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     *  Runs a spell check on the text area, and possibly prompts to add words
     *  to the dictionary. 
     */
    private void spellCheck()
    {
        
        String s = textArea.getText();
        s = s.replaceAll("[,.':;\"]", ""); // remove punctuation
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(s.split("[ \\n]")));
        list = dict.checkWords(list);
        if (list.isEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "All words in dictionary.",
                    "Spell Check",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            String words = list.size() == 1 ? "word" : "words";
            String are = list.size() == 1 ? "is" : "are";
            String message = "The following " + words + " " + are + " not in the dictionary:\n\n";
            for (String str : list)
                message += str + '\n';
            message += "\n";
            message += "Would you like to add the " + words + " to the dictionary?";
            int ret = JOptionPane.showConfirmDialog(null,
                                                    message,
                                                    "Spell Check",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.QUESTION_MESSAGE);
            if (ret == JOptionPane.YES_OPTION)
            {
                for (String str : list)
                    dict.add(str);
                dict.printToFile(commonFile);
                JOptionPane.showMessageDialog(null,
                        "Words added successfully.",
                        "Spell Check",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     *  Open a file the user chooses and display it in the text area.
     */
    private void readTextFile()
    {
        JFileChooser cf = new JFileChooser();
        cf.setDialogTitle("Choose Text File");
        cf.showOpenDialog(null);
        Scanner sc = null;
        try
        {
            sc = new Scanner(cf.getSelectedFile());
            String s = "";
            while (sc.hasNextLine())
                s += sc.nextLine() + '\n';
            textArea.setText(s);
        } catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null,
                                          "Error opening file.",
                                          "Bad File",
                                          JOptionPane.ERROR_MESSAGE);
        } finally
        {
            if (sc != null)
                sc.close();
        }
    }

    public static void main(String[] args)
    {
        Main main = new Main();
        main.setVisible(true);
    }
}
