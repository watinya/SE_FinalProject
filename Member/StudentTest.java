package Member;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTest {
	char[] pw = {1,2,3};
	String id = "410877033";
	String name = "test";
	Student test = new Student(id,pw,name);
	String year = "108-1";
	String subject = "科技與社會";
	String tpw = "123";
	Member test1 = new Member(id,tpw,name);
	
	@Test
	public void getScoreTest() {
		String output = test.getScore(id, year, subject);
		assertEquals("0", output);
	}
	
	@Test
	public void printScoreTest() {
		boolean output = test.printScore(test1);
		assertEquals(true , output);
	}
	/*
	@Test
	public void Test() {
		fail("Not yet implemented");
	}
	*/

}
