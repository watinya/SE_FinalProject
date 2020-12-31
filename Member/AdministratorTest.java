package Member;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdministratorTest {
	char[] pw = {1,2,3};
	String id = "0417";
	String name = "WI";
	Administrator test = new Administrator(id,pw,name);
	String year = "108-1";
	String subject = "科技與社會";
	String studentId = "410877033";
	
	@Test
	public void addUserTest() {
		boolean output = test.addUser("410877099", "123", "test", "學生");
		assertEquals(true, output);
	}
	
	@Test
	public void removeUserTest() {
		boolean output = test.removeUser("410877019");
		assertEquals(true, output);
	}
	
	@Test
	public void getUserInformationTest() {
		String output = test.getUserInformation(studentId);
		assertEquals("410877033 123 test 學生", output);
	}
	
	
	@Test
	public void changeUserInformationTest() {
		boolean output = test.changeUserInformation("410877099", "410877099", "321", "XXX", "學生");
		assertEquals(true, output);
	}
	
	@Test
	public void checkStudentExistTest() {
		boolean output = test.checkStudentExist(studentId);
		assertEquals(true,output);
	}
	
	
	@Test
	public void addStudentInfoTest() {
		boolean output = test.addStudentInfo("410877044", "XXX", "108");
		assertEquals(true, output);
	}
	
	@Test
	public void removeStudentTest() {
		boolean output = test.removeStudent("410877044");
		assertEquals(true,output);
	}
	
	@Test
	public void getStudentInformationTest() {
		String output = test.getStudentInformation(studentId);
		assertEquals("410877033 test 109", output);
	}
	
	@Test
	public void changeStudentInformationTest() {
		boolean output = test.changeStudentInformation("410877033", "test", "109");
	}
	
	@Test
	public void addSubjectTest() {
		boolean output = test.addSubject(year, "test", "test", "1", "必修", "葉道明");
		assertEquals(true, output);
	}
	
	@Test
	public void removeSubjectTest() {
		boolean output = test.removeSubject(year, "test");
		assertEquals(true, output);
	}
	
	@Test
	public void getSubjectInformationTest() {
		String output = test.getSubjectInformation("108-1", "計算機概論");
		assertEquals("108-1 SM101 計算機概論 3 必修 葉道明", output);
	}
	
	@Test
	public void changeSubjectInformationTest() {
		try {
			boolean output = test.changeSubjectInformation(year, subject, year, "test", subject, "test", "test", "葉道明");
			assertEquals(true, output);
		}catch(Exception e) {
			System.err.println();
		}
	}
	
	@Test
	public void addStudentCourseTest() {
		boolean output = test.addStudentCourse(year, subject, studentId);
		assertEquals(true, output);
	}
	
	@Test
	public void removeStudentCourseTest() {
		boolean output = test.removeStudentCourse(year, subject, studentId);
		assertEquals(true, output);
	}
	
	/*
	@Test
	public void Test() {
		fail("Not yet implemented");
	}
	*/
}
