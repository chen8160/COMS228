package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Yuxiang Chen
 * This is the JUnit tests for NaiveTextAnalyzer class.
 *
 */
public class NaiveTextAnalyzerTest {
	NaiveTextAnalyzer n;
	Property p;
	@Before
	public void setUp(){
		n = new NaiveTextAnalyzer();
		p = new Property();
		n.init(p);
	}

	//test the set up of default constructor
	@Test
	public void constructor_default(){
		n = new NaiveTextAnalyzer();
		try{
			n.getProperty();
			fail();
		} catch(NullPointerException e){
			assertTrue(true);
		}
		assertEquals("Naive Text Sentiment Analyzer", n.getName());
		assertEquals("Test", n.getInputType());
	}
	//test the set up of constructor with a newname
	@Test
	public void constructor_newname(){
		n = new NaiveTextAnalyzer("abc");
		try{
			n.getProperty();
			fail();
		} catch(NullPointerException e){
			assertTrue(true);
		}
		assertEquals("abc", n.getName());
		assertEquals("Test", n.getInputType());
	}
	//test findSentiment() method
	@Test
	public void testfindSentiment(){
		double sentimentScore = n.findSentiment("I love fun happy good fun happy good positive positive.");
		assertEquals(6, sentimentScore, 0.000001);
		sentimentScore = n.findSentiment("happy fun good sad bad regret");
		assertEquals(0, sentimentScore, 0.000001);
		sentimentScore = n.findSentiment("I dislike sad sad sad bad bad angry angry.");
		assertEquals(-7, sentimentScore, 0.000001);
		//others
	}
	//test findSentiment() method with null String
	@Test(expected = NullPointerException.class)
	public void findSentiment_nullString(){
		String s = null;
		n.findSentiment(s);
	}
	//test findSentiment() method with null property
	@Test(expected = IllegalStateException.class)
	public void findSentiment_nullProperty(){
		n = new NaiveTextAnalyzer();
		n.findSentiment("I love fun happy good fun happy good positive positive.");
	}
	//test findSentiment() method with empty String and numOfWords equals numOfStops
	@Test
	public void findSentment_emptyString_numOfWordsequalsnumOfStops(){
		assertEquals(0, n.findSentiment(""), 0.000001);
		assertEquals(0, n.findSentiment("fun fun fun bad bad bad"), 0.000001);
	}
	//test findCount() method
	@Test
	public void testfindCount(){
		String[] wordArr = new String[] {"I", "love", "good", "fun", "happy", "positive"};
		String[] tokenArr = new String[] {"me", "I", "happy", "is", "am"};
		assertEquals(2, NaiveTextAnalyzer.findCount(wordArr, tokenArr));
	}
	//test findCount() method with wordArr equals null
	@Test(expected = NullPointerException.class)
	public void findCount_wordArrNull(){
		String[] wordArr = null;
		String[] tokenArr = new String[] {"I"};
		NaiveTextAnalyzer.findCount(wordArr, tokenArr);
	}
	//test findCount() method with tokenArr equals null
	@Test(expected = NullPointerException.class)
	public void findCount_tokenArrNull(){
		String[] tokenArr = null;
		String[] wordArr = new String[] {"I"};
		NaiveTextAnalyzer.findCount(wordArr, tokenArr);
	}
	//test findCount() method with empty wordArr
	@Test
	public void findCount_emptyArray(){
		String[] wordArr = new String[] {"I"};
		String[] tokenArr = new String[]{};
		assertEquals(0, NaiveTextAnalyzer.findCount(wordArr, tokenArr));
		tokenArr = new String[] {"I"};
		wordArr = new String[] {};
		assertEquals(0, NaiveTextAnalyzer.findCount(wordArr, tokenArr));
		tokenArr = new String[] {};
		assertEquals(0, NaiveTextAnalyzer.findCount(wordArr, tokenArr));
	}
	//test equals() method
	@Test
	public void testequals(){
		NaiveTextAnalyzer m = new NaiveTextAnalyzer();
		m.init(new Property());
		NaiveTextAnalyzer o = new NaiveTextAnalyzer("A");
		o.init(new Property());
		assertTrue(n.equals(m));
		assertFalse(n.equals(o));
	}
	//test init() method
	@Test
	public void testinit(){
		p = new Property("config1.txt");
		n.init(p);
		assertTrue(p.equals(n.getProperty()));
	}
	//test init() method with null Property
	@Test(expected = NullPointerException.class)
	public void init_null(){
		p = null;
		n.init(p);
	}
	//test showSentiment() method with different numbers
	@Test
	public void testshowSentiment(){
		assertEquals("Positive", n.showSentiment(1));
		assertEquals("Negative", n.showSentiment(-3));
		assertEquals("Unknown", n.showSentiment(0.2));
		assertEquals("Unknown", n.showSentiment(-0.3));
	}
	//test showSentiment() method with null Property
	@Test(expected = IllegalStateException.class)
	public void showSentiment_nullProperty(){
		n = new NaiveTextAnalyzer();
		n.showSentiment(4);
	}
	//test getProperty() method
	@Test
	public void testgetProperty(){
		assertTrue(n.getProperty().equals(p));
	}
}
