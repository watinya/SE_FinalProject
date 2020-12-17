package Member;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		//student
		char[] a = {'1','2','3'};
		String id = "410877033";
		String pw = new String(a);
		String name = "梁家銘";
		//teacher
		char[] b = {'8','8','8'};
		String tid = "77001";
		String tpw = new String(b);
		String tname = "孫培真";
		
		Teacher tc = new Teacher(tid,b,tname);
		//tc.outputClassmate("108-1","科技與社會");
		//tc.setScore("科技與社會","410877033","60");
		
		
		Student text1 = new Student(id,a,name);
		//text1.changePassword(b);
		text1.outputClassmate("108-1", "科技與社會");
		text1.outputCourseList("108-1");
		//text1.getScore("108-1", "科技與社會");
		//text1.getScore();
		/*
		int d =3;
		String str = String.format("%3d",d );  
		System.out.println(str);
		*/
	}

}
//File f = new File("data\\subjects\\108-1\\java.txt");
/*
File f = new File("data/students/410877033");
f.mkdir();
*/