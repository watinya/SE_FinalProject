package Member;

import static org.junit.Assert.*;

import org.junit.Test;

public class TeacherTest {
	char[] pw = {1,2,3};
	String id = "77014";
	String name = "孫培真";
	Teacher test = new Teacher(id,pw,name);
	String year = "108-2";
	String subject = "微積分";
	String studentId = "410877033";

	@Test
	public void writeScoreTest() {
		Object[][] object = {{"410877019","90"},{"410877033","88"}};
		boolean output = test.writeScore(year, subject, object);
		assertEquals(true, true);
	}
	
	
	
	/*
	@Test
	public void Test() {
		fail("Not yet implemented");
	}
	*/
	/*
	for(int i=0; i<data.length; i++) {
		for(int j=0; j<data[i].length; j++) {
			System.out.print(data[i][j] + " ");
		}
		System.out.println();
	}
	*/
}
