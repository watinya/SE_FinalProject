package Member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Teacher extends Member{
	//所有指導課程
	public ArrayList<Subject> subjects = new ArrayList<Subject>();
	//建立帳號基本屬性
	public Teacher(String id, char[] password, String name){
		//紀錄當前使用者:帳號,密碼,使用者名稱
		super(id,new String(password),name);
		//建立指導課程
		try {
		createCourse("data\\teachers\\"+ name +"\\指導課程.txt");	
		}catch(IOException e) {
			System.out.println("建立指導課程 Error");
		}	
	}
	//建立指導課程
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
	//輸入學生成績
	public void setScore(String subject,String studentId, String score){
		try {
			File f = new File("data\\course\\"+findSubject(subject).getYear()+"\\"+ subject +".txt");
			InputStreamReader reade = new InputStreamReader(new FileInputStream(f),"utf-8");
			BufferedReader reader = new BufferedReader(reade);
			String line,id,fileContent = "";
			//第一行不動作
			fileContent = fileContent.concat(reader.readLine() + "\n");
			while((line = reader.readLine()) != null) {
				id = line.split(" ")[0];
				//寫入成績
				if(id.equals(studentId)) {
					fileContent = fileContent.concat(id +" "+ line.split(" ")[1] +" "+ score +"\n");
				}else {
					fileContent = fileContent.concat(line +"\n");
				}
			}
			reader.close();
			//寫入更新資料
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
		}catch(IOException e) {
			System.out.println("輸入學生成績 Error");
		}
	}
	private Subject findSubject(String subject) {
		for(int i=0; i<subjects.size(); i++) {
			if(subject.equals(subjects.get(i).getName())){
				return subjects.get(i);
			}
		}
		return null;
	}
}//end class
