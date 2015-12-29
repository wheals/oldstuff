import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Shmuale Mark
 * 
 * Implements utility methods for a dictionary
 */
public class DictionaryUtility implements DictionaryUtilityInterface
{
    /**
     * Which of these sentences don't contain a GRE word?
     * 
     * @param a ArrayList of sentences
     * @param d dictionary with the GRE words
     * @return an ArrayList of Strings containing the sentences that don't contain a GRE word
     */
    @Override
    public ArrayList<String> checkGRE(ArrayList<String> a, DictionaryInterface d)
    {
        ArrayList<String> list = new ArrayList<String>();
        for (String s : a)
        {
            s = s.replaceAll("[,.\":;]", ""); // remove punctuation
            if (!checkGRE(s, d))
                list.add(s);
        }
        return list;
    }

    /** 
     * Does this sentence contain a GRE word?
     * 
     * @param s the sentence to check
     * @param d dictionary with the GRE words
     * @return true if s contains a GRE word, false if not.
     */
    @Override
    public boolean checkGRE(String s, DictionaryInterface d)
    {
        Scanner sc = new Scanner(s);
        while (sc.hasNext())
            if (d.checkWord(sc.next()))
            {
                sc.close();
                return true;
            }
        sc.close();
        return false;
    }

}
