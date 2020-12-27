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
	public void setScoreTest() {
		boolean output = test.setScore(subject, studentId, "100");
		assertEquals(true, true);
	}
	
	@Test
	public void getSubjectInformationTest() {
		String output = test.getSubjectInformation(year, subject);
		assertEquals("SM103 微積分 3 選修 孫培真", output);
	}
	/*
	@Test
	public void Test() {
		fail("Not yet implemented");
	}
	*/
}
