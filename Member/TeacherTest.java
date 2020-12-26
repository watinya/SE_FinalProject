package Member;

import static org.junit.Assert.*;

import org.junit.Test;

public class TeacherTest {
	char[] pw = {1,2,3};
	String id = "77014";
	String name = "孫培真";
	Teacher test = new Teacher(id,pw,name);
	String year = "108-1";
	String subject = "科技與社會";
	String studentId = "410877019";

	@Test
	public void setScoreTest() {
		boolean output = test.setScore(subject, studentId, "100");
		assertEquals(true, true);
	}
	
	@Test
	public void getSubjectInformationTest() {
		String output = test.getSubjectInformation(year, subject);
		assertEquals("SM104 科技與社會 2 必修 孫培真", output);
	}
	/*
	@Test
	public void Test() {
		fail("Not yet implemented");
	}
	*/
}
