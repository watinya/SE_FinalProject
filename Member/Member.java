package Member;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.awt.Container;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Member {
	public String id;
	public String password;
	public String name;

	public Member() {
	}

	public Member(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}
	//更改密碼
	public void changePassword(char[] newPassword) {
		try {
			//找檔案路徑
			switch (id.length()) {
			case 9:
				setNewPassword("data\\studentAccount.txt", new String(newPassword));
				break;
			case 5:
				setNewPassword("data\\teacherAccount.txt", new String(newPassword));
				break;
			case 4:
				setNewPassword("data\\administratorAccount.txt", new String(newPassword));
				break;
			default:
				JOptionPane.showMessageDialog(null, "ID長度不正確");
			}
		} catch (IOException e) {
			System.out.println("changePassword Error");
		}
	}
	private void setNewPassword(String dataLocation, String newPassword) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		String line, user, fileContent = "";
		//尋找帳號資料
		while ((line = reader.readLine()) != null) {
			user = line.split(" ")[0];
			if (user.equals(id)) {
				fileContent = fileContent.concat(id + " " + newPassword + " " + name +"\n");
			}else {
				fileContent = fileContent.concat(line + "\n");
			}
		}
		reader.close();

		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
		BufferedWriter writer = new BufferedWriter(write);
		//寫入更新密碼資料
		writer.write(fileContent);
		writer.close();
	}
	//列出課程同學
	public void outputClassmate(String year, String subjectName)  {
		//建檔案路徑
		String dataLocation = "data\\course\\" + year + "\\" + subjectName + ".txt";
		try {
			outputClassmate(dataLocation);
		}catch (IOException e) {
			System.out.println("outputClassmate Error");
		}	
	}
	private void outputClassmate(String dataLocation) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		String line,id,name,fileContent = "";
		//跳過第一行
		reader.readLine();
		//抓出每個學生學號與名字
		while((line = reader.readLine()) != null) {
			id = line.split(" ")[0];
			name = line.split(" ")[1];
			fileContent = fileContent.concat(id +"  "+ name +"\n");
		}
		reader.close();
		JOptionPane.showMessageDialog(null, fileContent, "選課名單", JOptionPane.PLAIN_MESSAGE);
	}
	//列出學期課程清單
	public void outputCourseList(String year) {
		String dataLocation = "data\\course\\" + year;
		try {
			createCourseList(dataLocation);
		}catch(IOException e) {
			System.out.println("outputCourseList Error");
		}
	}
	private void createCourseList(String dataLocation) throws IOException {
		File f = new File(dataLocation);
		File[] fileList = f.listFiles();
		//讀取每個課程第一行內容
		String[][] fileContent = new String[fileList.length][5];
		for(int i=0; i<fileList.length; i++) {
			InputStreamReader read = new InputStreamReader(new FileInputStream(fileList[i]), "utf-8");
			BufferedReader reader = new BufferedReader(read);
			String[] line = reader.readLine().split(" ");
			fileContent[i] = line;
		}
		//視窗顯示
		new CourseList(fileContent);
	}
	
}// end class


