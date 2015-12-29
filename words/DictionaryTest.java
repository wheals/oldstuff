import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class DictionaryTest {

	private static ArrayList<String> words;
	private static ArrayList<String> result, test1;
	private static Dictionary commonWords;
	private static Dictionary greWords;
	private static DictionaryUtility greUtility;
	private static File commonFile, greFile, testFile;
	
	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		greUtility = new DictionaryUtility();
		commonWords = new Dictionary();
		greWords = new Dictionary();
		words = new ArrayList<String>();
		test1 = new ArrayList<String>();
		test1.add("I abhor tests.");
		test1.add("They make me sick to my abdomen.");
		test1.add("So I must adhere to my study schedule.");
		test1.add("I hate tests.");
		
		String[] w = {"they", "make", "me", "sick", "to", "my", "abdomen"};
		for(int i=0;i<w.length;i++)
			words.add(w[i]);
		
		createCommon();
		createGRE();
	}
	
	@AfterClass
	public static void oneTimeTearDown() throws Exception {
		words = null;
		greUtility = null;
		commonWords = null;
		greWords = null;
		test1 = null;
	}

	@Test
	public void testCheckWords() {
		result = commonWords.checkWords(words);
		assertEquals(true, result.get(0).equals("sick"));
		assertEquals(true, result.get(1).equals("abdomen"));
	}
	
	@Test
	public void testCheckWordsStudent()
	{
	    result = greWords.checkWords(words);
	    assertEquals(true, result.get(0).equals("they"));
	    assertEquals(true, result.get(1).equals("make"));
	    assertEquals(true, result.get(2).equals("me"));
	    assertEquals(true, result.get(3).equals("sick"));
	    assertEquals(true, result.get(4).equals("to"));
	    assertEquals(true, result.get(5).equals("my"));
	}
	@Test
	public void testCheckWord(){
		assertEquals(true, commonWords.checkWord("heavy"));
		assertEquals(false, commonWords.checkWord("applicable"));
	}
	
	@Test
	public void testCheckWordStudent()
	{
	    assertEquals(true, greWords.checkWord("sinuous"));
		assertEquals(false, greWords.checkWord("the"));
	}
	
	@Test
	public void testAdd(){
		assertEquals(false, commonWords.checkWord("applicable"));
		commonWords.add("applicable");
		assertEquals(true, commonWords.checkWord("applicable"));
		//restore commonWords back to original
		commonWords.create(commonFile);
	}
	
	@Test
	public void testCheckGreArrayList()
	{
		result = greUtility.checkGRE(test1, greWords);
		assertEquals(result.get(0),"I hate tests");	
	}
	
	@Test
	public void testCheckGreArrayListStudent()
	{
		// Provide additional test for the checkGRE(ArrayList<String> a, Dictionary d)
		// method, with different data than above
	    String s = "We don't need no education";
		test1.add(s);
		result = greUtility.checkGRE(test1, greWords);
        assertEquals(result.get(1),s); 
	}
	
	@Test
	// TO BE IMPLEMENTED BY STUDENT
	public void testCheckGreStringStudent()
	{
		// test the DictionaryUtility method checkGRE(String s, Dictionary d)
		// must be at least 2 tests
	    assertEquals(greUtility.checkGRE("the", greWords),false);
	    assertEquals(greUtility.checkGRE("sinuous", greWords),true); 
	}
	
	@Test
	public void testPrintToFile()
	{
		String parentPath;
		commonWords.add("basketball");
		parentPath = commonFile.getParent();
		testFile = new File(parentPath+"/test.txt");
		//create test.txt file in same directory as commonwords file
		commonWords.printToFile(testFile);
		//create a new commonWords dictionary with test.txt
		commonWords.create(testFile);
		assertEquals(true, commonWords.checkWord("heavy"));
		assertEquals(true, commonWords.checkWord("basketball"));
		//restore commonWords back to original
		commonWords.create(commonFile);
	}
	
	public static void createCommon()
	{
		JFileChooser cf = new JFileChooser();
		cf.setDialogTitle("Choose Common word dictionary file");
		cf.showOpenDialog(null);   		//show file chooser for dictionary
		commonFile = cf.getSelectedFile();
		commonWords.create(commonFile);	//create hash table of dictionary
	}
	
	public static void createGRE()
	{
		JFileChooser cf = new JFileChooser();
		cf.setDialogTitle("Choose GRE dictionary file");
		cf.showOpenDialog(null);   		//show file chooser for dictionary
		greFile = cf.getSelectedFile();
		greWords.create(greFile);	//create hash table of dictionary
	}
}
