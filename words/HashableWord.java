
/**
 * @author Shmuale Mark
 * 
 * Implements a wrapper for a String that has our hashcode implementation.
 */
public class HashableWord implements HashableWordInterface
{
    private String str;
    
    /**
     * Create a new HashableWord
     * 
     * @param str the initial string
     */
    public HashableWord(String s)
    {
        str = s.toLowerCase();
    }
 
    /**
     * Compute the hash code of the word.
     * 
     * @return the hash code
     */
    public int hashCode()
    {
        final int HASH_MULTIPLIER = 31; 
        int h = 0; 
        for (int i = 0; i < str.length(); i++) 
           h = HASH_MULTIPLIER * h + str.charAt(i);
        return Math.abs(h);
    }

    /**
    * Compares a String portion of a HashableWord to a String
    * portion of another HashableWord 
    * @param s HashableWord to compare
    * @return true is Strings are same or else false
    */
    @Override
    public boolean equals(HashableWordInterface s)
    {
        return getString().equals(s.getString());
    }

    /**
     * Get the string that we're wrapping
     * 
     * @return the wrapped String
     */
    @Override
    public String getString()
    {
        return str;
    }
    
    @Override
    public boolean equals(Object o)
    { return o instanceof HashableWordInterface && equals((HashableWordInterface) o); }
    @Override
    public String toString()
    { return getString(); }
}
