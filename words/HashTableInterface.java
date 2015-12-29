import java.util.ArrayList;

/** methods for a HashTable of Strings.  An array of LinkedLists
 * 
 * @author Professor Myers
 *
 */
public interface HashTableInterface {

	/**
	 * Adds an element to this set.  Adds to the appropriate LinkedList by taking the wordHashCode()
	 * and mod (%) by the table size.
	 * @param s a HashableWord to add to the HashTable
	 * @return the number of words currently in HashTable
	 */
	public int add(HashableWordInterface s);
	
	/**
	 * Tests to see if the HashableWord is in the HashTable.
	 * @param s a HashableWord
	 * @return true if word contains in the dictionary
	 */
	public boolean contains(HashableWordInterface s);
	
	/**
	 * Puts the words in the hashtable in a sorted ArrayList
	 * @return ArrayList of words in hashtable in sorted order
	 */
	public ArrayList<String> sort();
}
