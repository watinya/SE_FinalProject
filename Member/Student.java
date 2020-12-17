package Member;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Student extends Member{	

	private String inYear ;
	private ArrayList<Subject> subjects = new ArrayList<Subject>();
	//建立帳號基本屬性
	public Student(String id, char[] password, String name) {
		//紀錄當前使用者:帳號,密碼,使用者名稱
		super(id,new String(password),name);
		//建立選修課程
		try {
			createCourse("data\\students\\"+ id +"\\選修課程.txt");	
		}catch(IOException e) {
			System.out.println("建立選修課程 Error");
		}	
	}
	//建立選修課程
	private void createCourse(String dataLoaction) throws IOException {
			File f = new File(dataLoaction);
			InputStreamReader reade = new InputStreamReader(new FileInputStream(f),"utf-8");
			BufferedReader reader = new BufferedReader(reade);
			String line;
			while((line = reader.readLine()) != null) {
				subjects.add(new Subject(line.split(" ")[0],line.split(" ")[1]));
			}
			reader.close();
	}
	//查看所有成績
	public void getScore() {
		String content = "";
		for(int i=0; i<subjects.size(); i++) {
			content = content.concat(getScore(subjects.get(i).getYear(),subjects.get(i).getName()) +"\n");
		}
		JOptionPane.showMessageDialog(null, content, "課程成績", JOptionPane.PLAIN_MESSAGE);
	}
	//找出每科成績
	private String getScore(String year, String subject) {
		for(int i=0; i<subjects.size(); i++) {
			if(subjects.get(i).getYear().equals(year) &&
			   subjects.get(i).getName().equals(subject)) {
				for(int j=0; j<subjects.get(i).getSubjectStudents().size(); j++) {
					if(subjects.get(i).getSubjectStudents().get(j).getId().equals(super.id)){
						String content = String.format("%s%5s%3s",subject,"成績:",subjects.get(i).getSubjectStudents().get(j).getScore());
						return content;
						//JOptionPane.showMessageDialog(null, content, "課程成績", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		}
		return null;
	}
}
