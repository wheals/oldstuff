import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Shmuale Mark
 * 
 * Wrapper of a hash table for words, with methods appropriate for a dictionary
 * implementation.
 */
public class Dictionary implements DictionaryInterface
{
    HashTableInterface dict = new HashTable();
    
    /**
     * Checks if a specific given word is in the dictionary
     * 
     * @param s the word
     * @return true if the word is in dictionary, or false if not
     */
    @Override
    public boolean checkWord(String s)
    {
        return dict.contains(new HashableWord(s));
    }

    /**
     * Check whether any words aren't in this dictionary
     * 
     * @param words ArrayList of words to be checked
     * @return an ArrayList of all words not in dictionary
     */
    @Override
    public ArrayList<String> checkWords(ArrayList<String> words)
    {
        ArrayList<String> list = new ArrayList<String>();
        for (String s : words)
            if (!checkWord(s))
                list.add(s);
        return list;
    }
    
    /**
     * Add a word to the dictionary.
     * 
     * @param word String to add to dictionary
     */
    @Override
    public void add(String word)
    {
        dict.add(new HashableWord(word));
    }

    /**
     * Create dictionary from the file contents
     * 
     * @param f the file to read in from
     * @return true if File is found and words added, returns false if file not found
     */
    @Override
    public boolean create(File f)
    {
        dict = new HashTable();
        try
        {
            Scanner sc = new Scanner(f);
            while (sc.hasNext())
                add(sc.next());
            sc.close();
            return true;
        } catch (FileNotFoundException e)
        {
            return false;
        }
    }

    /**
     * Write words in dictionary to a file in alphabetical order
     * 
     * @param f the file to write to
     * @return true if file exists and is writable, false otherwise
     */
    @Override
    public boolean printToFile(File f)
    {
        try
        {
            PrintWriter printer = new PrintWriter(f);
            for (String s : dict.sort())
                printer.println(s);
            printer.close();
            return true;
        } catch (FileNotFoundException e)
        {
            return false;
        }
    }
    
    @Override
    public String toString()
    {
        String ret = "";
        for (String s : dict.sort())
            ret += s + '\n';
        return ret;
    }

}
