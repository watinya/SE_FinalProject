package Member;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Subject {
	private String year;
	private String number;
	private String name;
	private String credit;
	private String type;
	private String techer;
	private ArrayList<SubjectStudents> subjectStudents = new ArrayList<SubjectStudents>();
	//建課程學生
	public Subject(String year, String subjectName) {
		this.year = year;
		name = subjectName;
		//建檔案路徑
		String dataLocation = "data\\course\\" + year + "\\" + subjectName + ".txt";
		try {
			createData(dataLocation);
		}catch (IOException e) {
			System.out.println("outputClassmate Error");
		}	
	}
	private void createData(String dataLocation) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		String line,id,name,score;
		//讀第一行
		line = reader.readLine();
		this.number = line.split(" ")[0];//課程編號
		this.credit = line.split(" ")[2];//學分
		this.type = line.split(" ")[3];//課程類型 
		//抓出每個學生:學號,名字,分數
		while((line = reader.readLine()) != null) {
			id = line.split(" ")[0];//學號
			name = line.split(" ")[1];//名稱
			score = line.split(" ")[2];//分數
			subjectStudents.add(new SubjectStudents(id,name,score));
		}
		reader.close();
	}
	
	public String getYear() {
		return year;
	}
	public String getNumber() {
		return number;
	}
	public String getName() {
		return name;
	}
	public String getCredit() {
		return credit;
	}
	public String getType() {
		return type;
	}
	public String getTecher() {
		return techer;
	}
	public ArrayList<SubjectStudents> getSubjectStudents() {
		return subjectStudents;
	}
}//end class
