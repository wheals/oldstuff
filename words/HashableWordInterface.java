/** Guarantees that Words that implement HashableWord have definitions for hashCode and equals
 * 
 * @author Professor Myers
 *
 */
public interface HashableWordInterface {
	/**
	 * compute the hash code of the word
	 * @return hash code
	 */
	public int hashCode();

	/**
	 * Compares a String portion of a HashableWord to a String
	 * portion of another HashableWord 
	 * @param s String to compare
	 * @return true is Strings are same or else false
	 */
	public boolean equals(HashableWordInterface s);
	
	/**
	 * returns the string portion of the HashableWord
	 * @return string portion of the HashableWord
	 */
	public String getString();

}
