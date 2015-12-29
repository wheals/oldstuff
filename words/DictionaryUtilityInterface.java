import java.util.ArrayList;

/** Used for specialty methods of a dictionary
 * 
 * @author Professor Myers
 *
 */
public interface DictionaryUtilityInterface {
	/** returns an array of Strings which are sentences that don't contain a sentence
	 * 
	 * @param a ArrayList of Strings (sentences)
	 * @param d the Dictionary to compare against
	 * @return the ArrayList of Strings (sentences) that don't contain a gre word
	 */
	public ArrayList<String> checkGRE(ArrayList<String> a, DictionaryInterface d);
	
	/** returns true if the String (sentence) contains a gre word
	 * 
	 * @param s String (sentence)
	 * @param d dictionary to compare it against
	 * @return true if gre word is in string (sentence) false if not.
	 */
	public boolean checkGRE(String s, DictionaryInterface d);

}
