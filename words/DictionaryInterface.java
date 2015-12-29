import java.io.File;
import java.util.ArrayList;
/** methods of a Dictionary
 * 
 * @author Professor Myers
 *
 */

public interface DictionaryInterface {
    
	/**
	 * check if a word is in the dictionary
	 * @param s String
	 * @return true if word is in dictionary or false if not
	 */
	public boolean checkWord(String s);

	/**
	 * check if words in an ArrayList are in the dictionary
	 * @param words ArrayList of words to be checked
	 * @return an ArrayList of all words not in dictionary
	 */
	public ArrayList<String> checkWords(ArrayList<String> words);

	/**
	 * create hash table of strings in the file contents
	 * @param f File
	 * @return true if File is found and words added, returns false if file not found
	 */
	public boolean create(File f);

	/**
	 * add word to dictionary
	 * @param word String to add to dictionary
	 */
	public void add(String word);

	/**
	 * write words in dictionary to a file
	 */
	public boolean printToFile(File f);

}
