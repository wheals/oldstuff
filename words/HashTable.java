import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;


/**
 * @author Shmuale Mark
 * 
 * Implements a hash table for use with Strings (wrapped with HashableWordInterface)
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class HashTable implements HashTableInterface
{
    static final private int NUM_BUCKETS = 100;
    private LinkedList<HashableWordInterface> buckets[];
    private int size;
    
    /**
     * Initialises a new hash table.
     */
    public HashTable()
    {
        size = 0;
        buckets = new LinkedList[NUM_BUCKETS];
        for (int i = 0; i < NUM_BUCKETS; i++)
            buckets[i] = new LinkedList();
    }
    /**
     * Adds an element to this set.  Adds to the appropriate LinkedList by taking the
     * word's hashCode() and mod (%) by HashTable.NUM_BUCKETS.
     * 
     * @param s a HashableWord to add to the HashTable
     * @return the number of words currently in HashTable
     */
    @Override
    public int add(HashableWordInterface s)
    {
        buckets[s.hashCode() % NUM_BUCKETS].add(s);
        return ++size;
    }

    /**
     * Tests to see if the HashableWordInterface is in the HashTable.
     * 
     * @param s a HashableWord
     * @return true if there is a word with the same string in the HashTable
     */
    @Override
    public boolean contains(HashableWordInterface s)
    {
        return buckets[s.hashCode() % NUM_BUCKETS].contains(s);
    }

    /**
     * Puts the words in the hash table in a sorted ArrayList
     * 
     * @return ArrayList of words in hash table in sorted order
     */
    @Override
    public ArrayList<String> sort()
    {
        // Use a treesort (so we don't have to separately put items
        // in a collection and then sort it)
        TreeSet<String> strings = new TreeSet<String>();
        for (LinkedList list : buckets)
            for (Object o : list)
                strings.add(o.toString());
        return new ArrayList<String>(strings);
    }

}
