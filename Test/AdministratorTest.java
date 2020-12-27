package Test;

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
		assertEquals("410877033 123 梁家銘 學生", output);
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
	
	//400line
	
	/*
	@Test
	public void Test() {
		fail("Not yet implemented");
	}
	*/
}
