package Member;

import static org.junit.Assert.*;

import javax.swing.table.DefaultTableModel;

import org.junit.Test;

public class MemberTest {
	//char[] pw = {1,2,3};
	String pw = "123";
	String id = "0417";
	String name = "WI";
	Member test = new Member(id,pw,name);
	String year = "108-1";
	String sj = "科技與社會";
	String[] columns = { "學號", "姓名", "成績"};
	DefaultTableModel table = new DefaultTableModel(null, columns);

	@Test
	public void changePasswordTest() {
		boolean output = test.changePassword(id, pw, "111");
		assertEquals(true,output);
	}
	
	@Test
	public void outputClassmateTest() {
		boolean output = test.outputClassmate(year,sj);
		assertEquals(true,output);
	}
	
	@Test
	public void outputCourseListTest() {
		boolean output = test.outputCourseList(year,table);
		assertEquals(true, output);
	}
	
	/*
	@Test
	public void Test() {
		fail("Not yet implemented");
	}
	*/
}
