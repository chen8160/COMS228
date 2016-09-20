package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Yuxiang Chen
 * This is a JUnit test for Property class.
 *
 */
public class PropertyTest {

	//Test the fields created by default constructor. 
	private Property p1;  //default constructor
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	
	//Set up. p1 is created by default constructor.
	@Before
	public void setUp(){
		p1 = new Property();
		System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	//Test default constructor
	@Test
	public void constructor_default() {
		assertArrayEquals(p1.getPositiveTerms(), new String[]{"fun", "happy", "positive"});
		assertArrayEquals(p1.getNegativeTerms(), new String[]{"sad", "bad", "angry"});
		assertArrayEquals(p1.getStopTerms(), new String[]{"a", "an", "the"});
		assertEquals( 0.5, p1.getMinDistance(), 0.000001);
		assertEquals(p1.getScoringMethod(), 0);
	}
	//test copy constructor
	@Test
	public void constructor_copy(){
		p1.setPositiveTerms(new String[] {"good", "great", "awesome", "interesting"});
		p1.setNegativeTerms(new String[] {"bad", "mad", "fail", "lose"});
		p1.setStopTerms(new String[] {"I", "have", "do", "ok"});
		p1.setMinDistance(4);
		p1.setScoringMethod(1);
		Property p = new Property(p1);
		assertArrayEquals(p1.getPositiveTerms(), p.getPositiveTerms());
		assertArrayEquals(p1.getNegativeTerms(), p.getNegativeTerms());
		assertArrayEquals(p1.getStopTerms(), p.getStopTerms());
		assertEquals(p1.getMinDistance(), p.getMinDistance(), 0.000001);
		assertEquals(p1.getScoringMethod(), p.getScoringMethod());
	}
	//test file name constructor
	@Test
	public void constructor_fileName(){
		Property p = new Property("config1.txt");
		assertArrayEquals(p.getPositiveTerms(), new String[]{"happy","good","fun","fine","positive"});
		assertArrayEquals(p.getNegativeTerms(), new String[]{"dislike","sad","bad","sorry","regret","angry"});
		assertArrayEquals(p.getStopTerms(), new String[]{"I","an","a","the","of","on"});
		assertEquals(p.getMinDistance(), 4.0, 0.000001);
		assertEquals(p.getScoringMethod(), 0);
	}
	//test throws NullPointerException of file name constructor
	@Test(expected = NullPointerException.class)
	public void constructor_fileName_NullPointerException(){
		String s = null;
		Property p = new Property(s);
	}
	//test handle NumberFormatException of file name constructor
	@Test
	public void constructor_fileName_NumberFormatException(){
		Property p = new Property("config_numberFormatException.txt");
	}
	//test handle IOException of file name constructor
	@Test
	public void constructor_fileName_IOException(){
		new Property("s");
		String expected = "s (No such file or directory)";
		assertEquals(outContent.toString().trim(), expected);
	}
	//test if the String array returned by getPositiveTerms() is correct.
	@Test
	public void TestgetPositiveTerms(){
		assertArrayEquals(p1.getPositiveTerms(), new String[] { "fun", "happy", "positive" });
	}
	//test if the positive terms change properly after calling setPositiveTerms() method
	@Test
	public void TestsetPositiveTerms(){
		String[] newterms = new String[]{"good", "ok","fine"};
		p1.setPositiveTerms(newterms);
		assertArrayEquals(newterms, p1.getPositiveTerms());
	}
	//test throws NullPointerException of setPositiveTerms() method
	@Test(expected = NullPointerException.class)
	public void setPositiveTerms_NullPointerException(){
		String[] newterms = null;
		p1.setPositiveTerms(newterms);
	}
	//test if the String array returned by getNegativeTerms() is correct.
	@Test
	public void TestgetNegativeTerms(){
		assertArrayEquals(p1.getNegativeTerms(), new String[] {"sad", "bad", "angry"});
	}
	//test if the negative terms change properly after calling setNegativeTerms() method
	@Test
	public void TestsetNegativeTerms(){
		String[] newterms = new String[]{"mad","cry","sad"};
		p1.setNegativeTerms(newterms);
		assertArrayEquals(newterms, p1.getNegativeTerms());
	}
	//test throws NullPointerException of setNegativeTerms() method
	@Test(expected = NullPointerException.class)
	public void setNegativeTerms_NullPointerException(){
		String[] newterms = null;
		p1.setNegativeTerms(newterms);
	}
	//test if the String array returned by getStopTerms() is correct.
	@Test
	public void TestgetStopTerms(){
		assertArrayEquals(p1.getStopTerms(), new String[] {"a", "an", "the"});
	}
	//test if the stop terms change properly after calling setStopTerms() method
	@Test
	public void TestsetStopTerms(){
		String[] newterms = new String[]{"this", "am", "yes"};
		p1.setStopTerms(newterms);
		assertArrayEquals(newterms, p1.getStopTerms());
	}
	//test throws NullPointerException of setStopTerms() method
	@Test(expected = NullPointerException.class)
	public void setStopTerms_NullPointerException(){
		String[] newterms = null;
		p1.setStopTerms(newterms);
	}
	//test if the int returned by getScoringMethod() is correct
	@Test
	public void testgetScoringMethod(){
		assertEquals(p1.getScoringMethod(), 0);
	}
	//test if the scoringmethod change properly after calling setScoringMethod() method
	@Test
	public void testsetScoringMethod(){
		p1.setScoringMethod(1);
		assertEquals(p1.getScoringMethod(), 1);
	}
	//test throws IllegalArgumentException of setScoringMethod();
	@Test(expected = IllegalArgumentException.class)
	public void setScoringMethod_IllegalArgumentException(){
		p1.setScoringMethod(5);
	}
	//test if the double returned by getMinDistance() is correct
	@Test
	public void testgetMinDistance(){
		assertEquals(p1.getMinDistance(), 0.5, 0.000001);
	}
	//test if the mindistance change properly after calling setMinDistance() method
	@Test
	public void testsetMinDistance(){
		p1.setMinDistance(4.0);
		assertEquals(p1.getMinDistance(), 4.0, 0.000001);
	}
	//test throws IllegalArgumentException of getMinDistance();
	@Test(expected = IllegalArgumentException.class)
	public void setMinDistance_IllegalArgumentException(){
		p1.setMinDistance(-5);
	}
	//test equals() method
	@Test
	public void testequals(){
		Property p = new Property();
		assertTrue(p.equals(p1));
		Property p2 = new Property("config1.txt");
		assertFalse(p1.equals(p2));
	}
	//test toString() method
	@Test
	public void testtoString(){
		assertEquals(p1.toString(), "Property");
	}
	
	
}
